package com.xiseven.diycode;

import android.app.Application;
import android.content.Context;

/**
 * Created by XISEVEN on 2016/11/20.
 */

public class DiyCodeApp extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }
}
