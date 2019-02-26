package com.duoshan.www.lib_common.utils;

import android.content.Context;

import androidx.core.content.ContextCompat;

/**
 * author:  zhouchaoxiang
 * date:    2019/2/15
 * explain:
 */
public class PixelUtils {
    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (density * dp + 0.5f);
    }

    public static int px2dp(Context context, int px) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (px / density + 0.5f);
    }
}
