package com.fearaujo.junomovies.movies;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.fearaujo.data.model.Movie;
import com.fearaujo.data.model.PopularMovies;
import com.fearaujo.data.service.MoviesDataSource;
import com.fearaujo.junomovies.IPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MoviesPresenter implements IPresenter<IMoviesView>, Callback<PopularMovies> {

    private IMoviesView mView;
    private MoviesDataSource mMoviesDataSource;
    private Context mApplicationContext;

    private final String MOVIES_KEY = "moviesKey";

    public MoviesPresenter(IMoviesView mView, Context applicationContext) {
        this.mView = mView;
        this.mApplicationContext = applicationContext;
    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        mView.recyclerViewSetup();
        mView.createAdapter();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(MOVIES_KEY)) {
            List<Movie> movies = savedInstanceState.getParcelableArrayList(MOVIES_KEY);
            showData(movies);
        } else {
            requestPopularMovies();
        }
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public Bundle onSaveInstanceState(Bundle outState) {
        List<Movie> movies = mView.getMoviesVisible();
        ArrayList<Movie> moviesArray = new ArrayList<>(movies);
        outState.putParcelableArrayList(MOVIES_KEY, moviesArray);
        return outState;
    }

    @Override
    public void onResponse(Call<PopularMovies> call, Response<PopularMovies> response) {
        if (response.isSuccessful() && response.body() != null) {
            showData(response.body().getResults());
        } else {
            showErrorMessage();
        }
    }

    @Override
    public void onFailure(Call<PopularMovies> call, Throwable t) {
        showErrorMessage();
    }

    void requestPopularMovies() {
        showProgressBar();

        if (mMoviesDataSource == null) {
            mMoviesDataSource = new MoviesDataSource(mApplicationContext);
        }

        mMoviesDataSource.getPopularMovies(this);
    }

    void requestRatedMovies() {
        showProgressBar();

        if (mMoviesDataSource == null) {
            mMoviesDataSource = new MoviesDataSource(mApplicationContext);
        }

        mMoviesDataSource.getRatedMovies(this);
    }

    private void showData(List<Movie> movies) {
        mView.updateRecyclerViewData(movies);

        mView.hideProgressBar();
        mView.hideErrorMessage();
        mView.showRecyclerView();
    }

    private void showErrorMessage() {
        mView.showErrorMessage();
        mView.hideRecyclerView();
        mView.hideProgressBar();
    }

    private void showProgressBar() {
        mView.showProgressBar();
        mView.hideErrorMessage();
        mView.hideRecyclerView();

    }
}
