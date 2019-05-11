package com.example.timeisgold;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TimerShowWaste extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_wastetimer);

        Button decResult = findViewById(R.id.decBtn);

        decResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent decInt = new Intent(getApplicationContext(), WasteResult.class);
                startActivity(decInt);
            }
        });
    }
}
