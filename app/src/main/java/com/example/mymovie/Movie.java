package com.example.mymovie;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {
    private int poster;
    private String title;
    private String year;
    private String releaseInfo;
    private String runtime;
    private String genre;
    private String cast;
    private String overview;

    public Movie(int poster, String title, String year, String releaseInfo, String runtime, String genre, String cast, String overview) {
        this.poster = poster;
        this.title = title;
        this.year = year;
        this.releaseInfo = releaseInfo;
        this.runtime = runtime;
        this.genre = genre;
        this.cast = cast;
        this.overview = overview;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReleaseInfo() {
        return releaseInfo;
    }

    public void setReleaseInfo(String releaseInfo) {
        this.releaseInfo = releaseInfo;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
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
        dest.writeString(this.releaseInfo);
        dest.writeString(this.runtime);
        dest.writeString(this.genre);
        dest.writeString(this.cast);
        dest.writeString(this.overview);
    }

    private Movie(Parcel in) {
        this.poster = in.readInt();
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
