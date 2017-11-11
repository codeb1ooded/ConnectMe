package com.deepshikha.hackathonapp.event;

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

public class ListEventsActivity extends AppCompatActivity implements Callbacks.ListEventCallback {

    RecyclerView eventsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_events);
        eventsRecyclerView = findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        eventsRecyclerView.setAdapter(new EventAdapter(this, new ArrayList<String>()));
        Toast.makeText(this, "Fetching new event list", Toast.LENGTH_SHORT).show();
        NetworkManager.getInstance().getEvents(this);
    }

    @Override
    public void listEvents(List<String> list) {
        eventsRecyclerView.setAdapter(new EventAdapter(this, list));
    }
}
