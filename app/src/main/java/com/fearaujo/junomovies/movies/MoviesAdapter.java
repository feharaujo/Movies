package com.fearaujo.junomovies.movies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fearaujo.data.model.Movie;
import com.fearaujo.data.service.ImageLoader;
import com.fearaujo.junomovies.R;

import java.util.ArrayList;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private List<Movie> movies = new ArrayList<>();
    private ImageLoader mImageLoader;
    private Context mContext;

    private ListItemClickListener mListItemClickListener;

    public MoviesAdapter(ListItemClickListener mListItemClickListener) {
        this.mListItemClickListener = mListItemClickListener;
        mImageLoader = new ImageLoader();
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        int layoutIdForListItem = R.layout.item_movie;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, parent, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        mImageLoader.loadImage(mContext, holder.ivMovie, movies.get(position).getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void updateData(@NonNull List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    List<Movie> getMovies() {
        return movies;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView ivMovie;

        MoviesViewHolder(View itemView) {
            super(itemView);
            ivMovie = (ImageView) itemView.findViewById(R.id.ivMovie);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListItemClickListener.onListItemClick(movies.get(getAdapterPosition()));
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(Movie movie);
    }

}
