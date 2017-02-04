package edu.fjnu.smd.domain;

import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Component
public class User extends ValueObject{

    @NotEmpty(message="用户账号不能为空！")
    @Pattern(regexp="\\w{6,10}",message="用户名6-10位")
    private String userNo;

    private String userName;

    @NotEmpty(message="密码不能为空")
    @Length(max=10,min=6,message="密码不能小于6位，且不能大于10位")
    private String userPwd;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

}
