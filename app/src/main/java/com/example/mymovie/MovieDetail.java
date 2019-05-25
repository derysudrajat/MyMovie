package com.example.mymovie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MovieDetail extends AppCompatActivity {
    private ImageView imgPoster;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tv_release;
    private TextView tvRuntime;
    private TextView tvGenre;
    private TextView tvCast;
    private TextView tvOverview;
    private TvShow tvShow;
    private ProgressBar progressBar;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        progressBar = findViewById(R.id.detail_progressBar);
        prepare();
        try {
            tvShow = getIntent().getParcelableExtra(TvShowFragment.TV_SHOW_EXTRA);
            movie = getIntent().getParcelableExtra(MoviesFragment.MOVIE_EXTRA);
            if (getIntent().getParcelableExtra(MoviesFragment.MOVIE_EXTRA) != null ) {
                setDataFromMovies();
            }else if (getIntent().getParcelableExtra(TvShowFragment.TV_SHOW_EXTRA)!=null){
                setDataFromTvShow();
            }
        }catch (Exception e){
            Log.d("MovieDetail","Ex: "+e);
        }

    }
    private void prepare(){
        showLoading(true);
        imgPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        tvYear = findViewById(R.id.tv_year);
        tv_release = findViewById(R.id.tv_date_release);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvGenre = findViewById(R.id.tv_genre);
        tvCast = findViewById(R.id.tv_cast);
        tvOverview = findViewById(R.id.tv_description);
        TextView tvDateCertification = findViewById(R.id.rel_cer_info);
    }
    private void setDataFromMovies(){
        setTitle(movie.getTitle());
        Glide.with(this).load(movie.getPoster()).into(imgPoster);
        tvTitle.setText(movie.getTitle());
        tvYear.setText(movie.getYear());
        tv_release.setText(movie.getReleaseInfo());
        tvRuntime.setText(movie.getRuntime());
        tvGenre.setText(movie.getGenre());
        tvCast.setText(movie.getCast());
        tvOverview.setText(movie.getOverview());
        showLoading(false);
    }
    private void setDataFromTvShow(){
        setTitle(tvShow.getTitle());
        Glide.with(this).load(tvShow.getPoster()).into(imgPoster);
        tvTitle.setText(tvShow.getTitle());
        tvYear.setText(tvShow.getYear());
        tv_release.setText(tvShow.getCertification());
        tvRuntime.setText(tvShow.getRuntime());
        tvGenre.setText(tvShow.getGenre());
        tvCast.setText(tvShow.getCast());
        tvOverview.setText(tvShow.getOverview());
        showLoading(false);
    }
    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
