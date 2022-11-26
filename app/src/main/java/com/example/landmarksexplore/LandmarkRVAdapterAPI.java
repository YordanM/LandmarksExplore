package com.example.landmarksexplore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

// Extends the Adapter class to RecyclerView.Adapter
// and implement the unimplemented methods
public class LandmarkRVAdapterAPI extends RecyclerView.Adapter<LandmarkRVAdapterAPI.ViewHolder> {
    ArrayList<Landmark> landmarkArrayList;
    Context context;
    static private DBHandler dbHandler;

    // Constructor for initialization
    public LandmarkRVAdapterAPI(Context context, ArrayList<Landmark> landmarkArrayList) {
        this.context = context;
        this.landmarkArrayList = landmarkArrayList;
        dbHandler = new DBHandler(context);
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
        Landmark modal = landmarkArrayList.get(position);
        holder.landmarkNameTV.setText(modal.getName());
        holder.landmarkLatitudeTV.setText(modal.getLatitude());
        holder.landmarkLongitudeTV.setText(modal.getLongitude());
        holder.landmarkAddressTV.setText(modal.getAddress());

        // below line is to add on click listener for our recycler view item.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are calling a method to add new
                // landmark to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(modal.getName(), modal.getLatitude(), modal.getLongitude(), modal.getAddress());

                // after adding the data we are displaying a toast message.
                Toast.makeText(context, "Landmark has been added.", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return landmarkArrayList.size();
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
