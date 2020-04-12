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
import android.widget.Toast;

import com.latihan.reky.myuianduxsubmission.database.NoteHelperMovie;
import com.squareup.picasso.Picasso;


public class DetailActivityMovie extends AppCompatActivity {

    private ProgressBar progressBar;
    private NoteHelperMovie noteHelperMovie;
    public static final String EXTRA_MOVIE = "extra_movie";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        progressBar = findViewById(R.id.progressBarMovie);

        ImageView imageView = findViewById(R.id.img_movie_detail);
        TextView titleMovieDetail = findViewById(R.id.tv_detail_title_movie);
        TextView descMovieDetail = findViewById(R.id.tv_detail_desc_movie);

        Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
       /* final String chooseMovieId = Integer.toString(movie .getId());*/

        noteHelperMovie = NoteHelperMovie.getInstance(getApplicationContext());
        noteHelperMovie.open();



        progressBar.setVisibility(View.VISIBLE);

        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ movie.getImgPhoto())
                .fit().centerInside()
                .into(imageView, new com.squareup.picasso.Callback(){

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
        titleMovieDetail.setText(movie.getTxtTitle());
        descMovieDetail.setText(movie.getTxtDesc());

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }

      /*  if (noteHelperMovie.tapMovie(chooseMovieId)) {
            progressBar.setVisibility(View.VISIBLE);
        }*/


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
               Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
               long result = noteHelperMovie.insert(movie);
                if (result > 0) {
                    Toast.makeText(this, "Berhasil ditambahkan ke Favorite", Toast.LENGTH_SHORT).show();


                } else {
                    Toast.makeText(this, "Gagal ditambahkan ke Favorite", Toast.LENGTH_SHORT).show();
                }
                break;
            case android.R.id.home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
