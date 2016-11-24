package com.xiseven.diycode.ui.impl;

import com.xiseven.diycode.domain.User;
import com.xiseven.diycode.presenter.BasePresenter;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public interface IBaseView {
    /**
     *  获取presenter对象
     * @param presenter
     */
    void setPresenter(BasePresenter presenter);

}
