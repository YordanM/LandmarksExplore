package com.example.landmarksexplore;

public class Landmark {
    public Landmark (String id, String name, String latitude, String longitude, String address)
    {
        this.Id = id;
        this.Name = name;
        this.Latitude = latitude;
        this.Longitude = longitude;
        this.Address = address;
    }

    public Landmark()
    {

    }

    public String Id;
    public String Name;
    public String Latitude;
    public String Longitude;
    public String Address;
}
