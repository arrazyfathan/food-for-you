package com.example.foodforyou;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
            Window window = this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.orange_color));


            // Splash Screen
            Thread thread = new Thread() {
                public void run() {
                    try {
                        sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        startActivity(new Intent(SplashActivity.this, SignUp.class));
                        finish();
                    }
                }
            };
            thread.start();
    }
}
