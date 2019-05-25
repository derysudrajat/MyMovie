package com.example.mymovie;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TvShow implements Parcelable {
    private String poster;
    private String title;
    private String year;
    private String runtime;
    private String certification;
    private String genre;
    private String cast;
    private String overview;

    @SuppressLint("SimpleDateFormat")
    public TvShow(JSONObject object, String castList, String[] AState) {
        try {
            String mTitle = (object.getString("name") != null) ? object.getString("name") : "-";
            String mReleaseDate = (object.getString("first_air_date") != null) ? object.getString("first_air_date") : "-";
            String mYear = (mReleaseDate.split("-")[0] != null) ? mReleaseDate.split("-")[0] : "Unknown";
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
                if (object.getJSONArray("episode_run_time").getInt(0) != 0) {
                    int runtime = object.getJSONArray("episode_run_time").getInt(0);
                    int hh = runtime / 60;
                    int mm = runtime % 60;
                    if (runtime > 60) {
                        mRuntime = new StringBuilder()
                                .append(hh)
                                .append(AState[2])
                                .append(mm)
                                .append(AState[3]).toString();
                    } else {
                        mRuntime = new StringBuilder()
                                .append(mm)
                                .append(AState[3])
                                .toString();
                    }
                } else {
                    mRuntime = "-";
                }
            } catch (Exception e) {
                Log.d("TVSHOW", "Runtime: " + mRuntime);
            }

            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date mDate = formatter.parse(mReleaseDate);
            formatter = new SimpleDateFormat("dd MMM yyyy");
            String strDate = formatter.format(mDate);

            this.title = mTitle;
            this.overview = mOverview;
            this.certification = strDate;
            this.year = mYear;
            this.poster = mPoster;
            this.runtime = mRuntime;
            this.cast = castList;
            this.genre = mGenre.toString();
        } catch (Exception e) {
            Log.d("TcShow", "Constructor: " + e.getMessage());
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

    public String getCertification() {
        return certification;
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
        dest.writeString(this.certification);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.cast);
        dest.writeString(this.overview);
    }

    private TvShow(Parcel in) {
        this.poster = in.readString();
        this.title = in.readString();
        this.year = in.readString();
        this.certification = in.readString();
        this.runtime = in.readString();
        this.genre = in.readString();
        this.cast = in.readString();
        this.overview = in.readString();
    }

    public static final Parcelable.Creator<TvShow> CREATOR = new Parcelable.Creator<TvShow>() {
        @Override
        public TvShow createFromParcel(Parcel source) {
            return new TvShow(source);
        }

        @Override
        public TvShow[] newArray(int size) {
            return new TvShow[size];
        }
    };
}
