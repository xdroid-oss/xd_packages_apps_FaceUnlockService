package org.pixelexperience.faceunlock;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.pixelexperience.faceunlock.util.NotificationUtils;
import org.pixelexperience.faceunlock.util.Util;

public class FaceApplication extends Application {
    private static final String TAG = FaceApplication.class.getSimpleName();
    public static FaceApplication mApp;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (Util.DEBUG) {
                Log.d(FaceApplication.TAG, "mReceiver Received intent with action = " + action);
            }
            if (intent.getAction().equals(Intent.ACTION_USER_PRESENT)) {
                NotificationUtils.checkAndShowNotification(context);
            }
        }
    };

    public static FaceApplication getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        if (Util.DEBUG) {
            Log.d(TAG, "onCreate");
        }
        super.onCreate();
        mApp = this;
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_USER_PRESENT);
        intentFilter.setPriority(IntentFilter.SYSTEM_HIGH_PRIORITY);
        registerReceiver(mReceiver, intentFilter, null, null);
        getPackageManager().setComponentEnabledSetting(new ComponentName(this, SetupFaceIntroActivity.class),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
        Util.setFaceUnlockAvailable(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        if (Util.DEBUG) {
            Log.d(TAG, "onTerminate");
        }
        super.onTerminate();
    }

    public void postRunnable(Runnable runnable) {
        mHandler.post(runnable);
    }
}
