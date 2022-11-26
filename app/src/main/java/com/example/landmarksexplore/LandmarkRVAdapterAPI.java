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

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class LandmarkRVAdapterAPI extends RecyclerView.Adapter<LandmarkRVAdapterAPI.ViewHolder> {
    ArrayList<LandmarkModal> places;
    Context context;

    // Constructor for initialization
    public LandmarkRVAdapterAPI(Context context, ArrayList<LandmarkModal> places) {
        this.context = context;
        this.places = places;
    }

    @NonNull
    @Override
    public LandmarkRVAdapterAPI.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the Layout(Instantiates list_item.xml
        // layout file into View object)
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.landmark_rv_item, parent, false);

        // Passing view to ViewHolder
        LandmarkRVAdapterAPI.ViewHolder viewHolder = new LandmarkRVAdapterAPI.ViewHolder(view);
        return viewHolder;
    }

    // Binding data to the into specified position
    @Override
    public void onBindViewHolder(@NonNull LandmarkRVAdapterAPI.ViewHolder holder, int position) {

        // on below line we are setting data
        // to our views of recycler view item.
        LandmarkModal modal = places.get(position);
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
        return places.size();
    }

    // Initializing the Views
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
