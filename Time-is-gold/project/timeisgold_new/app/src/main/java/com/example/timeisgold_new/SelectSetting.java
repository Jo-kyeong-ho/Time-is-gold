package com.example.timeisgold_new;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SelectSetting extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_setting);

        Button value = findViewById(R.id.valueBtn);
        Button waste = findViewById(R.id.wasteBtn);

        value.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent valueInt = new Intent(getApplicationContext(), SettingValue.class);
                startActivity(valueInt);
            }
        });
        waste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent wasteInt = new Intent(getApplicationContext(), SettingWaste.class);
                startActivity(wasteInt);
            }
        });
    }
}
