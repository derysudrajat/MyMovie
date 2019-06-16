package com.example.mymovie;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
    public static final String TV_SHOW_KEY = "tv";
    public static final String TV_SHOW_OUTSTATE = "tv_list";
    public String Language, Hours, Munites, As;
    RecyclerView rvTvShow;
    SwipeRefreshLayout refreshLayout;
    String[] AdditionalState= new String[4];

    public TvShowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.list_item, container, false);
        progressBar = rootView.findViewById(R.id.progressBar);
        refreshLayout = rootView.findViewById(R.id.swipe_refresh);

        Language = getResources().getString(R.string.lang);
        Hours = getResources().getString(R.string.hours);
        Munites = getResources().getString(R.string.munites);
        As = getResources().getString(R.string.as);
        AdditionalState[0] = Language;
        AdditionalState[1] = As;
        AdditionalState[2] = Hours;
        AdditionalState[3] = Munites;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mainViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MainViewModel.class);
            }
            mainViewModel.getTvShow().observe(getActivity(),getTvShow);

            adapter = new TvShowAdapter(getActivity());
            adapter.notifyDataSetChanged();

            rvTvShow = rootView.findViewById(R.id.mainRv);
            rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
            rvTvShow.setAdapter(adapter);

            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    adapter.clear();
                    showData();
                    refreshLayout.setRefreshing(false);
                }
            });

            if (savedInstanceState!=null){
                onActivityCreated(savedInstanceState);
            }else{
                showData();
            }
            Log.d("TVSHOWFRAG","onCreateView was Called");
        }catch (Exception e){
            Log.d("TVSHOW","Exception: "+e);

        }
        return rootView;

    }

    private Observer<ArrayList<TvShow>> getTvShow = new Observer<ArrayList<TvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<TvShow> tvShowItems) {
            if (tvShowItems!=null){
                Log.d("TVSHOWFRAG","getTvShow was Called");
                adapter.setmData(tvShowItems);
                showLoading(false);
            }
        }
    };

    private void showData(){
        Log.d("TVSHOWFRAG","showData was Called");
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("TVSHOWFRAG","onSaveInsState was Called");
        outState.putParcelableArrayList(TV_SHOW_OUTSTATE,new ArrayList<Parcelable>(adapter.getTvShowList()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState!=null){
            Log.d("TVSHOWFRAG","onACreated was Called");
            ArrayList<TvShow> mList;
            mList = savedInstanceState.getParcelableArrayList(TV_SHOW_OUTSTATE);
            adapter.clear();
            adapter.setmData(mList);
            rvTvShow.setAdapter(adapter);
        }
    }
}
