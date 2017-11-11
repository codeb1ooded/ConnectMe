package com.deepshikha.hackathonapp.recommendation;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.deepshikha.hackathonapp.Callbacks;
import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

public class RecommendationFragment extends Fragment implements Callbacks.RecommendationCallback {

    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        recyclerView = view.findViewById(R.id.recommendationsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecommendationAdapter(getContext(), new ArrayList<String>()));
        Toast.makeText(getContext(), "Fetching new recommendations", Toast.LENGTH_SHORT).show();
        String username = getContext().getSharedPreferences("ConnectMe", Context.MODE_PRIVATE).getString("username", "red");
        NetworkManager.getInstance().getRecommendations(username, this);
        return view;
    }

    @Override
    public void recommendations(List<String> list) {
        recyclerView.setAdapter(new RecommendationAdapter(getContext(), list));
    }
}
