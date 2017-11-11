package com.deepshikha.hackathonapp.recommendation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.deepshikha.hackathonapp.Callbacks;
import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.network.NetworkManager;

import java.util.ArrayList;
import java.util.List;

public class RecommendationActivity extends AppCompatActivity implements Callbacks.RecommendationCallback {


    RecyclerView recommendationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);
        recommendationsRecyclerView = findViewById(R.id.recommendationsRecyclerView);
        recommendationsRecyclerView.setHasFixedSize(true);
        recommendationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recommendationsRecyclerView.setAdapter(new RecommendationAdapter(this, new ArrayList<String>()));
        Toast.makeText(this, "Fetching new recommendations", Toast.LENGTH_SHORT).show();
        String username = getSharedPreferences("ConnectMe", MODE_PRIVATE).getString("username", "red");
        NetworkManager.getInstance().getRecommendations(username, this);
    }

    @Override
    public void recommendations(List<String> list) {
        recommendationsRecyclerView.setAdapter(new RecommendationAdapter(this, list));
    }
}
