package com.fearaujo.junomovies.movies;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.fearaujo.data.model.Movie;
import com.fearaujo.junomovies.R;
import com.fearaujo.junomovies.details.MovieDetailsActivity;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements IMoviesView, MoviesAdapter.ListItemClickListener {

    private ProgressBar mProgressBar;
    private RecyclerView mRvMovies;
    private LinearLayout llError;

    private MoviesPresenter mPresenter;
    private MoviesAdapter mAdapter;

    private final static int COLUMNS_ORIENTATION_PORTRAIT = 2;
    private final static int COLUMNS_ORIENTATION_LANDSCAPE = 4;

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        mPresenter = new MoviesPresenter(this, getActivity().getApplicationContext());

        mProgressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mRvMovies = (RecyclerView) view.findViewById(R.id.rvMovies);
        llError = (LinearLayout) view.findViewById(R.id.llError);

        recyclerViewSetup();
        createAdapter();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        llError.setOnClickListener(getErrorClickListener());
        mPresenter.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState = mPresenter.onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void recyclerViewSetup() {
        RecyclerView.LayoutManager layoutManager = null;
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager = new GridLayoutManager(getContext(), COLUMNS_ORIENTATION_PORTRAIT);
        } else {
            layoutManager = new GridLayoutManager(getContext(), COLUMNS_ORIENTATION_LANDSCAPE);
        }

        mRvMovies.setLayoutManager(layoutManager);
        mRvMovies.setHasFixedSize(true);
    }

    @Override
    public void createAdapter() {
        mAdapter = new MoviesAdapter(this);
        mRvMovies.setAdapter(mAdapter);
    }

    @Override
    public void updateRecyclerViewData(List<Movie> movies) {
        mAdapter.updateData(movies);
    }

    @Override
    public void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView() {
        mRvMovies.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRecyclerView() {
        mRvMovies.setVisibility(View.GONE);
    }

    @Override
    public void showErrorMessage() {
        llError.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMessage() {
        llError.setVisibility(View.GONE);
    }

    @Override
    public List<Movie> getMoviesVisible() {
        return mAdapter.getMovies();
    }

    public void searchRatedMovies() {
        mPresenter.requestRatedMovies();
    }

    public void searchPopularityMovies() {
        mPresenter.requestPopularMovies();
    }

    private View.OnClickListener getErrorClickListener() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.requestPopularMovies();
            }
        };
    }

    @Override
    public void onListItemClick(Movie movie) {
        Intent intent = new Intent(getActivity(), MovieDetailsActivity.class);
        intent.putExtra(MovieDetailsActivity.MOVIE_EXTRA, movie);
        startActivity(intent);
    }
}
