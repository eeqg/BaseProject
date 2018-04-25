package com.project.base.resource.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class ResourceUtil {
    public ResourceUtil() {
    }

    public static String getString(Context context, int id) {
        return context.getResources().getString(id);
    }

    public static float getDimension(Context context, int id) {
        return context.getResources().getDimension(id);
    }

    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    public static Drawable getDrawable(Context context, int id) {
        return context.getResources().getDrawable(id);
    }

    public static ColorStateList getColorStateList(Context context, int id) {
        return context.getResources().getColorStateList(id);
    }

    public static Drawable getDrawable(Context context, String resName) {
        return getDrawable(context, getResourcesIdByName(context, "drawable", resName));
    }

    public static int getDrawableId(Context context, String resName) {
        return getResourcesIdByName(context, "drawable", resName);
    }

    private static int getResourcesIdByName(Context context, String resourceType, String resourcesName) {
        Resources resources = context.getResources();
        int id = resources.getIdentifier(resourcesName, resourceType, context.getPackageName());
        if(id == 0) {
            LogUtils.e("", "读取不到资源文件【" + resourcesName + "】!");
        }

        return id;
    }

    public static String[] getStringArray(Context context, int arrayId) {
        return context.getResources().getStringArray(arrayId);
    }
}