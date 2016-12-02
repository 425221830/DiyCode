package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.SitesAdapter;
import com.xiseven.diycode.bean.Sites;
import com.xiseven.diycode.ui.iView.ISitesView;
import com.xiseven.diycode.ui.presenter.SitesPresenter;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/11/22.
 */

public class SitesFragment extends BaseFragment implements ISitesView {
    @BindView(R.id.recView_sites)
    RecyclerView recView_sites;
    @BindView(R.id.pd_sites)
    ProgressBar pb_sites;
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
        recView_sites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void hideProgress() {
        pb_sites.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        pb_sites.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAdapter(List<Sites> sitesList) {
        recView_sites.setAdapter(new SitesAdapter(mActivity, sitesList));
    }

}
