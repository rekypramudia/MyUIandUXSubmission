package com.latihan.reky.myuianduxsubmission.database;

import android.provider.BaseColumns;

public class DatabaseContractShow {

    private DatabaseContractShow () {}

    public static final class DatabaseShow implements BaseColumns {
        public static final String TABLE_NAME = "show_data";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESC = "overview";
        public static final String COLUMN_IMAGE = "poster_path";
    }
}
