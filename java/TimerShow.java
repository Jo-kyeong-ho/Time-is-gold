package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerShow extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_timer);

        Button incResult = findViewById(R.id.incBtn);
        Button decResult = findViewById(R.id.decBtn);

        incResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent incInt = new Intent(getApplicationContext(), MoneyIncrease.class);
                startActivity(incInt);
            }
        });
        decResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent decInt = new Intent(getApplicationContext(), MoneyDecrease.class);
                startActivity(decInt);
            }
        });
    }
}
