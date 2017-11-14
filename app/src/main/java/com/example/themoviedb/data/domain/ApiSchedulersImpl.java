package com.example.themoviedb.data.domain;

import javax.inject.Inject;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiSchedulersImpl implements ApiSchedulers {

    @Inject
    public ApiSchedulersImpl() {
    }

    @Override
    public SingleTransformer forSingle() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public ObservableTransformer forObservable() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public CompletableTransformer forCompletable() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());
    }
}
