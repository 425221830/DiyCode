package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.model.impl.SitesModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.ISitesView;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesPresenter extends BasePresenter {
    ISitesView mView;
    Context mContext;
    private SitesModel mModel;

    public SitesPresenter(IBaseView iView) {
        super(iView);
        mView = (ISitesView) iView;
        mContext = (Context) iView;
        mModel = new SitesModel();
    }
    public void initSites() {
        mModel.getSites();
    }
}
