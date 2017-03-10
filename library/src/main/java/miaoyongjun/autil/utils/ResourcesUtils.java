package miaoyongjun.autil.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;

/**
 * Created by miaoyongjun on 16/6/9.
 * 　　　　    　┃┫┫　┃┫┫
 * 　　　　    　┗┻┛　┗┻┛
 */
public class ResourcesUtils {
    public static Drawable getDrawable(Context context, int resId) {
        return getDrawable(context.getResources(), resId);
    }

    public static Drawable getDrawable(Context context, int resId, Theme theme) {
        return getDrawable(context.getResources(), resId, theme);
    }

    public static Drawable getDrawable(Resources resources, int resId) {
        return getDrawable(resources, resId, null);
    }

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static Drawable getDrawable(Resources resources, int resId, Theme theme) {
        if (VERSION.SDK_INT >= 21) {
            return resources.getDrawable(resId, theme);// heigh than leve21
        } else {
            return resources.getDrawable(resId);// small than leve21
        }
    }


    public static int getColor(Context context, int resId) {
        return getColor(context.getResources(), resId);
    }

    public static int getColor(Context context, int resId, Theme theme) {
        return getColor(context.getResources(), resId, theme);
    }

    public static int getColor(Resources resources, int resId) {
        return getColor(resources, resId, null);
    }

    public static int getColor(Resources resources, int resId, Theme theme) {
        if (VERSION.SDK_INT >= 22) {
            return resources.getColor(resId, theme);// heigh than leve21
        } else {
            return resources.getColor(resId);// small than leve21
        }
    }
}
