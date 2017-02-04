package edu.fjnu.smd.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import edu.fjnu.smd.domain.CourseType;
import edu.fjnu.smd.service.CourseTypeService;
import edu.fjnu.smd.utils.TypePage;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Controller
@RequestMapping("/courseType")
public class CourseTypeController extends BaseController {

    @Autowired
    private CourseTypeService courseTypeService ;

    public void setCourseTypeService(CourseTypeService courseTypeService) {
        this.courseTypeService = courseTypeService;
    }


    @ModelAttribute
    public void getCourseType(@RequestParam(value="typeId",required=false) Integer typeId,
                              Map<String, Object> map){
        if(typeId != null){
            map.put("courseType", courseTypeService.getCourseTypeById(typeId));
        }
    }

    /**
     * 访问课程类型输入界面
     */
    @RequestMapping("/toInput")
    public String input(Map<String, Object> map) throws Exception{

        map.put("courseType", new CourseType());

        return "courseType/input_course_type";
    }

    /**
     * 创建新课程类型
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/create", method=RequestMethod.POST)
    public String create(CourseType courseType) throws Exception{

        courseTypeService.addCourseType(courseType);

        return "redirect:/courseType/list";

    }

    @RequestMapping("/list")
    public String list(Map<String, Object> map,TypePage typePage ) throws Exception{

        typePage.setAllpage(courseTypeService.getpages(typePage.getRows()));

        map.put("courseTypeList", courseTypeService.loadPage(typePage.getPageindex(), typePage.getRows()));
        map.put("typePage", typePage);

        return "courseType/list_course_type";
    }

    @RequestMapping("/list/{index}")
    public String list(@PathVariable("index") Integer idx,Map<String, Object> map,TypePage typePage ) throws Exception{

        if(idx!=null)
            typePage.setPageindex(idx);

        typePage.setAllpage(courseTypeService.getpages(typePage.getRows()));

        map.put("courseTypeList", courseTypeService.loadPage(typePage.getPageindex(), typePage.getRows()));
        map.put("typePage", typePage);

        return "courseType/list_course_type";
    }

    @RequestMapping(value="/remove/{typeId}", method=RequestMethod.DELETE)
    public String remove(@PathVariable("typeId") Integer typeId) throws Exception{

        courseTypeService.removeCourseType(typeId);

        return "redirect:/courseType/list";
    }

    @RequestMapping(value="/preUpdate/{typeId}", method=RequestMethod.GET)
    public String preUpdate(@PathVariable("typeId") Integer typeId, Map<String, Object> map) throws Exception{

        map.put("courseType", courseTypeService.getCourseTypeById(typeId));

        return "courseType/update_course_type";
    }

    @RequestMapping(value="/update", method=RequestMethod.PUT)
    public String update(CourseType courseType) throws Exception{


        System.out.println(courseType);
        courseTypeService.updateCourseType(courseType);

        return "redirect:/courseType/list";
    }

}

