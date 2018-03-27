package com.fearaujo.junomovies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.fearaujo.junomovies.movies.MoviesFragment;

public class MainActivity extends AppCompatActivity {

    private MoviesFragment mMoviesFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mMoviesFragment = (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.frag_movies);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(MainActivity.this);
        menuInflater.inflate(R.menu.menu_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_popularity:
                mMoviesFragment.searchPopularityMovies();
                break;
            case R.id.action_rated:
                mMoviesFragment.searchRatedMovies();
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
