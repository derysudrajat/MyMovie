package com.example.mymovie;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvViewHolder> {
    private final Context context;
    private final ArrayList<TvShow> mData =new ArrayList<>();

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public void setmData(ArrayList<TvShow> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film,viewGroup,false);
        return new TvViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int i) {
        final TvShow tvShow = mData.get(i);
        holder.txtTitle.setText(tvShow.getTitle());
        holder.txtDesc.setText(tvShow.getOverview());
        Glide.with(context)
                .load(tvShow.getPoster())
                .into(holder.imgPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, tvShow.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnMoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(TvShowFragment.TV_SHOW_EXTRA, tvShow);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class TvViewHolder extends RecyclerView.ViewHolder {
        final TextView txtTitle;
        final TextView txtDesc;
        final ImageView imgPoster;
        final Button btnMoreInfo;

        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDesc = itemView.findViewById(R.id.txt_description);
            imgPoster = itemView.findViewById(R.id.img_poster);
            btnMoreInfo = itemView.findViewById(R.id.btn_more_info);
        }
    }
}
