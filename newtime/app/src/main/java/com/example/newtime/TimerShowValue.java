package com.example.newtime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TimerShowValue extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_valuetimer);

        Button incResult = findViewById(R.id.incBtn);

        incResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent incInt = new Intent(getApplicationContext(), TakePicture.class);
                startActivity(incInt);
            }
        });
    }
}