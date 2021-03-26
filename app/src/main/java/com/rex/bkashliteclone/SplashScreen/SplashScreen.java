package com.rex.bkashliteclone.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rex.bkashliteclone.MainActivity;
import com.rex.bkashliteclone.R;

public class SplashScreen extends AppCompatActivity {

    private Animation topAnimation;
    private ImageView bkashlogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        bkashlogo = findViewById(R.id.imageView2);

        topAnimation = AnimationUtils.loadAnimation(this,R.anim.top_slideanimation);

        bkashlogo.setAnimation(topAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();

            }
        },5000);

    }
}