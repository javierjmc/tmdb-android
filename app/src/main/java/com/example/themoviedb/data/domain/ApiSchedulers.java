package com.example.themoviedb.data.domain;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public interface ApiSchedulers {
    <T> SingleTransformer<T, T> forSingle();

    <T> ObservableTransformer<T, T> forObservable();

    CompletableTransformer forCompletable();
}

