package com.timeisgold.toolbar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android
        .database
        .sqlite
        .SQLiteDatabase;
import android
        .graphics
        .drawable
        .ShapeDrawable;
import android
        .graphics
        .drawable
        .shapes
        .OvalShape;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx
        .appcompat
        .app
        .AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
public class ResultValue extends AppCompatActivity {
    Button button;
    SQLiteDatabase db;
    TextView pricetext;
    View view1;
    View view2;
    int money;
    int time;
    int year;
    int month;
    int day;
    int sum_money;
    int sum_time;
    String yearS;
    String monthS;
    String dayS;
    @Override public void onBackPressed() { // 안드로이드 백버튼 막기
        return;
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_value);
        view1 = findViewById(R.id.backView1);
        view2 = findViewById(R.id.backView2);
        view1.setBackground(new ShapeDrawable(new OvalShape()));
        view2.setBackground(new ShapeDrawable(new OvalShape()));
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String date_up = mFormat.format(date);
        StringTokenizer st = new StringTokenizer(date_up, "-  ");
        yearS = st.nextToken();
        monthS = st.nextToken();
        dayS = st.nextToken();
        year = Integer.parseInt(yearS);
        month = Integer.parseInt(monthS);
        day = Integer.parseInt(dayS);
        pricetext = findViewById(R.id.pricetext);
        db = openOrCreateDatabase("money_datedb", Activity.MODE_PRIVATE, null);
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("select * from accounttable", null);
            cursor.moveToNext();
            money = cursor.getInt(0);
            time = cursor.getInt(1);
            cursor.close();
        } catch (Exception e) {}
        try {
            String SQL = "select money, time from moneytable where year = " + year + " and month = " + month + " and day=" + day;
            Cursor cur = db.rawQuery(SQL, null);
            cur.moveToNext();
            sum_money = cur.getInt(0);
            sum_time = cur.getInt(1);
            sum_money = sum_money + money;
            sum_time = sum_time + time;
            db.execSQL("update moneytable set money=" + sum_money + " where year = " + year + " and month =" + month + " and day=" + day);
            db.execSQL("update moneytable set time=" + sum_time + " where year = " + year + " and month =" + month + " and day=" + day);
        } catch (Exception e) {}
        pricetext.setText(money + "￦");
        button = findViewById(R.id.homebtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}