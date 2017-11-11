package com.deepshikha.hackathonapp;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.deepshikha.hackathonapp.event.ListEventsFragment;
import com.deepshikha.hackathonapp.recommendation.RecommendationFragment;

public class MainFragment extends Fragment {

    FrameLayout frameLayout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events:
                    ListEventsFragment listEventsFragment = new ListEventsFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_frame_layout, listEventsFragment).commit();
                    return true;
                case R.id.navigation_dashboard:
                    DashboardFragment dashboardFragment = new DashboardFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_frame_layout, dashboardFragment).commit();
                    return true;
                case R.id.navigation_recommendations:
                    RecommendationFragment recommendationFragment = new RecommendationFragment();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_frame_layout, recommendationFragment).commit();
                    return true;
            }
            return false;
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        BottomNavigationView navigation = view.findViewById(R.id.navigation);
        frameLayout = view.findViewById(R.id.main_fragment_frame_layout);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        DashboardFragment dashboardFragment = new DashboardFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment_frame_layout, dashboardFragment).commit();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }
}
