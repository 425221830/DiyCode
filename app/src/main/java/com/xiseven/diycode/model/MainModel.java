package com.xiseven.diycode.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.xiseven.diycode.model.impl.IMainModel;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class MainModel implements IMainModel{

    @Override
    public void getHeadImg(String imgUrl, AbsCallback callback) {
        OkGo.get(imgUrl)
                .execute(callback);
    }
}
