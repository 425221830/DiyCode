package com.xiseven.diycode.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.StringCallback;
import com.xiseven.diycode.domain.User;
import com.xiseven.diycode.ui.impl.IBaseView;
import com.xiseven.diycode.utils.SPUtils;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;

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
     * 保存登录信息
     * @param accounts 帐号
     * @param password 密码
     * @param s get token返回的json字符串
     */
    public void saveLoginInfo(String accounts, String password, String s) {
        SPUtils.setParam(mContext, "accounts", accounts);
        SPUtils.setParam(mContext, "password", password);
        SPUtils.setParam(mContext, "isLogin", true);
        try {
            SPUtils.setParam(mContext, "access_token", String.valueOf(new JSONObject(s).opt("access_token")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存用户名字和头像
     * @param user
     */
    public void saveUserInfo(User user) {
        SPUtils.setParam(mContext, "userName", user.getName());
        SPUtils.setParam(mContext, "userHeadUrl", user.getAvatar_url());
    }

}
