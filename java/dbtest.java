package com.example.termtest2;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class dbtest extends AppCompatActivity {
    SQLiteDatabase db;
    Button add;
    Button confirm;
    int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest);
        add=findViewById(R.id.add);
        confirm=findViewById(R.id.confirm);
        db=openOrCreateDatabase("testdb", Activity.MODE_PRIVATE,null);
        db.execSQL("drop table test");
        db.execSQL("create table test (id integer, money integer, year integer, month integer, date integer, evaluate varchar(20));");
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(number>2)
                {
                    Toast.makeText(getApplicationContext(),"database is full", Toast.LENGTH_LONG).show();
                    String k="countk  "+number;
                    Log.d("confirm1",k);

                }
                else {
                    db.execSQL("insert into test(id, money, year, month, date, evaluate) values (" +
                            number + ", " + "500, 2019, 5, 8, 'good');");
                    Log.d("confirm1", "insert ok");
                    number = number + 1;
                    String k = "count  " + number;
                    Log.d("confirm1", k);
                }
            }
        });
        //db.execSQL("update test set money=100 where date=8");
        confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String SQL="select * from test where date=8";
                Cursor c1=db.rawQuery(SQL,null);
                int recordCount=c1.getCount();
                String a;
                a=""+recordCount;
                Log.d("confirm1",a);
                for(int i=0;i<recordCount;i++)
                {
                    c1.moveToNext();
                    a=""+c1.getInt(0);
                    Log.d("confirm1",a);
                    a=""+c1.getInt(1);
                    Log.d("confirm1",a);
                    a=""+c1.getInt(2);
                    Log.d("confirm1",a);
                    a=""+c1.getInt(3);
                    Log.d("confirm1",a);
                    a=""+c1.getInt(4);
                    Log.d("confirm1",a);
                    a=""+c1.getString(5);
                    Log.d("confirm1",a);

                }
                c1.close();
                Log.d("confirm1","cursor finish");
            }
        });
    }
}
