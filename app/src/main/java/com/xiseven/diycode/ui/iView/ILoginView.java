package com.xiseven.diycode.ui.iView;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public interface ILoginView extends IBaseView{
    /**
     * 登录成功
     */
    void loginSuccess();

    /**
     * 登录失败
     */
    void loginFailed();

}
