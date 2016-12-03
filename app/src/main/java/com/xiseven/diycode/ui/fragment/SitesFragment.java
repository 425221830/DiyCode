package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.SitesAdapter;
import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.ui.iView.ISitesView;
import com.xiseven.diycode.ui.presenter.SitesPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by XISEVEN on 2016/11/22.
 */

public class SitesFragment extends BaseFragment implements ISitesView {
    @BindView(R.id.recView_sites)
    RecyclerView recView_sites;
    @BindView(R.id.pd_sites)
    ProgressBar pb_sites;
    @BindView(R.id.tv_sites)
    TextView tv_sites;
    SitesPresenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_sites;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {
        mPresenter = new SitesPresenter(this, mActivity);
        recView_sites.setLayoutManager(new LinearLayoutManager(mActivity));
        mPresenter.initSites();
    }

    @Override
    public void hideProgress() {
        pb_sites.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        pb_sites.setVisibility(View.VISIBLE);
    }

    /**
     * 加载失败后显示刷新按钮
     */
    @Override
    public void failed() {
        tv_sites.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.tv_sites)
    void failedClick() {
        tv_sites.setVisibility(View.GONE);
        mPresenter.initSites();
    }

    @Override
    public void setAdapter(List<Sites> sitesList) {
        recView_sites.setAdapter(new SitesAdapter(mActivity, sitesList));
    }
}
