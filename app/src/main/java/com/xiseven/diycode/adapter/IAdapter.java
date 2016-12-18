package com.xiseven.diycode.adapter;

import java.util.List;

/**
 * Created by XISEVEN on 2016/12/18.
 */

public interface IAdapter {
    void setList(List list);

    void notifyChange();
}
