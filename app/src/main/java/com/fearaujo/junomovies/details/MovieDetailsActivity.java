package com.fearaujo.junomovies.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.fearaujo.data.model.Movie;
import com.fearaujo.data.service.ImageLoader;
import com.fearaujo.junomovies.R;

public class MovieDetailsActivity extends AppCompatActivity {

    public static final String MOVIE_EXTRA = "movieExtra";
    private final String MOVIE_KEY = "movieKey";

    private TextView tvSynopsis;
    private TextView tvRating;
    private TextView tvDate;
    private ImageView backPoster;

    private Movie mMovie;
    private ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tvSynopsis = findViewById(R.id.tvSynopsis);
        tvRating = findViewById(R.id.tvRating);
        tvDate = findViewById(R.id.tvDate);
        backPoster = findViewById(R.id.back_poster);

        if(savedInstanceState != null) {
            mMovie = savedInstanceState.getParcelable(MOVIE_KEY);
        } else {
            mMovie = getIntent().getParcelableExtra(MOVIE_EXTRA);
        }

        mImageLoader = new ImageLoader();

        setTitle(mMovie.getTitle());

        tvSynopsis.setText(mMovie.getOverview());
        tvRating.setText(String.valueOf(mMovie.getVoteAverage()));
        tvDate.setText(mMovie.getReleaseDate());
        mImageLoader.loadImage(this, backPoster, mMovie.getBackdropPath());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(MOVIE_KEY, mMovie);
        super.onSaveInstanceState(outState);
    }
}
