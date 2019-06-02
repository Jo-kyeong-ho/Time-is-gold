package com.timeisgold.toolbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class value_setting_fragment extends Fragment {
    EditText nameE,valueE;
    String name,value;
    Button button;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.value_setting_fragment, container, false);
        nameE=(EditText)rootView.findViewById(R.id.name);
        valueE=(EditText)rootView.findViewById(R.id.value);
        button=(Button)rootView.findViewById(R.id.nextbtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameE.getText().toString();
                value=valueE.getText().toString();
                try{
                    int val=Integer.parseInt(value);
                    //홈으로 넘김
                    DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(), "money_datedb", null, 1);
                    // 쓰기 가능한 SQLiteDatabase 인스턴스 구함
                    db = databaseHelper.getWritableDatabase();
                    String SQL="insert into valuetable(workname, value) values ('" + name+"',"+val+")";
                    db.execSQL(SQL);
                    Toast.makeText(getActivity(),"값이 들어갔습니다.",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
                catch(Exception e){
                    Toast.makeText(getActivity(),"돈에 숫자를 입력하세요",Toast.LENGTH_LONG);
                }
            }
        });
        return rootView;
    }
}