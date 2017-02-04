package edu.fjnu.smd.service;

import java.util.List;
import edu.fjnu.smd.domain.CourseType;
import edu.fjnu.smd.page.Page;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseTypeService {

    void addCourseType(CourseType courseType);

    List<CourseType> loadAll();

    List<CourseType> loadPage(Page<CourseType> page);

    CourseType getCourseTypeById(Integer typeId);

    void removeCourseType(Integer typeId);

    void updateCourseType(CourseType courseType);

    int getpages(int rows);

}
