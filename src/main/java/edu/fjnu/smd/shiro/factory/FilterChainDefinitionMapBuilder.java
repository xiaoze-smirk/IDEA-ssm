package edu.fjnu.smd.shiro.factory;

import java.util.LinkedHashMap;

/**
 * Created by xiaozemaliya on 2017/2/13.
 */
public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap(){
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();

        /*
            配置哪些页面需要受保护.
            以及访问这些页面需要的权限.
            1). anon 可以被匿名访问
            2). authc 必须认证(即登录)后才可能访问的页面.
            3). logout 登出.
            4). roles 角色过滤器
            5). 过滤是有顺序的，所以下面顺序要注意
         */

        map.put("/index.jsp", "anon");
        map.put("/security/toLogin" ,"anon");
        map.put("/security/login", "anon");
        map.put("/security/toRegister" ,"anon");
        map.put("/security/register" ,"anon");
        map.put("/security/logout", "logout");
//      //认证后，角色是user的才能访问
//      map.put("/user.jsp", "authc,roles[user]");

        map.put("/**", "authc");

        return map;
    }

}
