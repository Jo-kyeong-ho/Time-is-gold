package com.timeisgold.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;

public class setting_fragment extends Fragment {
    Fragment fragment = null;
    TabLayout mTabLayout;
    Fragment fg;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.setting_fragment, container, false);
        int num;
        mTabLayout =  rootView.findViewById(R.id.layout_tab);
        fg = new value_setting_fragment();
        replaceFragment(fg);
        //mTabLayout.setSelected(false);
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if(pos == 0){
                    fg = new value_setting_fragment();
                    replaceFragment(fg);
                }
                else{
                    fg = new waste_setting_fragment();
                    replaceFragment(fg);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0) {
                    fg = new value_setting_fragment();
                    replaceFragment(fg);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                if(pos == 0){
                    fg = new value_setting_fragment();
                    replaceFragment(fg);
                }
                else{
                    fg = new waste_setting_fragment();
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
                //FragmentTransaction transection =
                getFragmentManager().beginTransaction();
                transection.replace(R.id.setting_fragment_layout, newFragment);
                transection.addToBackStack(null);
                transection.commit();
            } catch (Exception e) {
            }
        } else {
            transection.show(newFragment);
        }
    }
}
