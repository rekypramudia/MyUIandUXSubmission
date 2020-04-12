package com.latihan.reky.myuianduxsubmission.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.latihan.reky.myuianduxsubmission.Movie;

import static android.provider.BaseColumns._ID;
import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.DatabaseMovie.COLUMN_IDMOVIE;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.DatabaseMovie.TABLE_NAME;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.DatabaseMovie.COLUMN_IMAGE;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.DatabaseMovie.COLUMN_TITLE;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractMovie.DatabaseMovie.COLUMN_DESC;


public class NoteHelperMovie {

    public static final String DATABASE_TABLE = TABLE_NAME;
    private static DatabaseHelperMovie databaseHelperMovie;
    private static NoteHelperMovie INSTANCE;

    private static SQLiteDatabase databaseMovie;

    private NoteHelperMovie (Context context) {
        databaseHelperMovie = new DatabaseHelperMovie(context);
    }

    public static NoteHelperMovie getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NoteHelperMovie(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        databaseMovie = databaseHelperMovie.getWritableDatabase();
    }

    public void close() {
        databaseHelperMovie.close();

        if (databaseMovie.isOpen())
            databaseMovie.close();

    }

    public Cursor queryAll() {
        return databaseMovie.query(
                DATABASE_TABLE,
                null,
                null,
                null,
                null,
                null,
                _ID + " ASC");
    }

    public Cursor queryById(String id) {
        return databaseMovie.query(DATABASE_TABLE, null
        , _ID + " = ?"
        , new String[]{id}
        ,null
        , null
        ,null
        ,null);
    }

    public long insert(Movie movie) {
       ContentValues values = new ContentValues();
       values.put(COLUMN_IDMOVIE, movie.getId());
       values.put(COLUMN_IMAGE, movie.getImgPhoto());
       values.put(COLUMN_TITLE, movie.getTxtTitle());
       values.put(COLUMN_DESC, movie.getTxtDesc());

        return databaseMovie.insert(DATABASE_TABLE, null, values);
    }

    public int uodate(String id, ContentValues values) {
        return databaseMovie.update(DATABASE_TABLE, values, _ID +" = ?", new String[]{id});
    }

    public int deleteById(String id) {
        return databaseMovie.delete(DATABASE_TABLE, _ID + " = ?", new String[]{id});
    }

    public boolean tapMovie(String id) {
        databaseMovie = databaseHelperMovie.getWritableDatabase();
        String chooseString = "SELECT * FROM " + TABLE_NAME  + " WHERE " + COLUMN_IMAGE + " =? ";
        Cursor cursor = databaseMovie.rawQuery(chooseString, new String[]{id});
        boolean tapMovie = false;
        if (cursor.moveToFirst()) {
            tapMovie = true;
            int count = 0;
            while (cursor.moveToNext()) {
                count++;
            }
            Log.d(TAG, String.format("%d records found", count));
        }
        cursor.close();
        return tapMovie;

    }
}
