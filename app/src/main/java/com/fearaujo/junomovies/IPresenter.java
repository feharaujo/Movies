package com.fearaujo.junomovies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

public interface IPresenter<T> {

    void onCreateView(Bundle savedInstanceState);

    void onViewCreated(View view, @Nullable Bundle savedInstanceState);

    void onDestroyView();

    Bundle onSaveInstanceState(Bundle outState);

}
