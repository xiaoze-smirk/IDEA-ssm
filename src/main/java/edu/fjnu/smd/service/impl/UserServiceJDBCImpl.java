package edu.fjnu.smd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.fjnu.smd.dao.UserDao;
import edu.fjnu.smd.domain.User;
import edu.fjnu.smd.service.UserService;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Service("userService")
@Transactional
public class UserServiceJDBCImpl implements UserService {

    @Autowired
    private UserDao userDao ;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public User get(String userNo) {

        return userDao.get(userNo);

    }

}