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
public class TvShowFragment extends Fragment {
    private String[] dataTitle;
    private String[] dataYear;
    private String[] dataCertification;
    private String[] dataRuntime;
    private String[] dataGenre;
    private String[] dataCast;
    private String[] dataOverview;
    private TypedArray dataPoster;
    private TvShowAdapter adapter;
    public static final String TV_SHOW_EXTRA = "tv_show_extra";


    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);
        RecyclerView rvTvShow = rootView.findViewById(R.id.mainRv);
        rvTvShow.setHasFixedSize(true);
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TvShowAdapter(getActivity());
        prepare();
        addItem();
        rvTvShow.setAdapter(adapter);
        return rootView;
    }

    private void addItem() {
        ArrayList<TvShow> tvShows = new ArrayList<>();
        for (int i = 0; i < dataTitle.length; i++) {
            TvShow tvShow = new TvShow(
                    dataPoster.getResourceId(i, -1),
                    dataTitle[i], dataYear[i], dataCertification[i],
                    dataRuntime[i], dataGenre[i], dataCast[i],
                    dataOverview[i]);
            tvShows.add(tvShow);
        }
        adapter.setListTvShow(tvShows);
    }

    private void prepare() {
        dataTitle = getResources().getStringArray(R.array.tv_tittle);
        dataYear = getResources().getStringArray(R.array.tv_year);
        dataCertification = getResources().getStringArray(R.array.certification);
        dataRuntime = getResources().getStringArray(R.array.tv_runtime);
        dataGenre = getResources().getStringArray(R.array.tv_genre);
        dataCast = getResources().getStringArray(R.array.tv_cast);
        dataOverview = getResources().getStringArray(R.array.tv_overview);
        dataPoster = getResources().obtainTypedArray(R.array.tv_poster);
    }

}
