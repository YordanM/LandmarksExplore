package com.example.landmarksexplore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private Button readLandmarkBtn;
    private ArrayList<Landmark> landmarks;
    private RecyclerView landmarksRV;

    @SuppressLint({"MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing our variables.
        readLandmarkBtn = findViewById(R.id.idBtnReadLandmark);

        try {
            landmarks = PlacesLocationService.LandmarkList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //listing our landmarks
        landmarksRV = (RecyclerView) findViewById(R.id.recycleView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        landmarksRV.setLayoutManager(linearLayoutManager);
        LandmarkRVAdapterAPI adapterAPI = new LandmarkRVAdapterAPI(MainActivity.this, landmarks);
        landmarksRV.setAdapter(adapterAPI);


        //with this we are going to ViewLandmarks activity to see all saved landmarks
        readLandmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // opening a new activity via a intent.
                Intent i = new Intent(MainActivity.this, ViewLandmarks.class);
                startActivity(i);
            }
        });
    }
}