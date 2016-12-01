package com.xiseven.diycode.model;

import com.xiseven.diycode.http.MyCallBack;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public interface ILoginModel{
    void getToken(String username, String password, MyCallBack callback);
    void getMyInfo(MyCallBack callBack);
    void postDevices(MyCallBack callBack);
    void getHeadImg(MyCallBack callBack);
}