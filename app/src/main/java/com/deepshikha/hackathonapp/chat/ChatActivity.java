package com.deepshikha.hackathonapp.chat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    AbstractDBAdapter db;
    EditText editText;
    ImageView imageView;
    ArrayList<ChatModel> listMessages;
    ChatAdapter adapter;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        username = getIntent().getStringExtra("userto");
        setTitle(username);
        ListView listMsg = findViewById(R.id.chat_list);
        editText = (EditText) findViewById(R.id.edittext_chatbox);
        imageView = findViewById(R.id.analyze_image_view);
        db = new AbstractDBAdapter(ChatActivity.this);
        listMessages = db.getMessages(username);
        adapter = new ChatAdapter(this, listMessages);
        listMsg.setAdapter(adapter);

        Button send = findViewById(R.id.button_chatbox_send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatModel message = new ChatModel();
                message.message = editText.getText().toString();
                editText.setText("");
                message.byme = true;
                editText.setText("");
                listMessages.add(message);
                db.enterMessage(username, true, message.message);
                adapter.notifyDataSetChanged();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ChatActivity.this, AnalyseChat.class);
                i.putExtra("userto", username);
                startActivity(i);
            }
        });

    }
}
