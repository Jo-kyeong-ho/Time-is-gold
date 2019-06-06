package com.timeisgold.toolbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class daily_db_fragment extends Fragment {
    SQLiteDatabase db;
    EditText start,end;
    TextView item,timexml;
    int count=0;
    int time=0;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.daily_db_fragment, container, false);
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(), "money_datedb", null, 1);
        db = databaseHelper.getWritableDatabase();
        start=(EditText)getActivity().findViewById(R.id.startedit);
        end=(EditText)getActivity().findViewById(R.id.endedit);
        String startS,endS;
        startS=start.getText().toString();
        endS=end.getText().toString();
        if(startS.length()==8 &&endS.length()==8) {
            String yearE1 = startS.substring(0, 4);
            String yearE2 = endS.substring(0, 4);
            String monthE1 = startS.substring(4, 6);
            String monthE2 = endS.substring(4, 6);
            String dayE1 = startS.substring(6, 8);
            String dayE2 = endS.substring(6, 8);
            try {
                int year1 = Integer.parseInt(yearE1);
                int month1 = Integer.parseInt(monthE1);
                int day1 = Integer.parseInt(dayE1);
                int year2 = Integer.parseInt(yearE2);
                int month2 = Integer.parseInt(monthE2);
                int day2 = Integer.parseInt(dayE2);
                if (year1 == year2 && month1 == month2) {
                    if (day1 > day2) {
                        Toast.makeText(getActivity(), "잘못된 입력입니다", Toast.LENGTH_LONG).show();
                    } else {
                        String SQL = "select money, time from moneytable where year=" + year1 + " and month=" + month1 + " and day >=" + day1 + " and day <=" + day2;
                        Cursor cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                            Toast.makeText(getActivity(), "데이터가 없습니다", Toast.LENGTH_LONG).show();
                        }
                    }
                } else if (year1 == year2 && month1 != month2) {
                    if (month1 > month2) {
                        Toast.makeText(getActivity(), "잘못된 입력입니다", Toast.LENGTH_LONG).show();
                    } else {
                        String SQL = "select money, time from moneytable where year=" + year1 + " and month=" + month1 + " and day>=" + day1;
                        Cursor cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }
                        SQL = "select money, time from moneytable where year=" + year1 + " and month >" + month1 + " and month < " + month2;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }

                        SQL = "select money, time from moneytable where year=" + year1 + " and month =" + month2 + " and day <= " + day2;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }
                    }
                } else {
                    if (year1 > year2) {
                        Toast.makeText(getActivity(), "잘못된 입력입니다", Toast.LENGTH_LONG).show();
                    } else {
                        String SQL = "select money, time from moneytable where year=" + year1 + " and month >" + month1;
                        Cursor cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }

                        SQL = "select money, time from moneytable where year=" + year1 + " and month = " + month1 + " and day >= " + day1;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }

                        SQL = "select money, time from moneytable where year>" + year1 + " and year < " + year2;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }
                        SQL = "select money, time from moneytable where year=" + year2 + " and month <" + month2;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }

                        SQL = "select money, time from moneytable where year=" + year2 + " and month =" + month2 + " and day <= " + day2;
                        cursor = null;
                        try {
                            cursor = db.rawQuery(SQL, null);
                            int number = cursor.getCount();
                            for (int i = 0; i < number; i++) {
                                cursor.moveToNext();
                                count = count + cursor.getInt(0);
                                time = time + cursor.getInt(1);
                            }
                        } catch (Exception e) {
                        }
                    }
                }
                item = (TextView) rootView.findViewById(R.id.item);
                timexml = (TextView) rootView.findViewById(R.id.price);
                item.setText(count + "원");
                timexml.setText(time + "초");
            } catch (Exception E) {
                Toast.makeText(getActivity(), "잘못된 입력입니다", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(getActivity(), "8자리숫자를 입력하세요", Toast.LENGTH_LONG).show();
        }
        return rootView;

    }
}
