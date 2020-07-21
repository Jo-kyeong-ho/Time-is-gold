package com.timeisgold.toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx
        .fragment
        .app
        .Fragment;
import com
        .airbnb
        .lottie
        .LottieAnimationView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
public class HomeFragment extends Fragment {
    TextView da;
    TextView ti;
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
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home, container, false);
        da = (TextView)rootView.findViewById(R.id.da);
        ti = (TextView)rootView.findViewById(R.id.ti);
        int time = getArguments().getInt("time");
        da.setText(year + "년  " + month + "월  " + day + "일");
        ti.setText(time + "초");
        LottieAnimationView animationView = (LottieAnimationView)rootView.findViewById(R.id.animation_view);
        animationView.playAnimation();
        animationView.setProgress(0.0001f);
        animationView.loop(true);
        return rootView;
    }
}