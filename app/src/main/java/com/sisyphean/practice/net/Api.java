package com.sisyphean.practice.net;

import com.sisyphean.practice.bean.AuthBean;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UploadBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.URLContainer;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
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


    /**
     * 单张图片上传
     * @param file
     * @return
     */
    @POST("/auth/upload")
    @Multipart
    Observable<ResponseBean<UploadBean>> uploadFile(@Part MultipartBody.Part file);

    /**
     * 多图片上传
     * @param imgMap
     * @return
     */
    @POST("/auth/upload")
    @Multipart
    Observable<ResponseBean<UploadBean>> uploadImages(@PartMap Map<String, RequestBody> imgMap);

    /**
     * 获取认证信息
     * @return
     */
    @POST(URLContainer.AUTH_URL)
    @FormUrlEncoded
    Observable<ResponseBean<AuthBean>> reqAuthInfo(@Field("switch") int swt);

    /**
     * 提交认证信息
     * @param swt 开关：0 获取是否已提交认证， 1 提交认证数据
     * @param trueName
     * @param idCardNum
     * @param justUrl
     * @param backUrl
     * @return
     */
    @POST(URLContainer.AUTH_URL)
    @FormUrlEncoded
    Observable<ResponseBean<String>> userAuthenticate(@Field("switch") int swt,
                                                      @Field("true_name") String trueName,
                                                      @Field("id_card") String idCardNum,
                                                      @Field("id_card_just") String justUrl,
                                                      @Field("id_card_back") String backUrl);




}
