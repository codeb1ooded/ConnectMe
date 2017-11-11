package com.deepshikha.hackathonapp.event;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.deepshikha.hackathonapp.R;

public class CreateEventActivity extends AppCompatActivity {

    EditText eventName;
    EditText eventPlace;
    EditText eventDate;
    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        eventName = findViewById(R.id.eventNameEditText);
        eventPlace = findViewById(R.id.eventPlaceEditText);
        eventDate = findViewById(R.id.eventDateEditText);
        createEvent = findViewById(R.id.createEventButton);
        createEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = eventName.getText().toString();
                String place = eventPlace.getText().toString();
                String date = eventDate.getText().toString();
                Toast.makeText(CreateEventActivity.this, "Creating event", Toast.LENGTH_SHORT).show();
                Toast.makeText(CreateEventActivity.this, "Event created", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
