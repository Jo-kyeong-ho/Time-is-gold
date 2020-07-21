package com.timeisgold.toolbar;
import android.Manifest;
import android.app.Activity;
import android
        .content
        .pm
        .PackageManager;
import android.database.Cursor;
import android
        .database
        .sqlite
        .SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx
        .appcompat
        .app
        .ActionBarDrawerToggle;
import androidx
        .appcompat
        .app
        .AppCompatActivity;
import androidx
        .appcompat
        .widget
        .Toolbar;
import androidx
        .core
        .app
        .ActivityCompat;
import androidx
        .core
        .content
        .ContextCompat;
import androidx
        .core
        .view
        .GravityCompat;
import androidx
        .drawerlayout
        .widget
        .DrawerLayout;
import androidx
        .fragment
        .app
        .Fragment;
import androidx
        .fragment
        .app
        .FragmentTransaction;
import com
        .google
        .android
        .material
        .navigation
        .NavigationView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;
    SQLiteDatabase dbob;
    int year;
    int month;
    int day;
    String yearS;
    String monthS;
    String dayS;
    private final int MY_PERMISSIONS_REQUEST = 1001;
    private static String[] permission = {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private boolean checkPermissions() {
        int result;
        List < String > permissionList = new ArrayList<>();
        for (String pm : permission) {
            result = ContextCompat.checkSelfPermission(this, pm);
            if (result != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(pm);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), MY_PERMISSIONS_REQUEST);
            return false;
        }
        return true;
    }
    @Override public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST:
            {
                if (grantResults.length > 0) {
                    for (int i = 0; i < permissions.length; i ++) {
                        if (permissions[i].equals(this.permission[i])) {
                            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                                showToast_PermissionDeny();
                            }
                        }
                    }
                } else {
                    showToast_PermissionDeny();
                }
                return;
            }
        }
    }
    private void showToast_PermissionDeny() {
        Toast.makeText(this, "권한 요청에 동의 해주셔야 이용 가능합니다. 설정에서 권한 허용 하시기 바랍니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermissions();
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        String date_up = mFormat.format(date);
        StringTokenizer st = new StringTokenizer(date_up, "-  ");
        yearS = st.nextToken();
        monthS = st.nextToken();
        dayS = st.nextToken();
        year = Integer.parseInt(yearS);
        month = Integer.parseInt(monthS);
        day = Integer.parseInt(dayS);
        dbob = openOrCreateDatabase("money_datedb", Activity.MODE_PRIVATE, null);
        try { // 변경
            dbob.execSQL("create table accounttable (count integer, time integer);");
            dbob.execSQL("insert into accounttable(count, time) values (0, 0)");
            dbob.execSQL("create table temptable (count integer, time integer, setting integer);");
            dbob.execSQL("insert into temptable(count, time, setting) values (0, 0, 0)");
            dbob.execSQL("create table moneytable (money integer, year integer, month integer, day integer, time integer);");
            dbob.execSQL("create table valuetable(workname varchar(30),value integer);");
            dbob.execSQL("insert into valuetable(workname, value) values ('과제',8350)");
            dbob.execSQL("insert into valuetable(workname, value) values ('운동(헬스)',5000)");
            dbob.execSQL("insert into valuetable(workname, value) values ('개인공부',15000)");
            dbob.execSQL("insert into valuetable(workname, value) values ('독서',5000)");
            dbob.execSQL("insert into valuetable(workname, value) values ('봉사',10000)");
            dbob.execSQL("create table wastetable(workname varchar(30),value integer);");
            dbob.execSQL("insert into wastetable(workname, value) values ('스포츠',-3000)");
            dbob.execSQL("insert into wastetable(workname, value) values ('게임',-10000)");
            dbob.execSQL("insert into wastetable(workname, value) values ('낮잠',-5000)");
            dbob.execSQL("insert into wastetable(workname, value) values ('당구',-8000)");
            dbob.execSQL("insert into wastetable(workname, value) values ('술',-15000)");
        } catch (Exception e) {}
        String SQL = "select money, time from moneytable where year = " + year + " and month = " + month + " and day=" + day;
        Cursor cur = dbob.rawQuery(SQL, null);
        if (cur.getCount() == 0) {
            dbob.execSQL("insert into moneytable(money, year, month, day, time) values (0," + year + "," + month + "," + day + ",0)");
        }
        cur.close();
        cur = dbob.rawQuery(SQL, null);
        cur.moveToNext();
        int money = cur.getInt(0);
        int time = cur.getInt(1);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Time is Gold");
        fragment = new HomeFragment();
        Bundle bundle = new Bundle(1); // 파라미터는 전달할 데이터 개수
        bundle.putInt("money", money); // key , value
        bundle.putInt("time", time);
        fragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment_layout, fragment);
        ft.commit();
    }
    @Override public void onBackPressed() { // 안드로이드 백버튼 막기
        return;
    }
    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override public boolean onNavigationItemSelected(MenuItem item) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            String SQL = "select money, time from moneytable where year=" + year + " and month=" + month + " and day=" + day;
            Cursor cur = dbob.rawQuery(SQL, null);
            cur.moveToNext();
            int money = cur.getInt(0);
            int time = cur.getInt(1);
            fragment = new HomeFragment();
            Bundle bundle = new Bundle(2); // 파라미터는 전달할 데이터 개수
            bundle.putInt("money", money); // key , value
            bundle.putInt("time", time);
            fragment.setArguments(bundle);
        } else if (id == R.id.nav_timer) {
            fragment = new TimerFragment();
        } else if (id == R.id.nav_setting) {
            fragment = new SettingFragment();
        } else if (id == R.id.nav_db) {
            fragment = new DBFragment();
        }
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_layout, fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}