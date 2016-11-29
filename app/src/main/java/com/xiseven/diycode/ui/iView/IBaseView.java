package com.xiseven.diycode.ui.iView;

import com.xiseven.diycode.ui.presenter.BasePresenter;

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
