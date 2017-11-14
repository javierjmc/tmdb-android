package com.example.themoviedb.data.domain;

import io.reactivex.CompletableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public interface ApiSchedulers {
    SingleTransformer forSingle();

    ObservableTransformer forObservable();

    CompletableTransformer forCompletable();
}

