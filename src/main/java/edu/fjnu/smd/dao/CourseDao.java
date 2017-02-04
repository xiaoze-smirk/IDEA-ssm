package edu.fjnu.smd.dao;

import java.util.List;
import edu.fjnu.smd.domain.Course;
import edu.fjnu.smd.utils.CourseQueryHelper;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseDao {

    void addCourse(Course course);

    boolean removeCourseByNo(String courseNo);

    boolean removeCourseByTypeId(Integer typeId);

    void updateCourse(Course course);

    Course loadCourseByNo(String courseNo);

    List<Course> loadAll();

    List<Course> loadCourses(CourseQueryHelper helper);

    List<String> loadCourseByTypeId(Integer typeId);

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

