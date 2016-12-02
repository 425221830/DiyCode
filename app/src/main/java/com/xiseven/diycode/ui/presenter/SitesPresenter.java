package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.SitesModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.ISitesView;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public class SitesPresenter extends BasePresenter {
    ISitesView mView;
    private SitesModel mModel;

    public SitesPresenter(IBaseView iView, Context context) {
        super(context);
        mView = (ISitesView) iView;
        mModel = new SitesModel();
    }
    public void initSites() {
        mView.showProgress();
        mModel.getSites(new MyCallBack() {
            @Override
            public void success() {
                mView.setAdapter(mModel.sitesList);
                mView.hideProgress();
            }

            @Override
            public void failed() {
                mView.hideProgress();
            }
        });

    }
}
