package com.example.mymovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

class MovieAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>();
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_film, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder(convertView);
        Movie movie = (Movie) getItem(position);
        viewHolder.bind(movie);
        return convertView;
    }

    private class ViewHolder {
        private final TextView txtTitle;
        private final TextView txtDesc;
        private final ImageView imgPoster;

        ViewHolder(View convertView) {
            txtTitle = convertView.findViewById(R.id.txt_title);
            txtDesc = convertView.findViewById(R.id.txt_description);
            imgPoster = convertView.findViewById(R.id.img_poster);
        }

        void bind(Movie movie) {
            txtTitle.setText(movie.getTitle());
            txtDesc.setText(movie.getOverview());
            Glide.with(context).load(movie.getPoster()).into(imgPoster);
        }
    }
}
