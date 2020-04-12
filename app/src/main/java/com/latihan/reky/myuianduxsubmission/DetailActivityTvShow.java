package com.latihan.reky.myuianduxsubmission;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailActivityTvShow extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_tv";
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tv_show);

        progressBar = findViewById(R.id.progressBarTvShow);

        TvShow tvShow = getIntent().getParcelableExtra(EXTRA_TV);

        TextView titleTvShowDetail = findViewById(R.id.tv_detail_title_tvshow);
        TextView descTvShowDetail = findViewById(R.id.tv_detail_desc_tvshow);
        ImageView tvView = findViewById(R.id.img_tvshow_detail);

        progressBar.setVisibility(View.VISIBLE);

        titleTvShowDetail.setText(tvShow.getTitleName());
        descTvShowDetail.setText(tvShow.getDescName());
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ tvShow.getImgTv())
                .fit().centerInside()
                .into(tvView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        if (progressBar != null) {
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();

                    }
                });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favorite, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_love:
                break;
            case android.R.id.home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
