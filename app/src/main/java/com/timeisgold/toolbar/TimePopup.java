package com.timeisgold.toolbar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
public class TimePopup extends Activity {
    String job;
    Vibrator vibrator;
    int time;
    boolean exit = true;
    int time1;
    double money;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup_time);
        Intent intent = getIntent();
        time1 = intent.getIntExtra("time", 0);
        money = intent.getDoubleExtra("money", 0.0);
        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                400,
                3000,
                400,
                3000
        };
        vibrator.vibrate(pattern, 3);
    }
    // 확인 버튼 클릭
    public void mOnClose(View v) { // 데이터 전달하기
        vibrator.cancel();
        Intent intent2 = new Intent(TimePopup.this, Compare.class);
        intent2.putExtra("time", time1);
        intent2.putExtra("money", money);
        startActivity(intent2);
        finish();
    }
    @Override public boolean onTouchEvent(MotionEvent event) { // 바깥레이어 클릭시 안닫히게
        if (event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
    @Override public void onBackPressed() { // 안드로이드 백버튼 막기
        return;
    }
}