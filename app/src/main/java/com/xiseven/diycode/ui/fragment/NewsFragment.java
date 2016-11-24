package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public class NewsFragment extends BaseFragment {

//    @BindView(R.id.pullLoadMoreRecyclerView)
//    PullLoadMoreRecyclerView recView;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {

    }

}
