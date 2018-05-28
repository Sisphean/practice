package com.sisyphean.practice.common;

public class URLContainer {

    public final static String BASE_URL = "";

    /**
     * 注册
     */
    public final static String REGISTER_URL = "register";

    /**
     * 登陆
     */
    public final static String LOGIN_URL = "login";

    /**
     * 首页
     */
    public final static String HOME_URL = "article/list/{page}/json";


    /**
     * 获取验证码
     */
    public static final String VERIFICATION_URL = "/{controller}/send";

    /**
     * 忘记密码
     */
    public static final String RESET_PWD_URL = "forget";

    /**
     * 用户中心
     */
    public static final String USER_MAIN_PAGE_URL = "";

    /**
     * 身份认证
     */
    public static final String AUTH_URL = "identity";

    /**
     * 图片上传
     */
    public static final String UPLOAD_URL = "/{controller}/upload";
}
