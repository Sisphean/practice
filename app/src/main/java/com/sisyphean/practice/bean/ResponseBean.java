package com.sisyphean.practice.bean;

public class ResponseBean<T> {

    /**
     * info : {"uid":"4","email":"bluechinaz1@qq.com","token":"28eb7e53f4c387bbe9ce7bdd483eacc8","nickname":"bluechinaz1"}
     * status : 1
     */

    private T info;
    private int status;
    private String errorMsg;

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ResponseBean{" +
                "info=" + info +
                ", status=" + status +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
