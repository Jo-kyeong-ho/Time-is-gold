package com.example.newtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class GetPicture extends AppCompatActivity {
    Button btn;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_picture);

        btn = findViewById(R.id.nextBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wasteInt = new Intent(getApplicationContext(), TimerShowValue.class);
                startActivity(wasteInt);
            }
        });
    }
}
