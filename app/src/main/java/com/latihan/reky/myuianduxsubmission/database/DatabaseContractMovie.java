package com.latihan.reky.myuianduxsubmission.database;

import android.provider.BaseColumns;

public class DatabaseContractMovie {

    private DatabaseContractMovie () {}

    public static final class DatabaseMovie implements BaseColumns {
        public static final String TABLE_NAME = "movie_data";
        public static final String COLUMN_IDMOVIE = "idMovie";
        public static final String COLUMN_IMAGE = "poster_path";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "description";
    }
}
