package com.ptlearnpoint.www.traintime.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ptlearnpoint.www.traintime.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        startMainActivity();
    }

    private void startMainActivity() {
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
