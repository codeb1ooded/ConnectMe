package com.deepshikha.hackathonapp.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;
import com.deepshikha.hackathonapp.database.UsernameModel;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    RecyclerView recyclerView;
    SearchAdapter adapter;
    AbstractDBAdapter db;
    ArrayList<UsernameModel> results;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        db = new AbstractDBAdapter(getContext());
        String key = getArguments().getString("keyword");
        results = db.getSimilarUsers(key);

        adapter = new SearchAdapter(getContext(), results);

        recyclerView.setAdapter(adapter);
        return view;
    }
}
