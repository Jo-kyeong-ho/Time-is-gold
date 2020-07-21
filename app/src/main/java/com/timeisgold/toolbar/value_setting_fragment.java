package com.timeisgold.toolbar;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class value_setting_fragment extends Fragment {
    ArrayList<String> arrayList = new ArrayList<>();
    EditText nameE,valueE;
    String name,value;
    Button button;
    SQLiteDatabase db;
    String target;
    Button delete;
    public Spinner spinner;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(), "money_datedb", null, 1);
        // 쓰기 가능한 SQLiteDatabase 인스턴스 구함
        db = databaseHelper.getWritableDatabase();
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.value_setting_fragment, container, false);
        nameE=(EditText)rootView.findViewById(R.id.name);
        valueE=(EditText)rootView.findViewById(R.id.value);
        button=(Button)rootView.findViewById(R.id.nextbtn);
        spinner = rootView.findViewById(R.id.list_menu);
        delete = rootView.findViewById(R.id.deletebtn);
        Cursor cursor = null;
        try {
            String SQL="select workname, value from valuetable";
            cursor=db.rawQuery(SQL,null);
            int count=cursor.getCount();
            for(int i=0;i<count;i++){
                cursor.moveToNext();
                String work=cursor.getString(0);
                int value=cursor.getInt(1);
                arrayList.add(work+" - "+value+"원");
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                arrayList
        );

        spinner.setAdapter(listViewAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String k=arrayList.get(i);
                StringTokenizer st;
                st=new StringTokenizer(k," -");
                target=st.nextToken();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String SQL="delete from valuetable where workname='"+target+"'";
                Toast.makeText(getActivity(),target+"이(가) 삭제되었습니다.",Toast.LENGTH_LONG).show();
                db.execSQL(SQL);
                Intent finaly = new Intent(getActivity(),MainActivity.class);
                startActivity(finaly);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nameE.getText().toString();
                value=valueE.getText().toString();
                try{
                    int val=Integer.parseInt(value);
                    //홈으로 넘김
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