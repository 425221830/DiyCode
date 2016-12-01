package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.SitesAdapter;
import com.xiseven.diycode.ui.iView.ISitesView;
import com.xiseven.diycode.ui.presenter.BasePresenter;
import com.xiseven.diycode.ui.presenter.SitesPresenter;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/11/22.
 */

public class SitesFragment extends BaseFragment implements ISitesView{
    @BindView(R.id.recView_sites)
    RecyclerView recView_sites;
    SitesPresenter mPresenter;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_sites;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {
        mPresenter = new SitesPresenter(this);
        mPresenter.initSites();
        recView_sites.setAdapter(new SitesAdapter());
    }

    @Override
    public void initSites() {

    }
}
