package com.example.mymovie;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataYear;
    private String[] dataRelease;
    private String[] dataRuntime;
    private String[] dataGenre;
    private String[] dataCast;
    private String[] dataOverview;
    private TypedArray dataPoster;
    private MoviesAdapter adapter;
    public static final String MOVIE_EXTRA = "movie_extra";


    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);
        RecyclerView rvMovies = rootView.findViewById(R.id.mainRv);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MoviesAdapter(getActivity());
        prepare();
        addItem();
        rvMovies.setAdapter(adapter);
        return rootView;

    }

    private void addItem() {
        ArrayList<Movie> movies = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie(
                    dataPoster.getResourceId(i, -1),
                    dataTitle[i], dataYear[i], dataRelease[i],
                    dataRuntime[i], dataGenre[i], dataCast[i],
                    dataOverview[i]);
            movies.add(movie);
        }
        adapter.setListMovie(movies);
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
