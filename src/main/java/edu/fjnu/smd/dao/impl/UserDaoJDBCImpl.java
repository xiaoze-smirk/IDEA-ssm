package edu.fjnu.smd.dao.impl;

import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;
import edu.fjnu.smd.dao.UserDao;
import edu.fjnu.smd.domain.User;
import edu.fjnu.smd.mapper.UserMapper;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Repository("userDao")
public class UserDaoJDBCImpl extends SqlSessionDaoSupport implements UserDao {

    /*
     * mybatis3.0.0+mybatis-psring1.0.0无需，整合包自己注入
     * mybatis3.2.2+mybatis-spring1.2.0 必须自己注入sqlSessionFactory；
     */
    @Resource
    public void setSqlSessiionFactory(SqlSessionFactory sqlSessiionFactory) {
        super.setSqlSessionFactory(sqlSessiionFactory);
    }

    private UserMapper getmapper(){
        UserMapper mapper = this.getSqlSession().getMapper(UserMapper.class);
        return mapper;
    }

    @Override
    public User get(String userNo) {

        User user = getmapper().get(userNo);
        return user;

    }

}
