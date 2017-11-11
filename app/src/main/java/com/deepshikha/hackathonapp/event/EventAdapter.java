package com.deepshikha.hackathonapp.event;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deepshikha.hackathonapp.Callbacks;
import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.network.NetworkManager;

import java.util.List;

/**
 * Created by deepshikha.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>
        implements Callbacks.RegistrationCallback {

    List<String> eventList;
    Context context;
    int images[] = new int[]{R.drawable.event_img1, R.drawable.event_img2, R.drawable.event_img3, R.drawable.event_img4, R.drawable.event_img5};

    public EventAdapter(Context context, List<String> eventList) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.event_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final EventViewHolder holder, final int position) {
        holder.eventName.setText(eventList.get(position));
        holder.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = context.getSharedPreferences("ConnectMe", Context.MODE_PRIVATE)
                        .getString("username", "red");
                Toast.makeText(context,
                        "Registering " + username + " on event: " + eventList.get(position), Toast.LENGTH_SHORT).show();
                NetworkManager.getInstance().register(username, eventList.get(position), EventAdapter.this);

            }
        });
        holder.eventImage.setImageResource(images[position%images.length]);
    }

    @Override
    public void registrationStatus(boolean status) {
        if (status) {
            Toast.makeText(context,
                    "Registered on event", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context,
                    "Registration failed for event", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public class EventViewHolder extends RecyclerView.ViewHolder {

        TextView eventName;
        ImageView registerButton;
        ImageView eventImage;

        public EventViewHolder(View itemView) {
            super(itemView);
            eventName = itemView.findViewById(R.id.eventNameTextView);
            registerButton = itemView.findViewById(R.id.registerButton);
            eventImage = itemView.findViewById(R.id.event_image);
        }
    }
}
