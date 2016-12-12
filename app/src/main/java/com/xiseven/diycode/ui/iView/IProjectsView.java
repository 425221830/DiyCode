package com.xiseven.diycode.ui.iView;

import com.xiseven.diycode.bean.Project;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/7.
 */

public interface IProjectsView extends IBaseView {
    void notifyRecView(List<Project> mList);

    void getProjectsFailed();
}
