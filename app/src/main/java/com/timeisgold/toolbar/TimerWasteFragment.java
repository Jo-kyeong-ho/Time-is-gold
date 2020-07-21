package com.timeisgold.toolbar;
import android.content.Intent;
import android.database.Cursor;
import android
        .database
        .sqlite
        .SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import androidx
        .fragment
        .app
        .Fragment;
import java.util.ArrayList;
import java.util.StringTokenizer;
public class TimerWasteFragment extends Fragment {
    ArrayList < String > arrayList = new ArrayList<>();
    public Spinner spinner;
    public Button button;
    RadioButton value;
    RadioButton waste;
    EditText edit1;
    SQLiteDatabase db;
    int check1 = 0;
    int check2 = 0;
    int temp = 0;
    int timecount = 0;
    Button delete;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_timer_waste, container, false);
        value = rootView.findViewById(R.id.radio1);
        waste = rootView.findViewById(R.id.radio2);
        edit1 = rootView.findViewById(R.id.edit1);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(), "money_datedb", null, 1);
        db = databaseHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            String SQL = "select workname, value from wastetable";
            cursor = db.rawQuery(SQL, null);
            int count = cursor.getCount();
            for (int i = 0; i < count; i ++) {
                cursor.moveToNext();
                String work = cursor.getString(0);
                int value = cursor.getInt(1);
                arrayList.add(work + " - " + value + "원");
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        ArrayAdapter < String > listViewAdapter = new ArrayAdapter<>(getActivity(), android
                .R
                .layout
                .simple_spinner_dropdown_item, arrayList);
        spinner = rootView.findViewById(R.id.list_menu);
        value.setChecked(true);
        spinner.setEnabled(true);
        edit1.setEnabled(false);
        waste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setEnabled(false);
                edit1.setEnabled(true);
                check1 = 1;
                check2 = 0;
            }
        });
        value.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setEnabled(true);
                edit1.setEnabled(false);
                check1 = 0;
                check2 = 1;
            }
        });
        spinner.setAdapter(listViewAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView <?> adapterView, View view, int i, long l) {
                temp = i;
                String k = arrayList.get(i);
                StringTokenizer st;
                st = new StringTokenizer(k, "-");
                String target = st.nextToken();
            }
            @Override
            public void onNothingSelected(AdapterView <?> adapterView) {}
        });
        button = rootView.findViewById(R.id.nextbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1 == 0) {
                    db.execSQL("update temptable set count=" + temp);
                    db.execSQL("update temptable set setting=" + 0);
                    Intent intent = new Intent(getActivity(), TimerWaste.class);
                    startActivity(intent);
                } else {
                    try {
                        int wage = Integer.parseInt(edit1.getText().toString());
                        wage = wage - 2 * wage;
                        db.execSQL("update temptable set count=" + (
                                wage - 2 * wage
                        ));
                        db.execSQL("update temptable set setting=" + 1);
                        Intent intent = new Intent(getActivity(), TimerWaste.class);
                        startActivity(intent);
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), "숫자를 입력하세요", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        return rootView;
    }
}