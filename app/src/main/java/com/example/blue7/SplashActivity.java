package com.example.blue7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

//hiding action bar
      // getSupportActionBar().hide();
        //noinspection deprecation

        //hide status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);


        TextView textView = findViewById(R.id.row_inamefb);
        textView.animate().translationX(1000).setDuration(1000).setStartDelay(2500);


        //navigation to next activity -> Welcome activity
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (
                    Exception e) {
                e.printStackTrace();
            }
            finally {
                Intent intent = new Intent(SplashActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish(); //splash activity will be erased from memory
            }
        });


    thread.start();
    }
}




