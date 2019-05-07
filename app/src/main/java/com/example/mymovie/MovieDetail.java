package com.example.mymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {
    ImageView imgPoster;
    TextView tvTitle, tvYear, tv_release, tvRuntime, tvGenre, tvCast, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Movie movie = getIntent().getParcelableExtra(MainActivity.MOVIE_EXTRA);
        imgPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvYear = findViewById(R.id.tv_year);
        tv_release = findViewById(R.id.tv_date_release);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvGenre = findViewById(R.id.tv_genre);
        tvCast = findViewById(R.id.tv_cast);
        tvOverview = findViewById(R.id.tv_description);

        setTitle(movie.getTitle());
        Glide.with(this).load(movie.getPoster()).into(imgPoster);
        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tv_release.setText(movie.getReleaseInfo());
        tvRuntime.setText(movie.getRuntime());
        tvGenre.setText(movie.getGenre());
        tvCast.setText(movie.getCast());
        tvOverview.setText(movie.getOverview());
    }
}
