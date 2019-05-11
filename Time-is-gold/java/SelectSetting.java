package com.example.timeisgold;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectSetting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_select);

        Button valueSet = findViewById(R.id.valueSelBtn);
        Button wasteSet = findViewById(R.id.wasteSelBtn);

        valueSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent valueSetInt = new Intent(getApplicationContext(), ValueInsert.class);
                startActivity(valueSetInt);
            }
        });
        wasteSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent wasteSetInt = new Intent(getApplicationContext(), WasteInsert.class);
                startActivity(wasteSetInt);
            }
        });
    }
}