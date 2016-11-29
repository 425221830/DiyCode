package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.model.IMainModel;
import com.xiseven.diycode.model.impl.MainModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.IMainView;
import com.xiseven.diycode.utils.SPUtils;

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



}
