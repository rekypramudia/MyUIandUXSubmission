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

import com.latihan.reky.myuianduxsubmission.DetailActivityTvShowFav;
import com.latihan.reky.myuianduxsubmission.R;
import com.latihan.reky.myuianduxsubmission.TvShow;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TvFavoriteAdapter extends RecyclerView.Adapter<TvFavoriteAdapter.TvFavoriteHolder> {
    private ArrayList<TvShow> listNoteTv = new ArrayList<>();
    private Context aContext;

    public TvFavoriteAdapter(ArrayList<TvShow> listNoteTv, Context aContext) {
        this.listNoteTv = listNoteTv;
        this.aContext = aContext;
    }

    public ArrayList<TvShow> getListNoteTv() {
        return listNoteTv;
    }

    public void setListNoteTv(ArrayList<TvShow> listNoteTv) {
        if (listNoteTv.size() > 0) {
            this.listNoteTv.clear();
        }
        this.listNoteTv.addAll(listNoteTv);

        notifyDataSetChanged();
    }

    public void addItem(TvShow tvShow) {
        this.listNoteTv.add(tvShow);
        notifyItemInserted(listNoteTv.size() - 1);
    }

    public void updateItem(int position, TvShow tvShow) {
        this.listNoteTv.set(position, tvShow);
        notifyItemChanged(position, tvShow);
    }

    public void removeItem(int position) {
        this.listNoteTv.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listNoteTv.size());
    }


    @NonNull
    @Override
    public TvFavoriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tvshow_item_favorite,parent,false);
        return new TvFavoriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvFavoriteHolder holder, final int position) {
        TvShow itemTv = listNoteTv.get(position);

        String titleTvFav = itemTv.getTitleName();
        String descTvFav = itemTv.getDescName();
        String imageTvFav = itemTv.getImgTv();

        holder.titleTxtFav.setText(titleTvFav);
        holder.descTxtFav.setText(descTvFav);
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ imageTvFav).fit().centerInside().into(holder.imgTvFav);
        holder.showDetailFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailShowActivityFav = new Intent(aContext, DetailActivityTvShowFav.class);
                detailShowActivityFav.putExtra(DetailActivityTvShowFav.EXTRA_POSITION, position);
                detailShowActivityFav.putExtra(DetailActivityTvShowFav.EXTRA_NOTE, listNoteTv.get(position));
                aContext.startActivity(detailShowActivityFav);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listNoteTv.size();
    }

    public class TvFavoriteHolder extends RecyclerView.ViewHolder {
        ConstraintLayout showDetailFav;
        ImageView imgTvFav;
        TextView titleTxtFav, descTxtFav;

        public TvFavoriteHolder(@NonNull View itemView) {
            super(itemView);
            titleTxtFav = itemView.findViewById(R.id.tv_title_show_favorite);
            descTxtFav = itemView.findViewById(R.id.tv_description_show_favorite);
            imgTvFav = itemView.findViewById(R.id.image_tv_favorite);
            showDetailFav = itemView.findViewById(R.id.detail_tvFav_activity);
        }
    }
}
