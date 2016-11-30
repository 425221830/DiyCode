package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.bean.User;
import com.xiseven.diycode.model.IMainModel;
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
    private IMainModel mMainModel;

    public MainPresenter(IBaseView iView) {
        super(iView);
        mContext = (Context) iView;
        mMainView = (IMainView) iView;
        mMainModel = new MainModel();
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
