package com.fearaujo.data.service;

import com.fearaujo.data.BuildConfig;

class UrlConstants {

    static final String BASE_URL = "https://api.themoviedb.org";

    static final String BASE_IMG_URL = "http://image.tmdb.org/t/p/w400";

    static final String ENDPOINT_POPULAR_MOVIES = "/3/movie/popular?api_key=" + BuildConfig.API_KEY;

    static final String ENDPOINT_RATED_MOVIES = "/3/movie/top_rated?api_key=" + BuildConfig.API_KEY;

}
