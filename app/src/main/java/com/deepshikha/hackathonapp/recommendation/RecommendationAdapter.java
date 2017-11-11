package com.deepshikha.hackathonapp.recommendation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deepshikha.hackathonapp.R;
import com.deepshikha.hackathonapp.database.AbstractDBAdapter;

import java.util.List;

/**
 * Created by deepshikha .
 */

public class RecommendationAdapter extends BaseAdapter {

    List<String> recommendationList;
    Context context;
    AbstractDBAdapter db;
    int images[] = new int[]{R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8};

    public RecommendationAdapter(Context context, List<String> recommendationList) {
        this.context = context;
        this.recommendationList = recommendationList;
        db = new AbstractDBAdapter(context);
    }

    @Override
    public int getCount() {
        return recommendationList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.recommendation_list_item, null);
            final ImageView image = gridView.findViewById(R.id.profileImage);
            final TextView personName = gridView.findViewById(R.id.personNameTextView);
            final EditText tagEditText = gridView.findViewById(R.id.tagEditText);
            final Button connectButton = gridView.findViewById(R.id.connectButton);
            image.setImageResource(images[position%images.length]);
            personName.setText(recommendationList.get(position));
            connectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Connect invite sent with the tag", Toast.LENGTH_SHORT).show();
                    connectButton.setText("Invite sent");
                    connectButton.setTextSize(15.0f);
                    connectButton.setEnabled(false);
                    tagEditText.setEnabled(false);
                    db.insertLabel(recommendationList.get(position), tagEditText.getText().toString());
                }
            });

        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }
}
