package com.timeisgold.toolbar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
public class FirstPopup extends Activity {
    String job;
    int time;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_popup);
    }
    // 확인 버튼 클릭
    public void mOnClose(View v) { // 데이터 전달하기
        Intent intent2 = new Intent(FirstPopup.this, TakePicture.class);
        startActivity(intent2);
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