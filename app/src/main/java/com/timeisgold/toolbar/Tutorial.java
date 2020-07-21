package com.timeisgold.toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import androidx
        .appcompat
        .app
        .AppCompatActivity;
import androidx
        .viewpager
        .widget
        .ViewPager;
public class Tutorial extends AppCompatActivity {
    com
            .timeisgold
            .toolbar
            .Adapter adapter;
    ViewPager viewPager;
    Button button;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.viewpager_tutorial);
        viewPager = (ViewPager)findViewById(R.id.view);
        adapter = new com
                .timeisgold
                .toolbar
                .Adapter(this);
        viewPager.setAdapter(adapter);
        button = findViewById(R.id.skip);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Tutorial.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}