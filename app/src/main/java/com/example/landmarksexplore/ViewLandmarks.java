package com.example.landmarksexplore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ViewLandmarks extends AppCompatActivity {

    // creating variables for our array list,
    // dbhandler, adapter and recycler view.
    private ArrayList<Landmark> landmarkArrayList;
    private DBHandler dbHandler;
    private LandmarkRVAdapter landmarkRVAdapter;
    private RecyclerView landmarksRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_landmarks);

        // initializing our all variables.
        landmarkArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ViewLandmarks.this);

        // getting our landmarks array
        // list from db handler class.
        landmarkArrayList = dbHandler.readLandmarks();

        // on below line passing our array lost to our adapter class.
        landmarkRVAdapter = new LandmarkRVAdapter(landmarkArrayList, ViewLandmarks.this);
        landmarksRV = findViewById(R.id.idRVLandmarks);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ViewLandmarks.this, RecyclerView.VERTICAL, false);
        landmarksRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        landmarksRV.setAdapter(landmarkRVAdapter);
    }
}
