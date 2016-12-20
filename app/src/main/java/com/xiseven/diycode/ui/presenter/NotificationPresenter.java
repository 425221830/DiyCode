package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.NotificationModel;
import com.xiseven.diycode.ui.iView.INotificationView;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public class NotificationPresenter extends BasePresenter {
    private INotificationView iView;
    private NotificationModel mModel;
    private Context mContext;

    public NotificationPresenter(Context context) {
        super(context);
        mContext = context;
        iView = (INotificationView) context;
        mModel = new NotificationModel();
    }

    public void getNotification(Integer offset, Integer limit) {
        mModel.getNotification(offset, limit, new MyCallBack() {
            @Override
            public void success() {
                iView.setList(mModel.nList);
            }

            @Override
            public void failed() {
                iView.failed();
            }
        });
    }
}
