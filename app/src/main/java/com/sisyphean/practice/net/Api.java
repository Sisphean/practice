package com.sisyphean.practice.net;

import com.sisyphean.practice.bean.ArticlesBean;
import com.sisyphean.practice.bean.ResponseBean;
import com.sisyphean.practice.bean.UserBean;
import com.sisyphean.practice.common.URLContainer;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @FormUrlEncoded
    @POST(URLContainer.LOGIN_URL)
    Observable<ResponseBean<UserBean>> userLogin(@Field("username") String username,
                                                 @Field("password") String password);


    @GET(URLContainer.HOME_URL)
    Observable<ResponseBean<ArticlesBean>> reqArticleList(@Path("page") int curPage);

}
