package com.example.mymovie;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private String[] dataTitle;
    private String[] dataYear;
    private String[] dataRelease;
    private String[] dataRuntime;
    private String[] dataGenre;
    private String[] dataCast;
    private String[] dataOverview;
    private TypedArray dataPoster;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;
    public static final String MOVIE_EXTRA = "movie_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter(this);
        listView.setAdapter(adapter);
        prepare();
        addItem();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, MovieDetail.class);
                intent.putExtra(MOVIE_EXTRA, movies.get(position));
                startActivity(intent);
                Toast.makeText(MainActivity.this, movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addItem() {
        movies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie(
                    dataPoster.getResourceId(i, -1),
                    dataTitle[i], dataYear[i], dataRelease[i],
                    dataRuntime[i], dataGenre[i], dataCast[i],
                    dataOverview[i]);
            movies.add(movie);
        }
        adapter.setMovies(movies);
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.movie_title);
        dataYear = getResources().getStringArray(R.array.movie_year);
        dataRelease = getResources().getStringArray(R.array.release_info);
        dataRuntime = getResources().getStringArray(R.array.movie_runtime);
        dataGenre = getResources().getStringArray(R.array.movie_genre);
        dataCast = getResources().getStringArray(R.array.movie_cast);
        dataOverview = getResources().getStringArray(R.array.movie_overview);
        dataPoster = getResources().obtainTypedArray(R.array.movie_poster);
    }

}
