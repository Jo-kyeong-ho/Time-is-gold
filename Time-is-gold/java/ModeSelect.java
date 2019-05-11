package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModeSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_mode);

        Button value = findViewById(R.id.valueBtn);
        Button waste = findViewById(R.id.wasteBtn);

        value.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent valueInt = new Intent(getApplicationContext(), TimerShow.class);
                startActivity(valueInt);
            }
        });
        waste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent wasteInt = new Intent(getApplicationContext(), TimerShow.class);
                startActivity(wasteInt);
            }
        });

    }
}
