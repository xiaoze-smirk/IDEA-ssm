package edu.fjnu.smd.dao;

import java.util.List;
import edu.fjnu.smd.domain.CourseType;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseTypeDao {

    void addCourseType(CourseType courseType);

    List<CourseType> loadAll();

    List<CourseType> loadPage(int pageindex,int rows);

    CourseType getCourseTypeById(Integer typeId);

    void removeCourseType(Integer typeId);

    void updateCourseType(CourseType courseType);

    int getpages(int rows);

}
