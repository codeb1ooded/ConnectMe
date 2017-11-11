package com.deepshikha.hackathonapp.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;
import com.deepshikha.hackathonapp.database.UsernameModel;

import java.util.List;

/**
 * Created by megha on 11/11/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<UsernameModel> searchResult;
    Context context;
    AbstractDBAdapter db;

    public SearchAdapter(Context context, List<UsernameModel> searchResult) {
        this.context = context;
        this.searchResult = searchResult;
        db = new AbstractDBAdapter(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.username.setText(searchResult.get(position).username);
        holder.label.setText(searchResult.get(position).label);
    }

    @Override
    public int getItemCount() {
        return searchResult.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView username, label;

        public ViewHolder(View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.username);
            label = itemView.findViewById(R.id.label);
        }
    }

}
