package com.example.themoviedb;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hannesdorfmann.mosby3.mvi.MviFragment;
import com.hannesdorfmann.mosby3.mvi.MviPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public abstract class BaseFragment<V extends MvpView, P extends MviPresenter<V, ?>> extends MviFragment {

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.progress) protected ProgressBar mProgressBar;

    @LayoutRes
    public abstract int layoutRes();

    @StringRes
    public abstract int toolbarTitle();

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if (savedInstanceState != null && container != null)
            return container;
        else {
            final View view = LayoutInflater.from(container.getContext()).inflate(layoutRes(), container, false);
            ButterKnife.bind(this, view);

            setupToolbar(mToolbar);

            return view;
        }
    }

    public void setupToolbar(final Toolbar toolbar) {
        toolbar.setTitle(getString(toolbarTitle()));
    }
}
