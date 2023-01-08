package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

   private ImageView image;
   private  TextView view;

   Animation animationImage,animationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        image =findViewById(R.id.imageView1);
        view =findViewById(R.id.textView1);

        animationImage = AnimationUtils.loadAnimation(this,R.anim.image_animation);
        animationView = AnimationUtils.loadAnimation(this,R.anim.text_animation);

         image.setAnimation(animationImage);
         view.setAnimation(animationView);

        new CountDownTimer(4500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();

            }
        }.start();
    }
}