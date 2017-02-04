package edu.fjnu.smd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import edu.fjnu.smd.domain.Course;
import edu.fjnu.smd.exception.DataAccessException;
import edu.fjnu.smd.service.CourseService;
import edu.fjnu.smd.service.CourseTypeService;
import edu.fjnu.smd.utils.CourseQueryHelper;
import edu.fjnu.smd.utils.Page;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Controller
@RequestMapping("/course")
public class CourseController extends BaseController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseTypeService courseTypeService ;


    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setCourseTypeService(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }

    @ModelAttribute
    public void getCourse(@RequestParam(value="courseNo",required=false) String courseNo,
                          Map<String, Object> map){
        if(courseNo != null&&courseService.loadCourseByNo(courseNo)!= null){
            map.put("course", courseService.loadCourseByNo(courseNo));
            if(courseService.loadCourseByNo(courseNo).getCourseTextbookPic()!=null)
                System.out.println("开始要有了了了有图片啊 啊    啊啊 啊啊 啊啊 啊啊啊 ");
        }
    }

    @RequestMapping("/toInput")
    public String toInput(Map<String, Object> map,Course course) throws Exception {


        map.put("courseTypeList", courseTypeService.loadAll());

        course.setCourseStatus("O");
        course.setCourseReqs(new String[]{"a","b"});

        map.put("course", course);

        return "course/input_course";
    }

    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(@RequestParam("coursetextbookpic") MultipartFile file,Course course,Map<String, Object> map) throws Exception{

        //读取文件数据，转成字节数组

        if(file!=null){
            course.setCourseTextbookPic(file.getBytes());
        }

        try{
            courseService.addCourse(course);
            System.out.println("你好");
        }catch(DataAccessException e){
            map.put("exceptionMessage", e.getMessage());

            map.put("courseTypeList", courseTypeService.loadAll());

            return "course/input_course";
        }

        return "redirect:/course/list";
    }


    @RequestMapping("/list")
    public String list(Map<String, Object> map,Page page, CourseQueryHelper helper) throws Exception{

        page.setTotalRecNum((long)courseService.cntCoursesByHelper(helper));
        page.setPageContent(courseService.loadScopedCourses(helper, page.getStartIndex(), page.getEndIndex()-page.getStartIndex()+1));

        map.put("courseTypeList", courseTypeService.loadAll());
        map.put("page", page);
        map.put("helper", helper);
        return "course/list_course";

    }

    @RequestMapping("/list/{pageNo}")
    public String list(@PathVariable("pageNo") Integer pageNo,Map<String, Object> map,Page page,CourseQueryHelper helper) throws Exception{

        if(pageNo!=null)
            page.setPageNo(pageNo);

        page.setTotalRecNum((long)courseService.cntCoursesByHelper(helper));
        page.setPageContent(courseService.loadScopedCourses(helper, page.getStartIndex(), page.getEndIndex()-page.getStartIndex()+1));

        map.put("courseTypeList", courseTypeService.loadAll());
        map.put("page", page);
        map.put("helper", helper);

        return "course/list_course";

    }


    @RequestMapping(value="/remove/{courseNo}", method=RequestMethod.DELETE)
    public String remove(@PathVariable("courseNo") String courseNo) throws Exception{

        courseService.removeCourseByNo(courseNo);

        return "redirect:/course/list";

    }

    @RequestMapping(value="/preUpdate/{courseNo}", method=RequestMethod.GET)
    public String preUpdate(@PathVariable("courseNo") String courseNo,Map<String, Object> map) throws Exception{

        map.put("course" ,courseService.loadCourseByNo(courseNo));

        map.put("courseTypeList", courseTypeService.loadAll());

        return "course/update_course";

    }

    @RequestMapping(value="/update", method=RequestMethod.POST)
    public String update(@RequestParam("coursetextbookpic") MultipartFile file,Course course,Map<String, Object> map) throws Exception{

        //读取多段提交的文件数据，转成字节数组
        if(file.getBytes().length>0){
            course.setCourseTextbookPic(file.getBytes());
        }

        if(course.getCourseTextbookPic()!=null)
            System.out.println("有图片啊 啊    啊啊 啊啊 啊啊 啊啊啊 ");

        try{
            courseService.updateCourse(course);
        }catch(DataAccessException e){
            map.put("exceptionMessage", e.getMessage());

            map.put("courseTypeList", courseTypeService.loadAll());

            return "/course/update_course";
        }

        return "redirect:/course/list";

    }

    @RequestMapping("/getPic/{courseNo}")
    public String getPic(@PathVariable("courseNo") String courseNo,HttpServletRequest request,HttpServletResponse response) throws Exception{

        byte[] textBookPic = courseService.getTextbookPic(courseNo);

        if(textBookPic==null){
            String path = request.getSession().getServletContext().getRealPath("/pics/default.jpg");
            FileInputStream fis = new FileInputStream(new File(path));

            textBookPic = new byte[fis.available()];
            fis.read(textBookPic);
        }

        //向浏览器发通知，我要发送是图片
        response.setContentType("image/jpeg");
        ServletOutputStream sos=response.getOutputStream();
        sos.write(textBookPic);
        sos.flush();
        sos.close();

        return null; //由于已经把界面数据发回去了，所以不需要struts做VIEW服务了。

    }


}
