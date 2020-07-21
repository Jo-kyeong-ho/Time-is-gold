package com.timeisgold.toolbar;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

public class restPage extends AppCompatActivity {
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rest_page);

        ImageView iv = (ImageView)findViewById(R.id.sleeping);
        DrawableImageViewTarget imageViewTarget = new DrawableImageViewTarget(iv);

        Glide.with(this).load(R.raw.sleep).into(imageViewTarget);

    }
}
