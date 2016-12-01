package com.xiseven.diycode.ui.presenter;

import android.content.Context;
import android.util.Log;
import android.util.LogPrinter;

import com.xiseven.diycode.bean.MessageEvent;
import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.ILoginModel;
import com.xiseven.diycode.model.impl.LoginModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.ILoginView;
import com.xiseven.diycode.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class LoginPresenter extends BasePresenter {
    private static final String TAG = LogPrinter.class.getSimpleName();
    private ILoginView mLoginView;
    private ILoginModel mLoginModel;
    private Context mContext;

    public LoginPresenter(IBaseView iView) {
        super(iView);
        mContext = (Context) iView;
        mLoginView = (ILoginView) iView;
        mLoginModel = new LoginModel();
    }

    public void login(final String accounts, final String password) {
        SPUtils.setParam(mContext, "account", accounts);
        SPUtils.setParam(mContext, "password", password);
        mLoginModel.getToken(accounts, password, new MyCallBack() {
            @Override
            public void success() {
                //注册设备
                mLoginModel.postDevices(new MyCallBack() {
                    @Override
                    public void success() {
                        //获取我的信息
                        mLoginModel.getMyInfo(new MyCallBack() {
                            @Override
                            public void success() {
                                //获取登录用户头像
                                mLoginModel.getHeadImg(new MyCallBack() {
                                    @Override
                                    public void success() {
                                        //设置登录状态
                                        setLogin(true);
                                        EventBus.getDefault().post(new MessageEvent(1));
                                        mLoginView.loginSuccess();
                                    }
                                    @Override
                                    public void failed() {}});}
                            @Override
                            public void failed() {}});}
                    @Override
                    public void failed() {}});}
            @Override
            public void failed() {
                setLogin(false);
                mLoginView.loginFailed();
            }
        });
    }

}
