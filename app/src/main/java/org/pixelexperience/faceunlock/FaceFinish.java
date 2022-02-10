package org.pixelexperience.faceunlock;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import org.pixelexperience.faceunlock.util.Util;

public class FaceFinish extends Activity {
    private static final String TAG = FaceFinish.class.getSimpleName();

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.face_finish);
        final boolean enrollSuccess = getIntent().getBooleanExtra(SetupFaceIntroActivity.EXTRA_ENROLL_SUCCESS, true);
        Button buttonDone = findViewById(R.id.btn_done);
        buttonDone.setOnClickListener(view -> {
            setResult(enrollSuccess ? -1 : 0, null);
            finish();
        });
        Button buttonNext = findViewById(R.id.btn_next);
        buttonNext.setOnClickListener(view -> startFaceUpgrageFinishActivity());
    }

    @Override
    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1) {
            boolean enrollSuccess = getIntent().getBooleanExtra(SetupFaceIntroActivity.EXTRA_ENROLL_SUCCESS, true);
            setResult(enrollSuccess ? -1 : 0, null);
            if (Util.DEBUG) {
                Log.i(TAG, "result: " + enrollSuccess);
            }
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        setResult(-1);
        finish();
    }

    private void startFaceUpgrageFinishActivity() {
        startActivityForResult(new Intent(this, FaceUpgradeFinish.class), 1);
    }
}
