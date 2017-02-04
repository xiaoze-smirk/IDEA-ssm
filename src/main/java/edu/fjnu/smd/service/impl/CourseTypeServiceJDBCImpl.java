package edu.fjnu.smd.service.impl;

import java.util.List;

import edu.fjnu.smd.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import edu.fjnu.smd.dao.CourseDao;
import edu.fjnu.smd.dao.CourseTypeDao;
import edu.fjnu.smd.domain.CourseType;
import edu.fjnu.smd.service.CourseTypeService;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Service("courseTypeService")
@Transactional
public class CourseTypeServiceJDBCImpl implements CourseTypeService {

    @Autowired
    private CourseTypeDao courseTypeDao;

    @Autowired
    private CourseDao courseDao;

    public void setCourseTypeDao(CourseTypeDao courseTypeDao) {
        this.courseTypeDao = courseTypeDao;
    }

    public void setCourseDao(CourseDao courseDao) {
        this.courseDao = courseDao;
    }



    @Override
    public void addCourseType(CourseType courseType) {

        courseTypeDao.addCourseType(courseType);

    }

    @Override
    public List<CourseType> loadAll() {

        return courseTypeDao.loadAll();

    }

    @Override
    public CourseType getCourseTypeById(Integer typeId) {

        return courseTypeDao.getCourseTypeById(typeId);

    }

    @Override
    public void removeCourseType(Integer typeId) {

        if(courseDao.loadCourseByTypeId(typeId)!=null)
            courseDao.removeCourseByTypeId(typeId);

        courseTypeDao.removeCourseType(typeId);

    }

    @Override
    public void updateCourseType(CourseType courseType) {

        courseTypeDao.updateCourseType(courseType);

    }

    @Override
    public List<CourseType> loadPage(Page<CourseType> page) {


        return courseTypeDao.loadPage(page);

    }

    @Override
    public int getpages(int rows) {

        return courseTypeDao.getpages(rows);

    }

}
