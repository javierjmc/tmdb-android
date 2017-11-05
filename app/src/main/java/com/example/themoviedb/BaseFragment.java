package com.example.themoviedb;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

public abstract class BaseFragment<V extends MvpView, P extends MviPresenter<V, ?>> extends MviFragment {

    public abstract int layoutRes();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState != null && container != null)
            return container;
        else
            return LayoutInflater.from(container.getContext()).inflate(layoutRes(), container, false);
    }
}
