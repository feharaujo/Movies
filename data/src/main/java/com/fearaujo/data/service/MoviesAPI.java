package com.fearaujo.data.service;

import com.fearaujo.data.model.PopularMovies;

import retrofit2.Call;
import retrofit2.http.GET;

interface MoviesAPI {

    @GET(UrlConstants.ENDPOINT_POPULAR_MOVIES)
    Call<PopularMovies> fetchPopularMovies();

    @GET(UrlConstants.ENDPOINT_RATED_MOVIES)
    Call<PopularMovies> fetchRatedMovies();
}
