package com.example.mfl;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class veraa extends AppCompatActivity {
private static int SPLASH_SCREEN = 5000;



    Animation firstanim,lastanim;
ImageView image;
TextView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.veramain);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        firstanim = AnimationUtils.loadAnimation(this,R.anim.firstanim);
        lastanim = AnimationUtils.loadAnimation(this,R.anim.lastanim);

        image = findViewById(R.id.gf);
        logo = findViewById(R.id.pre);

        image.setAnimation(firstanim);
        logo.setAnimation(lastanim);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(veraa.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}
