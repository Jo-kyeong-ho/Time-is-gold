package com.timeisgold.toolbar;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.airbnb.lottie.LottieAnimationView;

public class TimerValue extends FragmentActivity {
    View view1;
    View view2;
    boolean dodo = true;
    TextView timestring;
    TextView moneystring;
    double money = 0.0;
    String timestr = "";
    Handler handler;
    Thread thread;
    boolean go=true;
    int time=0;
    String job;
    int timevalue;
    SQLiteDatabase db;
    int wage=0;
    int limit=0;
    Button button;

    @Override
    protected void onActivityResult(int reque, int result, Intent intent){
        super.onActivityResult(reque, result, intent);
        if(result == 2){
            time = intent.getIntExtra("time",0);
            go=true;
        }
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db=openOrCreateDatabase("money_datedb", Activity.MODE_PRIVATE,null);
        Cursor cursor=null;
        cursor=db.rawQuery("select * from temptable",null);
        cursor.moveToNext();
        int setting=cursor.getInt(2);
        if(setting==0) {
            try {
                cursor=null;
                int k = 0;
                cursor = db.rawQuery("select * from temptable", null);
                cursor.moveToNext();
                k = cursor.getInt(0);
                limit = cursor.getInt(1);
                cursor = null;
                cursor = db.rawQuery("select * from valuetable", null);
                for (int i = 0; ; i++) {
                    cursor.moveToNext();
                    if (i == k) {
                        wage = cursor.getInt(1);
                        break;
                    }
                }
            } catch (Exception e) {
            }
        }
        else{
            try {
                cursor=null;
                cursor = db.rawQuery("select * from temptable", null);
                cursor.moveToNext();
                wage = cursor.getInt(0);
                limit = cursor.getInt(1);
            } catch (Exception e) {
            }
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int tmptime=0;
        Intent intent3 = getIntent();
        tmptime = intent3.getIntExtra("time",0);
        money = intent3.getDoubleExtra("money",0.0);
        if(tmptime!=0){
            go=true;
            money = getIntent().getDoubleExtra("money",0.0);
            time=tmptime;
        }
        Log.d("confirm",tmptime + " " + money);
        setContentView(R.layout.timer_value);
        timestring = findViewById(R.id.edgeA);
        moneystring = findViewById(R.id.money);
        view1 = findViewById(R.id.backView1);
        view2 = findViewById(R.id.backView2);
        view1.setBackground(new ShapeDrawable(new OvalShape()));
        view2.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT >= 21) {
            view1.setClipToOutline(true);
            view2.setClipToOutline(true);
        }
        LottieAnimationView animationView = (LottieAnimationView)findViewById(R.id.animation_pig);
        animationView.playAnimation();
        animationView.loop(true);
        handler = new Handler();
        thread = new ourTimer();
        thread.start();
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    db.execSQL("update accounttable set count=" + money);
                    db.execSQL("update accounttable set time=" + time);
                    Intent intent = new Intent(TimerValue.this, result_value.class);
                    dodo=false;
                    startActivity(intent);
                }
                catch(Exception e){
                }
            }
        });
    }

    class ourTimer extends Thread{
        @Override
        public void run(){
            while(dodo){
                handler.post(new Runnable(){
                    @Override
                    public void run() {
                        timestr = "";
                        if (time > 144000) {
                            time = 0;
                        }
                        if (time < 36000) {
                            timestr = "0";
                            if (time < 3600) {
                                timestr = timestr + "0:";
                            } else
                                timestr = timestr + time / 3600 + ":";
                        } else {
                            timestr = time / 3600 + ":";
                        }
                        if ((time % 3600) < 600) {
                            timestr = timestr + "0";
                            if ((time % 3600) < 60) {
                                timestr = timestr + "0:";
                            } else {
                                timestr = timestr + (time % 3600) / 60 + ":";
                            }
                        } else {
                            timestr = timestr + (time % 3600) / 60 + ":";
                        }
                        if ((time % 60) < 10) {
                            timestr = timestr + "0" + (time % 60);
                        } else {
                            timestr = timestr + (time % 60);
                        }
                        timestring.setText(timestr);
                        moneystring.setText((int)money+" ￦");
                    }
                });
                try{
                    if(go==true) {
                        time = time + 1;
                        money = money+wage/3600.0;
                    }
                    if(time%limit==0&&go==true){
                        Intent intent = new Intent(TimerValue.this,time_popup.class);
                        intent.putExtra("time",time);
                        intent.putExtra("money",money);
                        finish();
                        startActivity(intent);
                        go=false;
                    }
                    Thread.sleep(1000);
                    timestr="";
                }
                catch (Exception e){}
            }
        }
    }
}
