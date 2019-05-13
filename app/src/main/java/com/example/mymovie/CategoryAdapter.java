package com.example.mymovie;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class CategoryAdapter extends FragmentPagerAdapter {
    private final Context mContext;

    public CategoryAdapter(Context mContext,FragmentManager fm) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int i) {
        if (i==0){
            return new MoviesFragment();
        }else{
            return new TvShowFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){
            return mContext.getString(R.string.tittle_movies);
        }else{
            return mContext.getString(R.string.tittle_tv_shows);
        }
    }
}
