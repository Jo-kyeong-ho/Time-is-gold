package com.timeisgold.toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;

public class database_fragment extends Fragment {
    FragmentPagerAdapter adapterFragPager;
    Fragment fg;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.database_fragment, container,false);

        ImageButton DayButton = (ImageButton) rootView.findViewById(R.id.goDb);
        DayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fg = new daily_db_fragment();
                replaceFragment(fg);
            }
        });
        return rootView;
    }
    private void replaceFragment(Fragment newFragment) {
        FragmentTransaction transection = getFragmentManager().beginTransaction();

        if (!newFragment.isAdded()) {
            try {
                getFragmentManager().beginTransaction();
                transection.replace(R.id.database_fragment_layout, newFragment);
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
