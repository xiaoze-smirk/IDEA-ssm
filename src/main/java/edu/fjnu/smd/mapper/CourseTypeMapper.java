package edu.fjnu.smd.mapper;

import java.util.List;
import java.util.Map;
import edu.fjnu.smd.domain.CourseType;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface CourseTypeMapper {

    void addCourseType(CourseType courseType);

    void removeCourseType(Integer typeId);

    void updateCourseType(CourseType courseType);

    CourseType getCourseTypeById(Integer typeId);

    List<CourseType> loadAll();

    List<CourseType> loadPage(Map map);

    int getpages();

}

