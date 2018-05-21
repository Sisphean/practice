package com.sisyphean.practice.bean;

public class UploadBean {


    /**
     * url : /upload/2018-05-18/ca5e9a06dc585c773fa37b3eff50c70a.png
     * key : back
     * name : 服务器付款.png
     * message : 上传成功
     */

    private String url;
    private String key;
    private String name;
    private String message;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
