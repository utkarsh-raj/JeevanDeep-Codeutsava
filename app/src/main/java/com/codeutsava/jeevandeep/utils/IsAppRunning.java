package com.codeutsava.jeevandeep.utils;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

public class IsAppRunning {

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null)
        {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals("com.codenicely.services.steelgroup")) {
                    return true;
                }
            }
        }
        return false;
    }

}
