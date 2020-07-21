package com.timeisgold.toolbar;
import android.database.Cursor;
import android
        .database
        .sqlite
        .SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx
        .fragment
        .app
        .Fragment;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
public class HomeInnerFragment extends Fragment { // TODO: Rename parameter arguments, choose names that match
    SQLiteDatabase db;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    long now = System.currentTimeMillis();
    Date date = new Date(now);
    String date_up = mFormat.format(date);
    StringTokenizer st = new StringTokenizer(date_up, "-  ");
    String yearS = st.nextToken();
    String monthS = st.nextToken();
    String dayS = st.nextToken();
    int year = Integer.parseInt(yearS);
    int month = Integer.parseInt(monthS);
    int day = Integer.parseInt(dayS);
    TextView mo;
    int money;
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity(), "money_datedb", null, 1);
        db = databaseHelper.getWritableDatabase();
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_innerhome, container, false);
        mo = (TextView)rootView.findViewById(R.id.mo);
        DBSearch();
        mo.setText(money + "￦");
        return rootView;
    }
    void DBSearch() {
        Cursor cursor = null;
        try {
            String SQL = "select money, time from moneytable where year=" + year + " and month=" + month + " and day=" + day;
            cursor = db.rawQuery(SQL, null);
            // cursor = db.query(tableName, null, null, null, null, null, null);
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    money = cursor.getInt(0);
                }
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}