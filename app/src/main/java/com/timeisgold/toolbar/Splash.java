package com.timeisgold.toolbar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.ImageView;
import com
        .bumptech
        .glide
        .Glide;
import com
        .bumptech
        .glide
        .request
        .target
        .DrawableImageViewTarget;
public class Splash extends Activity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        ImageView iv = findViewById(R.id.iv);
        DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(iv);
        Glide.with(this) .load(R.raw.pigstart).into(iv);

        Handler hd = new Handler();
        hd.postDelayed(new splashhandler(), 3000);
    }
    private class splashhandler implements Runnable {
        public void run() {
            startActivity(new Intent(getApplication(), Tutorial.class));
            Splash.this.finish();
        }
    }
    @Override public void onBackPressed() {}
}