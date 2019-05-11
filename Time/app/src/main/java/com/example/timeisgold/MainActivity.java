package com.example.timeisgold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton start = findViewById(R.id.startBtn);
        ImageButton set = findViewById(R.id.setBtn);
        ImageButton db = findViewById(R.id.dbBtn);

        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent startInt = new Intent(getApplicationContext(), SelectStart.class);
                startActivity(startInt);
            }
        });
        set.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent setInt = new Intent(getApplicationContext(), SelectSetting.class);
                startActivity(setInt);
            }
        });
        db.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent dbInt = new Intent(getApplicationContext(), DBShow.class);
                startActivity(dbInt);
            }
        });

    }
}