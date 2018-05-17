package com.sisyphean.practice.net;

import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.URLContainer;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    /**
     * 用户登录
     * @param email
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(URLContainer.LOGIN_URL)
    Observable<ResponseBean<UserBean>> userLogin(@Field("email") String email,
                                                 @Field("password") String password);


    /**
     * 用户注册
     * @param email
     * @param password
     * @param _password 确认密码
     * @param spread  推荐人邮箱
     * @return
     */
    @FormUrlEncoded
    @POST(URLContainer.REGISTER_URL)
    Observable<ResponseBean<String>> userRegister(@Field("email") String email,
                                                  @Field("password") String password,
                                                  @Field("_password") String _password,
                                                  @Field("spread") String spread);

    /**
     *
     * @param controller 控制器名
     * @param email
     * @param type 0-找回密码   1-增加提现地址
     * @return
     */
    @FormUrlEncoded
    @POST(URLContainer.VERIFICATION_URL)
    Observable<ResponseBean<String>> reqVerification(@Path("controller") String controller,
                                                     @Field("email") String email,
                                                     @Field("type") int type);

    /**
     * 忘记密码
     * @param email
     * @param verify    验证码
     * @param password  新密码
     * @param _password 确认密码
     * @return
     */
    @FormUrlEncoded
    @POST(URLContainer.RESET_PWD_URL)
    Observable<ResponseBean<String>> resetPassword(@Field("email") String email,
                                                   @Field("verify") String verify,
                                                   @Field("password") String password,
                                                   @Field("_password") String _password);

    /**
     * 获取用户首页信息
     * @return
     */
    @FormUrlEncoded
    @POST("")
    Observable<ResponseBean<UserBean>> reqUserMainPageInfo(@Field("default") String def);
}
