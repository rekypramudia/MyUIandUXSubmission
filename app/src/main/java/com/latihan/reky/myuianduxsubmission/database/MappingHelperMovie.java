package com.latihan.reky.myuianduxsubmission.database;


import android.database.Cursor;


import com.latihan.reky.myuianduxsubmission.Movie;

import java.util.ArrayList;

public class MappingHelperMovie {


    public static ArrayList<Movie> mapCursorToArrayList(Cursor moviesCursor) {
        ArrayList<Movie> moviesList = new ArrayList<>();

        while (moviesCursor.moveToNext()) {
            int id = moviesCursor.getInt(moviesCursor.getColumnIndexOrThrow(DatabaseContractMovie.DatabaseMovie._ID));
            String imageMovie = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContractMovie.DatabaseMovie.COLUMN_IMAGE));
            String titleMovie = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContractMovie.DatabaseMovie.COLUMN_TITLE));
            String descMovie = moviesCursor.getString(moviesCursor.getColumnIndexOrThrow(DatabaseContractMovie.DatabaseMovie.COLUMN_DESC));
            moviesList.add(new Movie(imageMovie, titleMovie, descMovie));

        }
        return moviesList;
    }

}
