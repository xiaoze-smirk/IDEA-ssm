package edu.fjnu.smd.service;

import edu.fjnu.smd.domain.User;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface UserService {

    User get(String userNo);

    void addUser(User user);

}

