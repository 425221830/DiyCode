package com.xiseven.diycode.ui.iView;

import com.xiseven.diycode.bean.Sites;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public interface ISitesView extends IBaseView {
    void hideProgress();
    void showProgress();
    void failed();
    void setAdapter(List<Sites> sitesList);
}
