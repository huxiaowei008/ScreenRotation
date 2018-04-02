package com.hxw.screenrotation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    private OrientationEventListener mOrientationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
        mOrientationListener = new OrientationEventListener(this) {
            @Override
            public void onOrientationChanged(int orientation) {
                Log.d(TAG, "onOrientationChanged: " + orientation);
                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN) {
                    return; // 手机平放时，检测不到有效的角度
                }
                // 只检测是否有四个角度的改变
                if (orientation > 350 || orientation < 10) {
                    // 0度：手机默认竖屏状态（home键在正下方）
                    Log.d(TAG, "下");
                } else if (orientation > 80 && orientation < 100) {
                    // 90度：手机顺时针旋转90度横屏（home建在左侧）
                    Log.d(TAG, "左");
                } else if (orientation > 170 && orientation < 190) {
                    // 180度：手机顺时针旋转180度竖屏（home键在上方）
                    Log.d(TAG, "上");
                } else if (orientation > 260 && orientation < 280) {
                    // 270度：手机顺时针旋转270度横屏，（home键在右侧）
                    Log.d(TAG, "右");
                }
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
        mOrientationListener.enable();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
        mOrientationListener.disable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                startActivity(new Intent(this, ManifestVerticalActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(this, ManifestHorizontalActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(this, DynamicVerticalActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, DynamicHorizontalActivity.class));
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        super.onConfigurationChanged(newConfig);
//        Log.d(TAG, "onConfigurationChanged");
//    }
}
