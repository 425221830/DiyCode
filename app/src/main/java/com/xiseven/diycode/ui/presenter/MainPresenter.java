package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.LoginModel;
import com.xiseven.diycode.model.impl.MainModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.IMainView;
import com.xiseven.diycode.utils.BitmapUtils;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class MainPresenter extends BasePresenter {
    private Context mContext;
    private IMainView mMainView;
    private MainModel mMainModel;

    public MainPresenter(IBaseView iView) {
        super((Context) iView);
        mContext = (Context) iView;
        mMainView = (IMainView) iView;
        mMainModel = new MainModel();
    }


    public void updateLogin() {
        new LoginModel().getToken((String) SPUtils.getParam(mContext, "account", ""),
                (String) SPUtils.getParam(mContext, "password", ""), new MyCallBack() {
                    @Override
                    public void success() {
                        new LoginModel().postDevices(new MyCallBack() {
                            @Override
                            public void success() {
                                showHeader();
                            }
                            @Override
                            public void failed() {}});
                    }
                    @Override
                    public void failed() {
                        showHeader();
                    }});
    }

    public void showHeader() {
        String s = (String) SPUtils.getParam(mContext, "myInfo", "");
        try {
            mMainView.showHeader(BitmapUtils.getCacheBitmap(mContext, "headImg"),(String) new JSONObject(s).opt("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



}
