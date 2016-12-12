package com.xiseven.diycode.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XISEVEN on 2016/12/7.
 */

public class DateUtils {

    @SuppressLint("SimpleDateFormat")
    public static long stringToLong(String strTime)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        Date date = null;
        date = formatter.parse(strTime);
        return date.getTime();
    }

    public static String getTimeAgo(String strTime) throws ParseException {
        long time = stringToLong(strTime);
        long nowTime = System.currentTimeMillis();
        long l = nowTime - time;
        l /= 1000;
        if (l < 60) {
            return (l) + "秒前";
        } else if (l < 60 * 60) {
            return (l / 60) + "分钟前";
        } else if (l < 60 * 60 * 24) {
            return (l / 60 / 60) + "小时前";
        } else if (l < 60 * 60 * 24 * 30) {
            return (l / 60 / 60 / 24) + "天前";
        } else if (l < 60 * 60 * 24 * 30 * 12) {
            return (l / 60 / 60 / 24 / 30) + "个月前";
        } else {
            return "很久以前";
        }
    }
}
