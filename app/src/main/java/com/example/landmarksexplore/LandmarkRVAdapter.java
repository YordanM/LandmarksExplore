package com.example.landmarksexplore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LandmarkRVAdapter extends RecyclerView.Adapter<LandmarkRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<LandmarkModal> landmarkModalArrayList;
    private Context context;

    // constructor
    public LandmarkRVAdapter(ArrayList<LandmarkModal> landmarkModalArrayList, Context context) {
        this.landmarkModalArrayList = landmarkModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landmark_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        LandmarkModal modal = landmarkModalArrayList.get(position);
        holder.landmarkNameTV.setText(modal.getName());
        holder.landmarkLatitudeTV.setText(modal.getLatitude());
        holder.landmarkLongitudeTV.setText(modal.getLongitude());
        holder.landmarkAddressTV.setText(modal.getAddress());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateLandmarkActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getName());
                i.putExtra("latitude", modal.getLatitude());
                i.putExtra("longitude", modal.getLongitude());
                i.putExtra("address", modal.getAddress());

                // starting our activity.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return landmarkModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView landmarkNameTV, landmarkLatitudeTV, landmarkLongitudeTV, landmarkAddressTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            landmarkNameTV = itemView.findViewById(R.id.idTVLandmarkName);
            landmarkLatitudeTV = itemView.findViewById(R.id.idTVLandmarkLatitude);
            landmarkLongitudeTV = itemView.findViewById(R.id.idTVLandmarkLongitude);
            landmarkAddressTV = itemView.findViewById(R.id.idTVLandmarkAddress);
        }
    }
}
