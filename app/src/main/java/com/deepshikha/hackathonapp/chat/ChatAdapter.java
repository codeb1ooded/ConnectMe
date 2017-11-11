package com.deepshikha.hackathonapp.chat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import com.deepshikha.hackathonapp.R;

/**
 * Created by megha on 12/11/17.
 */

public class ChatAdapter extends BaseAdapter {

    private Context context;
    private List<ChatModel> messagesItems;

    public ChatAdapter(Context context, List<ChatModel> navDrawerItems) {
        this.context = context;
        this.messagesItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return messagesItems.size();
    }

    @Override
    public ChatModel getItem(int position) {
        return messagesItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatModel m = messagesItems.get(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (messagesItems.get(position).byme) {
            convertView = mInflater.inflate(R.layout.message_right, null);
        } else {
            convertView = mInflater.inflate(R.layout.message_left, null);
        }

        TextView txtMsg = (TextView) convertView.findViewById(R.id.txtMsg);
        txtMsg.setText(m.message + "\n");

        return convertView;
    }
}