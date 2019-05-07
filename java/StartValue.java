package com.example.timeisgold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartValue extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_value);

        btn = findViewById(R.id.svBtn);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent setInt = new Intent(getApplicationContext(), TimerShow.class);
                startActivity(setInt);
            }
        });
    }
}
