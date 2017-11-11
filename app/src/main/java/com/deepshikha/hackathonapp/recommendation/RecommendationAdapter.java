package com.deepshikha.hackathonapp.recommendation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;

import java.util.List;

/**
 * Created by deepshikha .
 */

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.RecommendationViewHolder> {

    List<String> recommendationList;
    Context context;
    AbstractDBAdapter db;

    public RecommendationAdapter(Context context, List<String> recommendationList) {
        this.context = context;
        this.recommendationList = recommendationList;
        db = new AbstractDBAdapter(context);
    }

    @Override
    public RecommendationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendationViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.recommendation_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final RecommendationViewHolder holder, final int position) {
        holder.personName.setText(recommendationList.get(position));
        holder.connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        "Connect invite sent with the tag", Toast.LENGTH_SHORT).show();
                holder.connectButton.setText("Invite sent");
                holder.connectButton.setTextSize(15.0f);
                holder.connectButton.setEnabled(false);
                holder.tagEditText.setEnabled(false);
                db.insertLabel(recommendationList.get(position), holder.tagEditText.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return recommendationList.size();
    }


    public class RecommendationViewHolder extends RecyclerView.ViewHolder {

        TextView personName;
        EditText tagEditText;
        Button connectButton;

        public RecommendationViewHolder(View itemView) {
            super(itemView);
            personName = itemView.findViewById(R.id.personNameTextView);
            tagEditText = itemView.findViewById(R.id.tagEditText);
            connectButton = itemView.findViewById(R.id.connectButton);
        }
    }
}
