package com.latihan.reky.myuianduxsubmission.favorite;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.latihan.reky.myuianduxsubmission.DetailActivityMovieFav;
import com.latihan.reky.myuianduxsubmission.Movie;
import com.latihan.reky.myuianduxsubmission.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieFavoriteAdapter extends RecyclerView.Adapter<MovieFavoriteAdapter.MovieFavoriteHolder> {
    private ArrayList<Movie> listNoteMovie = new ArrayList<>();
    private Context mContext;

    public MovieFavoriteAdapter(ArrayList<Movie> listNoteMovie, Context mContext) {
        this.listNoteMovie = listNoteMovie;
        this.mContext = mContext;
    }

    public ArrayList<Movie> getListNoteMovie() {
        return listNoteMovie;
    }

    public void setListNoteMovie(ArrayList<Movie> listNoteMovie) {
        if (listNoteMovie.size() > 0) {
            this.listNoteMovie.clear();
        }
        this.listNoteMovie.addAll(listNoteMovie);

        notifyDataSetChanged();
    }

    public void addItem(Movie movie) {
        this.listNoteMovie.add(movie);
        notifyItemInserted(listNoteMovie.size() - 1);
    }

    public void updateItem(int position, Movie movie) {
        this.listNoteMovie.set(position, movie);
        notifyItemChanged(position, movie);
    }

    public void removeItem(int position) {
        this.listNoteMovie.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNoteMovie.size());
    }

    @NonNull
    @Override
    public MovieFavoriteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item_favorite, viewGroup,false);
        return new MovieFavoriteHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieFavoriteAdapter.MovieFavoriteHolder holder, final int position) {
        Movie movieItem= listNoteMovie.get(position);

        String imageMovFav = movieItem.getImgPhoto() ;
        String titleMovieFav = movieItem.getTxtTitle();
        String titleDescFav = movieItem.getTxtDesc();

        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ imageMovFav)
                .fit().centerInside()
                .into(holder.imgMovieFav);
        holder.tittleTextFav.setText(titleMovieFav);
        holder.descTextFav.setText(titleDescFav);
        holder.MovieDetailFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailMovieFav = new Intent(mContext, DetailActivityMovieFav.class);
                detailMovieFav.putExtra(DetailActivityMovieFav.EXTRA_POSITION, position);
                detailMovieFav.putExtra(DetailActivityMovieFav.EXTRA_NOTE, listNoteMovie.get(position));
                mContext.startActivity(detailMovieFav);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNoteMovie.size();
    }

    public static class MovieFavoriteHolder extends RecyclerView.ViewHolder {
        ConstraintLayout MovieDetailFav;
        ImageView imgMovieFav;
        TextView tittleTextFav;
        TextView descTextFav;

        public MovieFavoriteHolder(@NonNull View itemView) {
            super(itemView);
            imgMovieFav = itemView.findViewById(R.id.img_item_favorite);
            tittleTextFav = itemView.findViewById(R.id.tv_title_movie_favorite);
            descTextFav = itemView.findViewById(R.id.tv_description_movie_favorite);
            MovieDetailFav = itemView.findViewById(R.id.detail_favMovie_activity);
        }
    }
}
