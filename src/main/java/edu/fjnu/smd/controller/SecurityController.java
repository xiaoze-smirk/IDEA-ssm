package edu.fjnu.smd.controller;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import edu.fjnu.smd.domain.User;
import edu.fjnu.smd.exception.SMDException;
import edu.fjnu.smd.service.UserService;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
@Controller
@SessionAttributes(value={"loginedUser"})
@RequestMapping("/security")
public class SecurityController extends BaseController{

    @Autowired
    private UserService userService ;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/toLogin")
    public String toLogin(Map<String, Object> map) throws Exception {
        System.out.println("进来了进来了进来了进来了进来了");
        map.put("user", new User());

        return "login";
    }

    @RequestMapping("/toRegister")
    public String toRegister(Map<String, Object> map) throws Exception {

        map.put("user", new User());
        return "register";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(@Valid User user, Errors result,Map<String, Object> map) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();

        System.out.println(user.getUserNo()+"    "+user.getUserPwd());
        if(result.getErrorCount() > 0){
            System.out.println("出错了!");

            for(FieldError error:result.getFieldErrors()){
                System.out.println(error.getField() + ":" + error.getDefaultMessage());
            }

            return "login";

        }else if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(user.getUserNo(), user.getUserPwd());

            try {
                // 执行登录.
                currentUser.login(token);
                User dbUser = userService.get(user.getUserNo());
                map.put("loginedUser", dbUser);
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException e) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + e.getMessage());
            }
        }

        return "redirect:/security/mainController";
    }

    @RequestMapping(value="/register", method=RequestMethod.POST)
    public String register(@Valid User user, Errors result,Map<String, Object> map) throws Exception {

        try {
            User dbUser = userService.get(user.getUserNo());
            if(result.getErrorCount() > 0||dbUser!=null){
                System.out.println("出错了!");

                for(FieldError error:result.getFieldErrors()){
                    System.out.println(error.getField() + ":" + error.getDefaultMessage());
                }

                return "redirect:/security/toRegister";
            }
            userService.addUser(user);

        } catch (SMDException e) {
            map.put("exceptionMessage", e.getMessage());
        }

        return "login";
    }

    @RequestMapping("/mainController")
    public String main() throws Exception{
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(SessionStatus status) throws Exception{

        status.setComplete();
        return "redirect:/security/toLogin";

    }


}
