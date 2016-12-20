package com.xiseven.diycode.ui.iView;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/20.
 */

public interface INotificationView extends IBaseView {
    void setList(List list);

    void failed();
}
