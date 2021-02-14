package org.pixelexperience.faceunlock.util;

import android.content.Context;
import android.util.Log;

public class Settings {
    private static final String PROPERTY_FACEUNLOCK_AVAILABLE = "property_faceunlock_available";
    private static final String TAG = Settings.class.getSimpleName();

    public static boolean isByPassLockScreenEnabled(Context context) {
        int isEnabled = android.provider.Settings.Secure.getInt(context.getContentResolver(), "face_unlock_dismisses_keyguard", 1);
        Log.d(TAG, "isByPassLockScreenEnabled: " + isEnabled);
        return isEnabled == 1;
    }

    public static void setByPassLockScreenEnabled(Context context, int i) {
        android.provider.Settings.Secure.putInt(context.getContentResolver(), "face_unlock_dismisses_keyguard", i);
        Log.d(TAG, "setByPassLockScreenEnabled: " + i);
    }

    public static void setFaceUnlockAvailable(Context context, int i) {
        SharedUtil sharedPrefUtil = new SharedUtil(context);
        sharedPrefUtil.saveIntValue(PROPERTY_FACEUNLOCK_AVAILABLE, i);
        Log.d(TAG, "setFaceUnlockAvailable: " + i);
    }

    public static boolean isFaceUnlockAvailable(Context context) {
        SharedUtil sharedPrefUtil = new SharedUtil(context);
        return sharedPrefUtil.getIntValueByKey(PROPERTY_FACEUNLOCK_AVAILABLE) == 1;
    }
}
