package com.latihan.reky.myuianduxsubmission.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.*;

import androidx.annotation.Nullable;

public class DatabaseHelperMovie extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "movie.db";
    public static final int DATABASE_VERSION = 1;


    public DatabaseHelperMovie(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DATABASE_MOVIE_TABLE = "CREATE TABLE " +
                DatabaseMovie.TABLE_NAME + " (" +
                DatabaseMovie._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseMovie.COLUMN_IDMOVIE + "TEXT NOT NULL, " +
                DatabaseMovie.COLUMN_IMAGE + "TEXT NOT NULL, " +
                DatabaseMovie.COLUMN_TITLE + " TEXT NOT NULL, " +
                DatabaseMovie.COLUMN_DESC + "TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_DATABASE_MOVIE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseMovie.TABLE_NAME);
        onCreate(db);

    }
}
