package com.xiseven.diycode.ui.presenter;

import android.content.Context;

import com.xiseven.diycode.http.MyCallBack;
import com.xiseven.diycode.model.impl.ProjectsModel;
import com.xiseven.diycode.ui.iView.IBaseView;
import com.xiseven.diycode.ui.iView.INodeView;
import com.xiseven.diycode.ui.iView.IProjectsView;

/**
 * Created by XISEVEN on 2016/12/7.
 */

public class ProjectPresenter extends BasePresenter {


    private ProjectsModel mModel;
    private INodeView mView;

    public ProjectPresenter(IBaseView iView, Context context) {
        super(context);
        mView = (INodeView) iView;
        mModel = new ProjectsModel();
    }

    public void getProjects(Integer node_id, Integer offset, Integer limit) {
        mModel.getProjects(node_id, offset, limit, new MyCallBack() {
            @Override
            public void success() {
                mView.notifyRecView(mModel.projectList);
            }

            @Override
            public void failed() {
                mView.getFailed();
            }
        });
    }
}
