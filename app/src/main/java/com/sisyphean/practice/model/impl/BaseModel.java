package com.sisyphean.practice.model.impl;

import com.sisyphean.practice.model.IModel;
import com.sisyphean.practice.net.Api;
import com.sisyphean.practice.net.RetrofitClient;

public class BaseModel implements IModel {
    @Override
    public Api doRequest() {
        return RetrofitClient.getApi();
    }
}
