package edu.fjnu.smd.service;

import java.util.List;
import edu.fjnu.smd.domain.Course;
import edu.fjnu.smd.utils.CourseQueryHelper;


/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseService {

    void addCourse(Course course);
    boolean removeCourseByNo(String courseNo);
    List<Course> loadAll();
    Course loadCourseByNo(String courseNo);
    void updateCourse(Course course);
    List<Course> loadCourses(CourseQueryHelper helper);

    /**
     * 根据课程编号，获得教材封面图片
     * @param courseNo
     * @return
     */
    byte[] getTextbookPic(String courseNo);

    //获得该查询条件下会得到的记录条数
    int cntCoursesByHelper(CourseQueryHelper helper);
    //获得某查询条件下的某页的记录集
    List<Course> loadScopedCourses(CourseQueryHelper helper, int startIdx, int fetchSize);

}
