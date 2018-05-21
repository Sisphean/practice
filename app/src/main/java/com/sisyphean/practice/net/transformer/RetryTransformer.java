package com.sisyphean.practice.net.transformer;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RetryTransformer implements ObservableTransformer {

    private int retryCount = 0;
    private int retryMaxCount;
    private long interval;

    public RetryTransformer(int retryMaxCount, long interval) {
        this.retryMaxCount = retryMaxCount;
        this.interval = interval;
    }

    @Override
    public ObservableSource apply(Observable upstream) {
        return upstream
                .retryWhen(new Function<Observable<Throwable>, Observable<Object>>() {
                    @Override
                    public Observable<Object> apply(Observable<Throwable> throwableObservable) throws Exception {
                        return throwableObservable
                                .zipWith(Observable.range(1, retryMaxCount), new BiFunction<Throwable, Integer, Integer>() {
                                    @Override
                                    public Integer apply(Throwable throwable, Integer integer) throws Exception {
                                        return integer;
                                    }
                                })
                                .flatMap(new Function<Integer, ObservableSource<?>>() {
                                    @Override
                                    public ObservableSource<?> apply(Integer retryCount) throws Exception {
                                        return Observable.timer((long) Math.pow(interval, retryCount), TimeUnit.SECONDS);
                                    }
                                });

                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
