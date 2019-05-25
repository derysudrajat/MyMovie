package com.example.mymovie;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModel extends ViewModel {
    private static final String API_KEY = "0a597bad68c0b95d5fab612cff9d8891";
    AsyncHttpClient client = new AsyncHttpClient();
    // Data Movie
    private MutableLiveData<ArrayList<Movie>> listMovie = new MutableLiveData<>();
    final ArrayList<Movie> listItemsMovie = new ArrayList<>();
    // Data Tv Show
    private MutableLiveData<ArrayList<TvShow>> listTv = new MutableLiveData<>();
    final ArrayList<TvShow> listItemsTv = new ArrayList<>();

    LiveData<ArrayList<Movie>> getMovies() {
        return listMovie;
    }

    LiveData<ArrayList<TvShow>> getTvShow(){
        return listTv;
    }

    void setData(final String mKey, final String[] AState) {
        String url = new StringBuilder()
                .append("https://api.themoviedb.org/3/discover/")
                .append(mKey)
                .append("?api_key=")
                .append(API_KEY)
                .append("&language=")
                .append(AState[0]).toString();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject dataObj = list.getJSONObject(i);
                        String id = dataObj.getString("id");
                        getDataCast(id, list, mKey, AState);
                    }
                } catch (Exception e) {
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    void getDataCast(final String id, final JSONArray list, final String mKey, final String[] AState) {
        String url = new StringBuilder()
                .append("https://api.themoviedb.org/3/")
                .append(mKey)
                .append("/")
                .append(id)
                .append("/credits?api_key=")
                .append(API_KEY).toString();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray castList = responseObject.getJSONArray("cast");
                    StringBuilder cast = new StringBuilder();
                    for (int i = 0; i < 5; i++) {
                        String name = castList.getJSONObject(i).getString("name");
                        String chara = castList.getJSONObject(i).getString("character");
                        cast.append((chara != null) ?
                                new StringBuilder()
                                        .append(name)
                                        .append(" ")
                                        .append(AState[1])
                                        .append(" ")
                                        .append(chara)
                                        .append("\n")
                                        .toString() :
                                new StringBuilder()
                                        .append(name)
                                        .append(" ")
                                        .append(AState[1])
                                        .append(" -")
                                        .append("\n").toString());
                    }
                    getDetailData(id, list, cast.toString(), mKey, AState);

                } catch (JSONException e) {
                    Log.d("JSON Execption: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    void getDetailData(final String id, final JSONArray list, final String mCast, final String mKey, final String[] ASate) {
        String url = new StringBuilder()
                .append("https://api.themoviedb.org/3/")
                .append(mKey)
                .append("/")
                .append(id)
                .append("?api_key=")
                .append(API_KEY)
                .append("&language=")
                .append(ASate[0]).toString();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    if (mKey.equals(MoviesFragment.MOVIE_KEY)){
                        //Movie Data Set
                        Movie movieItems = new Movie(responseObject, mCast, ASate);
                        listItemsMovie.add(movieItems);
                        if (listItemsMovie.size() == list.length()) {
                            listMovie.postValue(listItemsMovie);
                        }
                    }else{
                        //Tv Data Set
                        TvShow tvshowItems = new TvShow(responseObject, mCast, ASate);
                        listItemsTv.add(tvshowItems);
                        if (listItemsTv.size()==list.length()){
                            listTv.postValue(listItemsTv);
                        }
                    }

                } catch (Exception e) {
                    Log.d("JSON Execption: ", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }
}
