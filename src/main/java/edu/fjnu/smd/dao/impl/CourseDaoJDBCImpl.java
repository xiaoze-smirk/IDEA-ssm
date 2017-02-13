package edu.fjnu.smd.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import edu.fjnu.smd.dao.CourseDao;
import edu.fjnu.smd.domain.Course;
import edu.fjnu.smd.mapper.CourseMapper;
import edu.fjnu.smd.utils.CourseQueryHelper;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Repository("courseDao")
public class CourseDaoJDBCImpl extends SqlSessionDaoSupport implements CourseDao {

    @Resource
    public void setSqlSessiionFactory(SqlSessionFactory sqlSessiionFactory) {
        super.setSqlSessionFactory(sqlSessiionFactory);
    }

    private CourseMapper getmapper(){
        CourseMapper mapper = this.getSqlSession().getMapper(CourseMapper.class);
        return mapper;
    }

    // 获取reqs
    private Course getNewCourse(Course course) {

        String equipStr = "";
        String[] courseReq = course.getCourseReqs();
        if (courseReq != null && courseReq.length > 0) {

            StringBuffer sb = new StringBuffer();
            for (String req : courseReq)
                sb.append(req).append("|");
            sb.deleteCharAt(sb.length() - 1);
            equipStr = sb.toString();

        }

        course.setReqs(equipStr);

        return course;
    }

    // 获取CourseReqs
    private List<Course> getReqCourse(List<Course> courseList) {

        for (Course course : courseList) {
            course.setCourseReqs(course.getReqs().split("\\|"));
        }

        return courseList;
    }


    private Map<String,Object> getQueryHelper(CourseQueryHelper helper) {

        Map<String,Object> map = new HashMap<String,Object>();		//参数

        if(StringUtils.isNotEmpty(helper.getQryCourseName())&&helper.getQryCourseName()!=null){
            map.put("qryCourseName", helper.getQryCourseName());
        }

        if(helper.getQryEndPoint()!=null){
            map.put("qryEndPoint", helper.getQryEndPoint());
        }

        if(helper.getQryStartPoint()!=null){
            map.put("qryStartPoint", helper.getQryStartPoint());
        }

        if(StringUtils.isNotEmpty(helper.getQryCourseType())&&helper.getQryCourseType()!=null){
            map.put("typeId", Integer.parseInt(helper.getQryCourseType()));
        }

        return map;
    }

    @Override
    public void addCourse(Course course) {

        getmapper().addCourse(getNewCourse(course));

    }

    @Override
    public boolean removeCourseByNo(String courseNo) {

        getmapper().removeCourseByNo(courseNo);

        return true;


    }

    @Override
    public boolean removeCourseByTypeId(Integer typeId) {

        getmapper().removeCourseByTypeId(typeId);

        return true;
    }

    @Override
    public List<Course> loadAll() {

        List<Course> list = getmapper().loadAll();

        return getReqCourse(list);

    }

    @Override
    public Course loadCourseByNo(String courseNo) {

        Course course=new Course();
        course=null;
        if(courseNo!=null)
            course = getmapper().loadCourseByNo(courseNo);
        if(course!=null) {
            course.setCourseReqs(course.getReqs().split("\\|"));
        }
        return course;

    }

    @Override
    public void updateCourse(Course course) {

        String[] courseReq = course.getCourseReqs();
        if (courseReq != null && courseReq.length > 0) {

            getmapper().updateCourse(getNewCourse(course)); // 操作

        } else {
            course.setReqs("");
            getmapper().updateCourse(course); // 操作
        }

    }

    @Override
    public List<Course> loadCourses(CourseQueryHelper helper) {

        List<Course> list = getmapper().loadCourses(getQueryHelper(helper));

        // 返回结果
        return getReqCourse(list);

    }

    @Override
    public List<String> loadCourseByTypeId(Integer typeId) {

        List<String> list = new ArrayList<String>();
        list=null;
        if(getmapper().loadCourseByTypeId(typeId)!=null)
            list=getmapper().loadCourseByTypeId(typeId);

        // 返回结果
        return list;
    }

    @Override
    public int cntCoursesByHelper(CourseQueryHelper helper) {

        int count = getmapper().cntCoursesByHelper(getQueryHelper(helper));

        // 返回结果
        return count;

    }

    @Override
    public List<Course> loadScopedCourses(CourseQueryHelper helper, int startIdx, int fetchSize) {


        Map<String,Object> map = new HashMap<String,Object>();
        map=getQueryHelper(helper);
        map.put("startIdx", startIdx);
        map.put("endIdx", startIdx+fetchSize);

        List<Course> list = getmapper().loadScopedCourses(map);

        // 返回结果
        return getReqCourse(list);

    }

    @Override
    public byte[] getTextbookPic(String courseNo) {

        byte[] textBookPic = null;

        Course course = getmapper().loadCourseByNo(courseNo); // 操作

        textBookPic = course.getCourseTextbookPic();

        return textBookPic;

    }

}