package com.xiseven.diycode.http;


/**
 * Created by XISEVEN on 2016/11/29.
 */

public interface MyCallBack<T> {
    void success(T t);

    void failed();
}
