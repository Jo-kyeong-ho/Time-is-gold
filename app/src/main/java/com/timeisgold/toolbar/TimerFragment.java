package com.timeisgold.toolbar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        .tabs
        .TabLayout;
public class TimerFragment extends Fragment {
    Fragment fragment = null;
    TabLayout mTabLayout;
    Fragment fg;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.fragment_timer, container, false);
        int num;
        mTabLayout = rootView.findViewById(R.id.layout_tab);
        fg = new TimerValueFragment();
        replaceFragment(fg);
        // mTabLayout.setSelected(false);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 0) {
                    fg = new TimerValueFragment();
                    replaceFragment(fg);
                } else {
                    fg = new TimerWasteFragment();
                    replaceFragment(fg);
                }
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    fg = new TimerValueFragment();
                    replaceFragment(fg);
                }
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if (pos == 0) {
                    fg = new TimerValueFragment();
                    replaceFragment(fg);
                } else {
                    fg = new TimerWasteFragment();
                    replaceFragment(fg);
                }
            }
        });
        return rootView;
    }
    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transection = getFragmentManager().beginTransaction();
        if (!newFragment.isAdded()) {
            try {
                getFragmentManager().beginTransaction();
                transection.replace(R.id.timer_fragment_layout, newFragment);
                transection.addToBackStack(null);
                transection.commit();
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            transection.show(newFragment);
        }
    }
}