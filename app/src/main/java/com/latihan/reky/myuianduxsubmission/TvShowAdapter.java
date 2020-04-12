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

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private Context aContext;
    private List<TvShow> listTvShow;


    TvShowAdapter(Context aContext, List<TvShow> listTvShow) {
        this.aContext = aContext;
        this.listTvShow = listTvShow;

    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(aContext).inflate(R.layout.tvshow_item,parent,false);
        return new TvShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, final int position) {
        holder.txtTitleShow.setText(listTvShow.get(position).getTitleName());
        holder.txtDescShow.setText(listTvShow.get(position).getDescName());
        Picasso.get().load("https://image.tmdb.org/t/p/w780"+ listTvShow.get(position).getImgTv()).fit()
                .centerInside()
                .into(holder.imgTvShow);

       holder.itemTvShowDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailActivityTvShow = new Intent(aContext,DetailActivityTvShow.class);
                detailActivityTvShow.putExtra(DetailActivityTvShow.EXTRA_TV, listTvShow.get(position));
                aContext.startActivity(detailActivityTvShow);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTvShow.size();
    }

    void setListShow(List<TvShow> listTvShow) {
        this.listTvShow =listTvShow;
        notifyDataSetChanged();
    }

    static class TvShowViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout itemTvShowDetail;
        TextView txtTitleShow;
        TextView txtDescShow;
        ImageView imgTvShow;
        TvShowViewHolder(@NonNull View itemView) {
            super(itemView);
            itemTvShowDetail = itemView.findViewById(R.id.tvshow_detail);
            txtTitleShow = itemView.findViewById(R.id.tv_title_show);
            txtDescShow = itemView.findViewById(R.id.tv_description_show);
            imgTvShow = itemView.findViewById(R.id.image_tv_photo);
        }
    }
}
