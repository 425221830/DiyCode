package com.xiseven.diycode.utils;

import android.os.Looper;

/**
 * Created by XISEVEN on 2016/11/23.
 */

public class ThreadUtils {
    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
