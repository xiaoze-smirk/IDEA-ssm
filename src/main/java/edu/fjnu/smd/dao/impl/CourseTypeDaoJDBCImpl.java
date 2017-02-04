package edu.fjnu.smd.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import edu.fjnu.smd.dao.CourseTypeDao;
import edu.fjnu.smd.domain.CourseType;
import edu.fjnu.smd.mapper.CourseTypeMapper;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Repository("courseTypeDao")
public class CourseTypeDaoJDBCImpl extends SqlSessionDaoSupport implements CourseTypeDao {

    @Resource
    public void setSqlSessiionFactory(SqlSessionFactory sqlSessiionFactory) {
        super.setSqlSessionFactory(sqlSessiionFactory);
    }

    private CourseTypeMapper getmapper(){
        CourseTypeMapper mapper = this.getSqlSession().getMapper(CourseTypeMapper.class);
        return mapper;
    }


    @Override
    public void addCourseType(CourseType courseType) {

        getmapper().addCourseType(courseType);

    }

    @Override
    public void removeCourseType(Integer typeId) {

        getmapper().removeCourseType(typeId);

    }

    @Override
    public void updateCourseType(CourseType courseType) {

        getmapper().updateCourseType(courseType);

    }

    @Override
    public CourseType getCourseTypeById(Integer typeId) {

        CourseType courseType = getmapper().getCourseTypeById(typeId);
        return courseType;

    }

    @Override
    public List<CourseType> loadAll() {

        List<CourseType> list = getmapper().loadAll();

        return list;

    }

    @Override
    public List<CourseType> loadPage(int pageindex, int rows) {

        Map<String,Object> map = new HashMap<String,Object>();		//参数
        map.put("startIdx", (pageindex-1)*rows);
        map.put("endIdx", pageindex*rows);

        List<CourseType> list = getmapper().loadPage(map);

        return list;

    }

    @Override
    public int getpages(int rows) {

        int pages = 0;

        // 查询总记录数
        int count = getmapper().getpages();

        if (count % rows == 0) {
            pages = count / rows;
        } else {
            pages = count / rows + 1;
        }
        // 返回结果
        return pages;

    }

}