package com.photogridviewapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.photogridviewapp.R;

public class Splash extends AppCompatActivity {

    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        handler = new Handler();
        long delayMillis = 3000;
        runnable = new Runnable() {

            @Override
            public void run() {


                startActivity(new Intent(Splash.this, MainActivity.class));
                finish();
            }
        };


        handler.postDelayed(runnable, delayMillis);
    }

    @Override
    public void onBackPressed() {
        if (handler != null)
            handler.removeCallbacks(runnable);
        super.onBackPressed();
    }
}
