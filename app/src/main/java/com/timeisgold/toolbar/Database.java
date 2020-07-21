package com.timeisgold.toolbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.pm10.library.CircleIndicator;

public class Database extends AppCompatActivity {
    MyPagerAdapter adapterViewPager;
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        ViewPager vpPager = (ViewPager) findViewById(R.id.viewPager);
        adapterViewPager = new MyPagerAdapter(this);
        vpPager.setAdapter(adapterViewPager);

        CircleIndicator circleIndicator = (CircleIndicator) findViewById(R.id.circle_indicator);
    }
}
