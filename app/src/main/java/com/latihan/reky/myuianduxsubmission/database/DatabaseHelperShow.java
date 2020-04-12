package com.latihan.reky.myuianduxsubmission.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.latihan.reky.myuianduxsubmission.database.DatabaseContractShow.*;

import androidx.annotation.Nullable;

public class DatabaseHelperShow extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "show.db";
    public static final int DATABASE_VERSION = 1;



    public DatabaseHelperShow(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DATABASE_SHOW_TABLE = "CREATE TABLE " +
                DatabaseShow.TABLE_NAME + " (" +
                DatabaseShow._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseShow.COLUMN_TITLE + "TEXT NOT NULL, " +
                DatabaseShow.COLUMN_DESC + "TEXT NOT NULL," +
                DatabaseShow.COLUMN_IMAGE  + "TEXT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_DATABASE_SHOW_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseShow.TABLE_NAME);
        onCreate(db);

    }
}
