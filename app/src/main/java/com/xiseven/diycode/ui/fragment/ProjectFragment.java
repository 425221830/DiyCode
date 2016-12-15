package com.xiseven.diycode.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;
import com.xiseven.diycode.R;
import com.xiseven.diycode.adapter.ProjectsAdapter;
import com.xiseven.diycode.bean.Project;
import com.xiseven.diycode.ui.iView.IProjectsView;
import com.xiseven.diycode.ui.presenter.ProjectPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by XISEVEN on 2016/11/21.
 */

public class ProjectFragment extends BaseFragment implements IProjectsView{
    private ProjectPresenter mPresenter;
    private int i = 1;
    private ProjectsAdapter projectsAdapter;

    @BindView(R.id.pullLoadMoreRecyclerView)
    PullLoadMoreRecyclerView recView;
    @Override
    public int getContentViewId() {
        return R.layout.fragment_project;
    }

    @Override
    protected void initAllMembers(View view, Bundle savedInstanceState) {
        mPresenter = new ProjectPresenter(this, mActivity);
        initRecView();
        recView.setRefreshing(true);
        mPresenter.getProjects(null, null, 10);

    }

    private void initRecView() {
        recView.setLinearLayout();
        if (projectsAdapter == null) {
            projectsAdapter = new ProjectsAdapter(mActivity);
        }
        recView.setAdapter(projectsAdapter);
        recView.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
                mPresenter.getProjects(null, null, 10);
                i = 1;
            }

            @Override
            public void onLoadMore() {
                mPresenter.getProjects(null, null, 10*++i);
            }
        });
    }

    @Override
    public void notifyRecView(List<Project> mList) {
        projectsAdapter.setProjectList(mList);
        projectsAdapter.notifyDataSetChanged();
        recView.setPullLoadMoreCompleted();
    }

    @Override
    public void getProjectsFailed() {
        recView.setPullLoadMoreCompleted();
        showToast("加载失败");
    }
}
