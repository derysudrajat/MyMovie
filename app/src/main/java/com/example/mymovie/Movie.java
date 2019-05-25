package com.example.mymovie;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Movie implements Parcelable {
    private String poster;
    private String title;
    private String year;
    private String releaseInfo;
    private String runtime;
    private String genre;
    private String cast;
    private String overview;


    @SuppressLint("SimpleDateFormat")
    public Movie(JSONObject object, String castList, String[] Astate) {
        try {
            String mTitle = (object.getString("title") != null) ? object.getString("title") : "-";
            String mReleaseDate = (object.getString("release_date") != null) ? object.getString("release_date") : "-";
            String mYear = (mReleaseDate.split("-")[0] != null) ? mReleaseDate.split("-")[0] : "-";
            String mPoster = "https://image.tmdb.org/t/p/w342" + object.getString("poster_path");
            String mOverview = (object.getString("overview") != null) ? object.getString("overview") : "-";

            StringBuilder mGenre = new StringBuilder();
            JSONArray genList = object.getJSONArray("genres");
            for (int i = 0; i < genList.length(); i++) {
                if (i == genList.length() - 1) {
                    mGenre.append(genList.getJSONObject(i).getString("name"));
                } else {
                    mGenre.append(genList.getJSONObject(i).getString("name")).append(", ");
                }
            }
            String mRuntime = "";
            try {
                if (object.getInt("runtime") != 0) {
                    int runtime = object.getInt("runtime");
                    int hh = runtime / 60;
                    int mm = runtime % 60;
                    if (runtime > 60) {
                        mRuntime = new StringBuilder()
                                .append(hh)
                                .append(Astate[2])
                                .append(mm)
                                .append(Astate[3]).toString();
                    } else {
                        mRuntime = new StringBuilder()
                                .append(mm)
                                .append(Astate[3]).toString();
                    }
                } else {
                    mRuntime = "Unknown";
                }
            } catch (Exception e) {
                Log.d("MOVIE", "Runtime: " + mRuntime);
            }

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date mDate = formatter.parse(mReleaseDate);
            formatter = new SimpleDateFormat("dd MMM yyyy");
            String strDate = formatter.format(mDate);

            this.title = mTitle;
            this.overview = mOverview;
            this.releaseInfo = strDate;
            this.year = mYear;
            this.poster = mPoster;
            this.runtime = mRuntime;
            this.cast = castList;
            this.genre = mGenre.toString();
        } catch (Exception e) {
            Log.d("Movie", "Constructor: " + e.getMessage());
        }
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getCast() {
        return cast;
    }

    public String getOverview() {
        return overview;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.releaseInfo);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.cast);
        dest.writeString(this.overview);
    }

    private Movie(Parcel in) {
        this.poster = in.readString();
        this.title = in.readString();
        this.year = in.readString();
        this.releaseInfo = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.cast = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
