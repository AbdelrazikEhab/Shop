package com.example.souqcom;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends Activity {

    final static int SPLASH_TIME = 3000;
    Animation animation;
    ImageView v1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        v1=findViewById(R.id.tv);

        animation= AnimationUtils.loadAnimation(this,R.anim.blinking_animation);
        v1.startAnimation(animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent home=new Intent(MainActivity.this, Login.class);
                startActivity(home);
                finish();
            }
        },SPLASH_TIME);

    }


}
//https://github.com/RayNjire/Android-Object-Animation/tree/master/app/src/main/res/anim