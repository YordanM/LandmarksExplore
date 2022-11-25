package com.example.landmarksexplore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateLandmarkActivity extends AppCompatActivity {

    // variables for our edit text, button, strings and dbhandler class.
    private EditText landmarkNameEdt, landmarkLatitudeEdt, landmarkLongitudeEdt, landmarkAddressEdt;
    private Button updateLandmarkBtn, deleteCourseBtn;
    private DBHandler dbHandler;
    String landmarkName, landmarkLatitude, landmarkLongitude, landmarkAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_landmark);

        // initializing all our variables.
        landmarkNameEdt = findViewById(R.id.idEdtLandmarkName);
        landmarkLatitudeEdt = findViewById(R.id.idEdtLandmarkLatitude);
        landmarkLongitudeEdt = findViewById(R.id.idEdtLandmarkLongitude);
        landmarkAddressEdt = findViewById(R.id.idEdtLandmarkAddress);
        updateLandmarkBtn = findViewById(R.id.idBtnUpdateLandmark);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new DBHandler(UpdateLandmarkActivity.this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        landmarkName = getIntent().getStringExtra("name");
        landmarkLatitude = getIntent().getStringExtra("latitude");
        landmarkLongitude = getIntent().getStringExtra("longitude");
        landmarkAddress = getIntent().getStringExtra("address");

        // setting data to edit text
        // of our update activity.
        landmarkNameEdt.setText(landmarkName);
        landmarkAddressEdt.setText(landmarkLatitude);
        landmarkLatitudeEdt.setText(landmarkAddress);
        landmarkLongitudeEdt.setText(landmarkLongitude);

        // adding on click listener to our update landmark button.
        updateLandmarkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update landmark
                // method and passing all our edit text values.
                dbHandler.updateLandmarks(landmarkName, landmarkNameEdt.getText().toString(), landmarkAddressEdt.getText().toString(), landmarkLatitudeEdt.getText().toString(), landmarkLongitudeEdt.getText().toString());

                // displaying a toast message that our landmark has been updated.
                Toast.makeText(UpdateLandmarkActivity.this, "Landmark Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateLandmarkActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        // adding on click listener for delete button to delete our course.
        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteLandmark(landmarkName);
                Toast.makeText(UpdateLandmarkActivity.this, "Deleted the landmark", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateLandmarkActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
