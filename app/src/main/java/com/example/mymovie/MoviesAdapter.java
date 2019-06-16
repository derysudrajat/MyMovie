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

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    private final Context context;
    private ArrayList<Movie> mData = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setmData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    public void clear() {
        int size = mData.size();
        mData.clear();
        notifyItemRangeRemoved(0, size);
    }

    public ArrayList<Movie> getMovieList(){
        return mData;
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_film, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder holder, int i) {
        final Movie movie = mData.get(i);
        holder.txtTitle.setText(movie.getTitle());
        holder.txtDesc.setText(movie.getOverview());
        Glide.with(context)
                .load(movie.getPoster())
                .into(holder.imgPoster);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, movie.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MovieDetail.class);
                intent.putExtra(MoviesFragment.MOVIE_EXTRA, movie);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        final TextView txtTitle;
        final TextView txtDesc;
        final ImageView imgPoster;

        MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDesc = itemView.findViewById(R.id.txt_description);
            imgPoster = itemView.findViewById(R.id.img_poster);
        }
    }
}
