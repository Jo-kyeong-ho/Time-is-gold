package com.timeisgold.toolbar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        ImageView iv = findViewById(R.id.iv);

        DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(iv);
        Glide.with(this).load(R.raw.pigstart).into(iv);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);

    }
    private class splashhandler implements Runnable{
        public void run(){
            startActivity(new Intent(getApplication(), MainActivity.class));
            Splash.this.finish();
        }
    }
    @Override
    public void onBackPressed() {
    }
}
