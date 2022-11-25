package com.example.landmarksexplore;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LandmarkAdapterAPI extends ArrayAdapter<LandmarkModal> {

    private Activity activity;
    private ArrayList<LandmarkModal> landmarkModalArrayList;
    private static LayoutInflater inflater = null;


    public LandmarkAdapterAPI(Activity activity, int textViewResourceId, ArrayList<LandmarkModal> landmarkModalArrayList) {
        super(activity, textViewResourceId, landmarkModalArrayList);

        try {
            this.activity = activity;
            this.landmarkModalArrayList = landmarkModalArrayList;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e){

        }

    }

    public static class ViewHolder {
        public TextView tVLandmarkName;
        public TextView tVLandmarkAddress;
        public TextView tVLandmarkLongitude;
        public TextView tVLandmarkLatitude;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                view = inflater.inflate(R.layout.landmark_rv_item, null);
                holder = new ViewHolder();

                holder.tVLandmarkName = (TextView) view.findViewById(R.id.idTVLandmarkName);
                holder.tVLandmarkLatitude = (TextView) view.findViewById(R.id.idTVLandmarkLatitude);
                holder.tVLandmarkLongitude = (TextView) view.findViewById(R.id.idTVLandmarkLongitude);
                holder.tVLandmarkAddress = (TextView) view.findViewById(R.id.idTVLandmarkAddress);

                view.setTag(holder);
            } else {
                holder = (ViewHolder) view.getTag();
            }

            holder.tVLandmarkName.setText(landmarkModalArrayList.get(position).getName());
            holder.tVLandmarkLatitude.setText(landmarkModalArrayList.get(position).getLatitude());
            holder.tVLandmarkLongitude.setText(landmarkModalArrayList.get(position).getLongitude());
            holder.tVLandmarkAddress.setText(landmarkModalArrayList.get(position).getAddress());

        } catch (Exception e) {

        }

        return view;
    }
}
