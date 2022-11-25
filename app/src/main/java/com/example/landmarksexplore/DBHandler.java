package com.example.landmarksexplore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "landmarksdb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_NAME = "landmarks";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable id for our course duration column.
    private static final String LATITUDE_COL = "latitude";

    // below variable for our course description column.
    private static final String LONGITUDE_COL = "longitude";

    // below variable is for our course tracks column.
    private static final String ADDRESS_COL = "address";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + LATITUDE_COL + " TEXT,"
                + LONGITUDE_COL + " TEXT,"
                + ADDRESS_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new landmark to our sqlite database.
    public void addNewCourse(String landmarkName, String landmarkLatitude, String landmarkLongitude, String landmarkAddress) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, landmarkName);
        values.put(LATITUDE_COL, landmarkLatitude);
        values.put(LONGITUDE_COL, landmarkLongitude);
        values.put(ADDRESS_COL, landmarkAddress);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the landmarks.
    public ArrayList<LandmarkModal> readLandmarks() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorLandmarks = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<LandmarkModal> landmarkModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorLandmarks.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                landmarkModalArrayList.add(new LandmarkModal(cursorLandmarks.getString(1),
                        cursorLandmarks.getString(4),
                        cursorLandmarks.getString(2),
                        cursorLandmarks.getString(3)));
            } while (cursorLandmarks.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorLandmarks.close();
        return landmarkModalArrayList;
    }

    // below is the method for updating our landmarks
    public void updateLandmarks(String originalLandmarkName, String landmarkName, String landmarkLatitude,
                             String landmarkLongitude, String landmarkAddress) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, landmarkName);
        values.put(LATITUDE_COL, landmarkLatitude);
        values.put(LONGITUDE_COL, landmarkLongitude);
        values.put(ADDRESS_COL, landmarkAddress);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalLandmarkName});
        db.close();
    }

    // below is the method for deleting our landmark.
    public void deleteLandmark(String landmarkName) {

        // on below line we are creating
        // a variable to write our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are calling a method to delete our
        // landmark and we are comparing it with our landmark name.
        db.delete(TABLE_NAME, "name=?", new String[]{landmarkName});
        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
