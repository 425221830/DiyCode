package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.xiseven.diycode.bean.MessageEvent;
import com.xiseven.diycode.bean.User;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.IMyInfoModel;
import com.xiseven.diycode.model.impl.MyInfoModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.IInfoView;
import com.xiseven.diycode.utils.BitmapUtils;
import com.xiseven.diycode.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class MyInfoPresenter extends BasePresenter{

    private IMyInfoModel myInfoModel;
    private IInfoView mInfoView;
    private Context mContext;

    public MyInfoPresenter(IBaseView iView) {
        super(iView);
        mContext = (Context) iView;
        mInfoView = (IInfoView) iView;
        myInfoModel = new MyInfoModel();
    }

    public void deleteDevices() {
        myInfoModel.deleteDevices(new MyCallBack() {
            @Override
            public void success() {
                setLogin(false);
                EventBus.getDefault().post(new MessageEvent(0));
            }

            @Override
            public void failed() {
            }
        });
    }

    public void init() {
        User user = new Gson().fromJson((String) SPUtils.getParam(mContext, "myInfo", ""), User.class);
        mInfoView.showHead(BitmapUtils.getCacheBitmap(mContext, "headImg"));
        mInfoView.showUsername(user.getName());
        mInfoView.showBio(user.getBio());
        mInfoView.showEmail(user.getEmail());
        mInfoView.showGithub(user.getGithub());
        mInfoView.showLevel(user.getLevel_name());
    }
}
