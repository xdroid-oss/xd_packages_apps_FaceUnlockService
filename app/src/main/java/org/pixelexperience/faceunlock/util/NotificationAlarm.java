package org.pixelexperience.faceunlock.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

public class NotificationAlarm {
    private static final String NOTIFICATION_PROP_ALARM = "org.pixelexperience.faceunlock.NOTIFICATION_PROP_ALARM";
    private static final String TAG = NotificationAlarm.class.getSimpleName();
    private static NotificationAlarm mInstance;
    private final AlarmManager mAlarmManager;

    private NotificationAlarm(Context context) {
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }

    public static synchronized NotificationAlarm getInstance(Context context) {
        NotificationAlarm notificationAlarm;
        synchronized (NotificationAlarm.class) {
            if (mInstance == null) {
                NotificationAlarm notificationAlarm2 = new NotificationAlarm(context);
                mInstance = notificationAlarm2;
                notificationAlarm2.setAlarm(context);
            }
            notificationAlarm = mInstance;
        }
        return notificationAlarm;
    }

    public void init() {
    }

    private void setAlarm(Context context) {
        if (Util.DEBUG) {
            Log.d(TAG, "setup Alarm");
        }
        Intent intent = new Intent();
        intent.setClass(context, NotiAlarmReceiver.class);
        intent.setAction(NOTIFICATION_PROP_ALARM);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        mAlarmManager.cancel(broadcast);
        mAlarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, getNexHourInMs(), broadcast);
    }

    private long getNexHourInMs() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(System.currentTimeMillis());
        instance.add(10, 1);
        return instance.getTimeInMillis();
    }

    public static class NotiAlarmReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NotificationAlarm.NOTIFICATION_PROP_ALARM)) {
                if (Util.DEBUG) {
                    Log.d(NotificationAlarm.TAG, "set 'face_notification_alarm_reach' to true");
                }
                new SharedUtil(context).saveBooleanValue(NotificationUtils.FACE_NOTIFICATION_ALARM_READY, true);
            }
        }
    }
}
