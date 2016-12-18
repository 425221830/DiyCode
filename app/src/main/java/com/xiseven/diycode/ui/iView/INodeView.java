package com.xiseven.diycode.ui.iView;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/18.
 */

public interface INodeView extends IBaseView {
    void notifyRecView(List list);
    void getFailed();

}
