package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.MyRepliesModel;
import com.xiseven.diycode.ui.iView.IMyRepliesView;
import com.xiseven.diycode.utils.SPUtils;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class MyRepliesPresenter extends BasePresenter {

    private MyRepliesModel mModel;
    private IMyRepliesView iView;
    private Context context;
    public MyRepliesPresenter(Context context) {
        super(context);
        this.context = context;
        iView = (IMyRepliesView) context;
        mModel = new MyRepliesModel();
    }

    public void getMyReplies(String order, Integer offset, Integer limit) {
        mModel.getMyReplies((String) SPUtils.getParam(context, "login", ""), order, offset, limit, new MyCallBack() {
            @Override
            public void success() {
                iView.setList(mModel.list);
            }

            @Override
            public void failed() {
                iView.failed();
            }
        });
    }
}
