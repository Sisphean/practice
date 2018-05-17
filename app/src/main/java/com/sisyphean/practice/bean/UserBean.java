package com.sisyphean.practice.bean;

public class UserBean {

    /**
     * uid : 4
     * email : bluechinaz1@qq.com
     * token : 28eb7e53f4c387bbe9ce7bdd483eacc8
     * nickname : bluechinaz1
     */

    private String uid;
    private String email;
    private String token;
    private String nickname;
    private String balance;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "uid='" + uid + '\'' +
                ", email='" + email + '\'' +
                ", token='" + token + '\'' +
                ", nickname='" + nickname + '\'' +
                ", balance='" + balance + '\'' +
                '}';
    }
}
