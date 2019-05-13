package com.example.mymovie;

import android.os.Parcel;
import android.os.Parcelable;

public class TvShow implements Parcelable {
    private final int poster;
    private final String title;
    private final String year;
    private final String certification;
    private final String runtime;
    private final String genre;
    private final String cast;
    private final String overview;

    public TvShow(int poster, String title, String year, String certification, String runtime, String genre, String cast, String overview) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.certification = certification;
        this.runtime = runtime;
        this.genre = genre;
        this.cast = cast;
        this.overview = overview;
    }

    public int getPoster() {
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
        dest.writeInt(this.poster);
        dest.writeString(this.title);
        dest.writeString(this.year);
        dest.writeString(this.certification);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.cast);
        dest.writeString(this.overview);
    }

    private TvShow(Parcel in) {
        this.poster = in.readInt();
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
