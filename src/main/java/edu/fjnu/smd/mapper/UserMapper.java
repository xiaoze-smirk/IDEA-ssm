package edu.fjnu.smd.mapper;

import edu.fjnu.smd.domain.User;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public interface UserMapper {

    User get(String userNo);

    void addUser(User user);

}
