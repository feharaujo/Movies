package com.fearaujo.junomovies.movies;

import com.fearaujo.data.model.Movie;

import java.util.List;

public interface IMoviesView {

    void recyclerViewSetup();

    void updateRecyclerViewData(List<Movie> movies);

    void createAdapter();

    void showProgressBar();

    void hideProgressBar();

    void showRecyclerView();

    void hideRecyclerView();

    void showErrorMessage();

    void hideErrorMessage();

    List<Movie> getMoviesVisible();

}
