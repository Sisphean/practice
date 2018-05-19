package com.sisyphean.practice.bean;

import com.google.gson.annotations.SerializedName;

public class AuthBean {

    /**
     * is_id_card : 1
     * identity_status : 审核中
     * true_name : netboy
     * id_card : 110123199211201021
     */

    public static final int AUTH_STATUS_UNAUTH = 0;
    public static final int AUTH_STATUS_AUTHING = 1;
    public static final int AUTH_STATUS_FAIL = 2;
    public static final int AUTH_STATUS_SUNCCESS = 3;

    @SerializedName("is_id_card")
    private int authCode; //认证状态码
    @SerializedName("identity_status")
    private String authStatus; //认证状态
    @SerializedName("true_name")
    private String trueName; //真实姓名
    @SerializedName("id_card")
    private String idCardNum; //身份证号码

    public int getAuthCode() {
        return authCode;
    }

    public void setAuthCode(int authCode) {
        this.authCode = authCode;
    }

    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getIdCardNum() {
        return idCardNum;
    }

    public void setIdCardNum(String idCardNum) {
        this.idCardNum = idCardNum;
    }
}
