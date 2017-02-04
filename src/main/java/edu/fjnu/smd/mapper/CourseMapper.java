package edu.fjnu.smd.mapper;

import java.util.List;
import java.util.Map;
import edu.fjnu.smd.domain.Course;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseMapper{

    void addCourse(Course course);

    boolean removeCourseByNo(String courseNo);

    boolean removeCourseByTypeId(Integer typeId);

    void updateCourse(Course course);

    Course loadCourseByNo(String courseNo);

    List<Course> loadAll();

    List<Course> loadCourses(Map map);

    List<String> loadCourseByTypeId(Integer typeId);

    //获得该查询条件下会得到的记录条数
    int cntCoursesByHelper(Map map);
    //获得某查询条件下的某页的记录集
    List<Course> loadScopedCourses(Map map);

    //根据type_id查找记录
    List<String> loadByTypeId(int typeId);
}
