package com.example.mymovie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment{

    private MoviesAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModel mainViewModel;
    public static final String MOVIE_EXTRA = "movie_extra";
    public static final String MOVIE_KEY = "movie";
    public String Language, Hours, Munites, As;
    String[] AdditionalState = new String[4];

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);

        progressBar = rootView.findViewById(R.id.progressBar);
        Language = getResources().getString(R.string.lang);
        Hours = getResources().getString(R.string.hours);
        Munites = getResources().getString(R.string.munites);
        As = getResources().getString(R.string.as);
        AdditionalState[0] = Language;
        AdditionalState[1] = As;
        AdditionalState[2] = Hours;
        AdditionalState[3] = Munites;
        mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
        mainViewModel.getMovies().observe(getActivity(),getMovie);

        adapter = new MoviesAdapter(getActivity());
        adapter.notifyDataSetChanged();

        RecyclerView rvMovies = rootView.findViewById(R.id.mainRv);
        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvMovies.setAdapter(adapter);

        showData();

        return rootView;

    }

    private  Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<Movie> movieItems) {
            if (movieItems!=null){
                adapter.setmData(movieItems);
                showLoading(false);
            }
        }
    };

    private void showData(){
        mainViewModel.setData(MOVIE_KEY,AdditionalState);
        showLoading(true);
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }



}
