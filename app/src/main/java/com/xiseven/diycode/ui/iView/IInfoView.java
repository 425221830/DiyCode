package com.xiseven.diycode.ui.iView;

import android.graphics.Bitmap;

/**
 * Created by XISEVEN on 2016/12/1.
 */

public interface IInfoView extends IBaseView{
    void showHead(Bitmap bitmap);

    void showUsername(String username);

    void showBio(String bio);
    void showEmail(String email);
    void showGithub(String github);
    void showLevel(String level);
}
