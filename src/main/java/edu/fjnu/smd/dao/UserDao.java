package edu.fjnu.smd.dao;

import edu.fjnu.smd.domain.User;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface UserDao {

    User get(String userNo);

    void addUser(User user);

}
