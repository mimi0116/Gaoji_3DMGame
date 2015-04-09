package com.jucky.gaoji_3dmgame;

import android.content.Context;
import android.os.Environment;

import com.lidroid.xutils.BitmapUtils;

import java.io.File;

/**
 * Created by Administrator on 2015/3/11.
 */
public class BitmapHelper {
    private static BitmapUtils utils;

    public static void initUtils(Context context) {
        utils = new BitmapUtils(context, new File(Environment.getExternalStorageDirectory(), "3dmgame").getAbsolutePath(), 0.25f);
        utils.configDefaultLoadingImage(R.drawable.ic_launcher);
        utils.configDefaultLoadFailedImage(R.drawable.ic_launcher);
        utils.configDefaultBitmapMaxSize(100,100);
        utils.configDefaultCacheExpiry(1000*60*60*24);
        utils.configDefaultConnectTimeout(5000);
        utils.configDefaultReadTimeout(10000);
    }

    public static BitmapUtils getUtils() {
        return utils;
    }
}
