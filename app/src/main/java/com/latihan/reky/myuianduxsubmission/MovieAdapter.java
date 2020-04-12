package com.latihan.reky.myuianduxsubmission;

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


import com.squareup.picasso.Picasso;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context mContext;
    private List<Movie> listMovie;


    MovieAdapter(Context mContext,List<Movie> listMovie) {
        this.mContext = mContext;
        this.listMovie = listMovie;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_item, viewGroup,false);
        return new MovieViewHolder(view);

    }

    @Override
    public void onBindViewHolder (MovieViewHolder holder, final int position) {
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ listMovie.get(position).getImgPhoto())
                .fit().centerInside()
                .into(holder.imgMovie);
        holder.tittleText.setText(listMovie.get(position).getTxtTitle());
        holder.descText.setText(listMovie.get(position).getTxtDesc());


        holder.itemMovieDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivityMovie = new Intent(mContext,DetailActivityMovie.class);
                detailActivityMovie.putExtra(DetailActivityMovie.EXTRA_MOVIE, listMovie.get(position));
                mContext.startActivity(detailActivityMovie);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout itemMovieDetail;
        ImageView imgMovie;
        TextView tittleText;
        TextView descText;
        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            itemMovieDetail = itemView.findViewById(R.id.movie_detail);
            imgMovie =  itemView.findViewById(R.id.img_item_photo);
            tittleText = itemView.findViewById(R.id.tv_title_movie);
            descText = itemView.findViewById(R.id.tv_description_movie);


        }
    }
}
