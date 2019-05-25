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
public class TvShowFragment extends Fragment {

    private TvShowAdapter adapter;
    private ProgressBar progressBar;
    private MainViewModel mainViewModel;
    public static final String TV_SHOW_EXTRA = "tv_show_extra";
    private static final String TV_SHOW_KEY = "tv";
    private String Language;
    private String Hours;
    private String Munites;
    private String As;
    private final String[] AdditionalState= new String[4];

    public TvShowFragment() {
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
        mainViewModel.getTvShow().observe(getActivity(),getTvShow);

        adapter = new TvShowAdapter(getActivity());
        adapter.notifyDataSetChanged();

        RecyclerView rvTvShow = rootView.findViewById(R.id.mainRv);
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTvShow.setAdapter(adapter);

        showData();

        return rootView;
    }

    private final Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShowItems) {
            if (tvShowItems!=null){
                adapter.setmData(tvShowItems);
                showLoading(false);
            }
        }
    };

    private void showData(){
        mainViewModel.setData(TV_SHOW_KEY, AdditionalState);
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
