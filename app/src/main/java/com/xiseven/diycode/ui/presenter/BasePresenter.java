package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.bean.User;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class BasePresenter {
    private Context mContext;

    public BasePresenter(IBaseView iView) {
        mContext = (Context) iView;
    }

    /**
     * 判断是否登录
     * @return
     */
    public boolean isLogin() {
        return (boolean) SPUtils.getParam(mContext, "isLogin", false);
    }
    /**
     * 设置登录状态
     * @return
     */
    public void setLogin(Boolean b) {
        SPUtils.setParam(mContext, "isLogin", b);
    }


}
