package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView recView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, null);
        ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        recView = (PullLoadMoreRecyclerView) view.findViewById(R.id.pullLoadMoreRecyclerView);
//        recView.setLinearLayout();
//        recView.setRefreshing(true);
//        mrecViewAdapter = new RecyclerViewAdapter(mActivity, mDataList);
//        recView.setAdapter(mrecViewAdapter);
//        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
//            @Override
//            public void onRefresh() {
//            }
//
//            @Override
//            public void onLoadMore() {
//            }
//        });
    }
}
