package com.deepshikha.hackathonapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepshikha.hackathonapp.database.AbstractDBAdapter;
import com.deepshikha.hackathonapp.search.SearchAdapter;


/**
 * Created by megha on 11/11/17.
 */

public class DashboardFragment extends Fragment {

    RecyclerView recyclerView;
    AbstractDBAdapter db;
    SearchAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = view.findViewById(R.id.connections_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        db = new AbstractDBAdapter(getContext());
        recyclerView.setAdapter(new SearchAdapter(getContext(), db.getAllUsers()));
        return view;
    }

}
