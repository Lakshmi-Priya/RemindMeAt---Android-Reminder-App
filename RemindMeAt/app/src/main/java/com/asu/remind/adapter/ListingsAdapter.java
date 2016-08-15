package com.asu.remind.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.asu.remind.R;
import com.asu.remind.model.ListingsModel;

import java.util.ArrayList;

/**
 * Created by asu on 15-Aug-16.
 */
public class ListingsAdapter extends RecyclerView.Adapter<ListingsAdapter.Viewholder> {


    ArrayList<ListingsModel> data;
    ListingsModel model;

    public ListingsAdapter(ArrayList<ListingsModel> data) {
        this.data = data;
    }

    public static class Viewholder extends RecyclerView.ViewHolder {

        public TextView eventText, timeAndDateText;

        public Viewholder(View itemView) {
            super(itemView);

            eventText = (TextView)itemView.findViewById(R.id.event);
            timeAndDateText = (TextView)itemView.findViewById(R.id.time_and_date);

        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listings_row, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

        model = data.get(position);

        holder.eventText.setText(model.getEvent());
        holder.timeAndDateText.setText("At "+model.getTime() +" On "+model.getDate());


    }

    @Override
    public int getItemCount() {
        return data.size();
    }



}
