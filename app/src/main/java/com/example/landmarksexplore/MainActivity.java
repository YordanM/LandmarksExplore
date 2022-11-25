package com.example.landmarksexplore;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // creating variables for our edittext, button and dbhandler
    private EditText nameEdt, latitudeEdt, longitudeEdt, addressEdt;
    private Button addLandmarkBtn, readLandmarkBtn;
    private DBHandler dbHandler;
    private ArrayList<LandmarkModal> landmarks;
    private ListView listView;

    @SuppressLint({"MissingInflatedId", "MissingPermission"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializing all our variables.
        nameEdt = findViewById(R.id.idEdtName);
        latitudeEdt = findViewById(R.id.idEdtLatitude);
        longitudeEdt = findViewById(R.id.idEdtLongitude);
        addressEdt = findViewById(R.id.idEdtAddress);
        addLandmarkBtn = findViewById(R.id.idBtnAddLandmark);
        readLandmarkBtn = findViewById(R.id.idBtnReadLandmark);
        listView = findViewById(R.id.idListViewAPI);

        try {
            landmarks = PlacesLocation.LandmarkList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }



        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(MainActivity.this);

        // below line is to add on click listener for our add landmark button.
        addLandmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // below line is to get data from all edit text fields.
                String Name = nameEdt.getText().toString();
                String latitude = latitudeEdt.getText().toString();
                String longitude = longitudeEdt.getText().toString();
                String address = addressEdt.getText().toString();

                // validating if the text fields are empty or not.
                if (Name.isEmpty() || latitude.isEmpty() || longitude.isEmpty() || address.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter all the data...", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // landmark to sqlite data and pass all our values to it.
                dbHandler.addNewCourse(Name, longitude, address, latitude);

                // after adding the data we are displaying a toast message.
                Toast.makeText(MainActivity.this, "Landmark has been added.", Toast.LENGTH_SHORT).show();
                nameEdt.setText("");
                longitudeEdt.setText("");
                latitudeEdt.setText("");
                addressEdt.setText("");
            }
        });

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