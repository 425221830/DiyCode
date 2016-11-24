package com.xiseven.diycode.model.impl;

import com.lzy.okgo.callback.AbsCallback;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public interface ILoginModel{
    void getToken(String username, String password, AbsCallback callback);
    void getUserInfo(String token, AbsCallback callback);
}