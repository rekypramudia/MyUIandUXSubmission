package com.latihan.reky.myuianduxsubmission;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailActivityTvShowFav extends AppCompatActivity {

    public static final String EXTRA_NOTE = "extra_note";
    public static final String EXTRA_POSITION = "extra_position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show_fav);
    }
}
