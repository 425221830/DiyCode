package com.xiseven.diycode.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.xiseven.diycode.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by XISEVEN on 2016/11/24.
 */

public class BitmapUtils {
    /**
     * 保存图片到cache缓存
     * @param context
     * @param bitmap
     */
    public static void saveImgToCache(Context context, String imgName, Bitmap bitmap) {

        File file = new File(context.getExternalCacheDir(), imgName + ".png");
        if (file.exists()) {
            if (bitmap.getByteCount()==(BitmapFactory.decodeFile(file.getAbsolutePath()).getByteCount())) {
                return;
            }
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取cache中的缓存
     * @param context
     * @param imgName
     * @return
     */
    public static Bitmap getCacheBitmap(Context context,String imgName) {

        Bitmap bitmap = null;
        File file = new File(context.getExternalCacheDir(), imgName + ".png");
        if(file.exists()) {
            bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        }else {
            Log.e("BitmapUtils", "getCacheBitmap: 文件不存在");
        }

        return bitmap;

    }
}
