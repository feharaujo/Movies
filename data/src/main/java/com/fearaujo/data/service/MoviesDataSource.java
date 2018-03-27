package com.fearaujo.data.service;

import android.content.Context;

import com.fearaujo.data.model.PopularMovies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class MoviesDataSource {

    private final MoviesAPI mMoviesAPI;

    public MoviesDataSource(Context applicationContext) {
        Retrofit retrofit = RetrofitCreator.getInstance(applicationContext);
        mMoviesAPI = retrofit.create(MoviesAPI.class);
    }

    public void getPopularMovies(Callback<PopularMovies> callback) {

        Call<PopularMovies> call = mMoviesAPI.fetchPopularMovies();
        call.enqueue(callback);
    }

    public void getRatedMovies(Callback<PopularMovies> callback) {

        Call<PopularMovies> call = mMoviesAPI.fetchRatedMovies();
        call.enqueue(callback);
    }

}
