package com.deepshikha.hackathonapp.event;

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

public class ListEventsFragment extends Fragment implements Callbacks.ListEventCallback {

    RecyclerView eventsRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_events, container, false);
        eventsRecyclerView = view.findViewById(R.id.eventsRecyclerView);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        eventsRecyclerView.setAdapter(new EventAdapter(getContext(), new ArrayList<String>()));
        Toast.makeText(getContext(), "Fetching new event list", Toast.LENGTH_SHORT).show();
        NetworkManager.getInstance().getEvents(this);
        return view;
    }

    @Override
    public void listEvents(List<String> list) {
        eventsRecyclerView.setAdapter(new EventAdapter(getContext(), list));
    }
}
