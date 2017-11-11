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
import android.widget.GridView;
import android.widget.Toast;

import com.deepshikha.hackathonapp.Callbacks;
import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

public class RecommendationFragment extends Fragment implements Callbacks.RecommendationCallback {

    GridView gridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        gridView = view.findViewById(R.id.grid_view_recommendation);
        gridView.setAdapter(new RecommendationAdapter(getContext(), new ArrayList<String>()));
        Toast.makeText(getContext(), "Fetching new recommendations", Toast.LENGTH_SHORT).show();
        String username = getContext().getSharedPreferences("ConnectMe", Context.MODE_PRIVATE).getString("username", "red");
        NetworkManager.getInstance().getRecommendations(username, this);
        return view;
    }

    @Override
    public void recommendations(List<String> list) {
        gridView.setAdapter(new RecommendationAdapter(getContext(), list));
    }
}
