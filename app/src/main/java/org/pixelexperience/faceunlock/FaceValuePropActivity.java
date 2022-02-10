package org.pixelexperience.faceunlock;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import org.pixelexperience.faceunlock.util.Util;

import com.airbnb.lottie.LottieAnimationView;

public class FaceValuePropActivity extends Activity implements View.OnClickListener {
    private LottieAnimationView mVideo;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.face_value_prop);
        Button btnCancel = findViewById(R.id.vp_cancel);
        btnCancel.setOnClickListener(this);
        Button btnNext = findViewById(R.id.vp_next);
        btnNext.setOnClickListener(this);
        mVideo = (LottieAnimationView) findViewById(R.id.video);
        mVideo.setAnimation(Util.isNightModeEnabled(this) ? R.raw.video_value_prop_dark : R.raw.video_value_prop);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.vp_cancel) {
            setResult(0);
            finish();
        } else if (view.getId() == R.id.vp_next) {
            setResult(-1);
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mVideo.playAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mVideo.cancelAnimation();
    }
}
