package edu.fjnu.smd.config;

/**
 * Created by xiaozemaliya on 2017/1/31.
 */
public final class VersionInfo {


    //application info
    public final static String APP_NAME="教务管理系统-演示版";
    public final static String APP_VERSION="1.0";
    public final static String APP_STATUS="Beta";
    public final static String APP_BUILDATE="2016-05-20";
    public final static String APP_BUILDVER="#2";

    //application author
    public final static String APP_AUTHOR="JAVAWEB课堂案例编写组";
    public final static String APP_WORKSTUDIO="2003-2016 福建师大信息技术学院 ";

    public final static String buildFooterStr()
    {
        StringBuffer sb=new StringBuffer();

        sb.append(APP_NAME);
        sb.append(" "+"(版本:"+APP_STATUS+APP_VERSION);
        sb.append(" &nbsp;&nbsp;Build:"+APP_BUILDVER);
        sb.append(" "+APP_BUILDATE+")");
        sb.append("&nbsp;&nbsp;&nbsp;&nbsp;开发团队:&nbsp;<a href=\"mailto:joeyang_ong@yahoo.com.cn\">"+APP_AUTHOR+"</a><br/>");
        sb.append("(C)&nbsp;"+APP_WORKSTUDIO+"&nbsp;&nbsp;");

        return sb.toString();
    }

}
