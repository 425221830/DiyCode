package com.xiseven.diycode.ui.presenter;

import android.content.Context;
import android.util.Log;
import android.util.LogPrinter;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.ILoginModel;
import com.xiseven.diycode.model.impl.LoginModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.ILoginView;

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
        iView.setPresenter(this);
        mContext = (Context) iView;
        mLoginView = (ILoginView) iView;
        mLoginModel = new LoginModel();
    }

    public void login(final String accounts, final String password) {
        mLoginModel.getToken(accounts, password, new MyCallBack() {
            @Override
            public void success(Object o) {
                String token = o.toString();
                //注册设备
                mLoginModel.postDevices("android", token);
                //获取我的信息
                mLoginModel.getMyInfo(token);
                Log.d(TAG, "success: " + token);
                mLoginView.loginSuccess();
            }

            @Override
            public void failed() {
                mLoginView.loginFailed();
            }
        });
    }

}
