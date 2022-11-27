package com.example.landmarksexplore;

import android.os.StrictMode;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PlacesLocationService {
    public static ArrayList<Landmark> LandmarkList() throws IOException, JSONException {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ArrayList<Landmark> result =  new ArrayList<>();
        String str;
        JSONArray places;

        //getting out api link
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=42.136097%2C24.742168&radius=2000&type=hotel&key=AIzaSyCWatdWW15FDBuNpNXtkDuLU18RWJfpGt0";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();


        Response response = client.newCall(request).execute();

        if (response.isSuccessful())
        {
            str = response.body().string();
            JSONObject jsonObject = new JSONObject(str);
            places = jsonObject.getJSONArray("results");

            JSONObject place;
            JSONObject geometry;
            JSONObject location;
            String latitude;
            String longitude;

            String name;
            String id;
            String address;
            for (int i = 0; i < places.length(); i++)
            {
                place = places.getJSONObject(i);
                geometry = place.getJSONObject("geometry");
                location = geometry.getJSONObject("location");
                latitude = location.getString("lat");
                longitude = location.getString("lng");

                name = place.getString("name");
                id = place.getString("place_id");
                address = place.getString("vicinity");

                Landmark lm = new Landmark(name, latitude, longitude, address);

                result.add(lm);
            }
        }
        return result;
    }
}
