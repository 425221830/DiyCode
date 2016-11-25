package com.xiseven.diycode.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.AbsCallback;
import com.xiseven.diycode.model.impl.IMainModel;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class MainModel implements IMainModel{

    @Override
    public void getHeadImg(final String imgUrl, final AbsCallback callback) {
        new Thread(){
            @Override
            public void run() {
                OkGo.get(imgUrl)
                        .execute(callback);
            }
        }.start();

    }
}
