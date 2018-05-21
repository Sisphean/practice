package com.sisyphean.practice.utils;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;

public class RxBus {

    private static RxBus rxBus;
    private final FlowableProcessor<Object> bus;

    private RxBus() {
        bus = PublishProcessor.create().toSerialized();
    }

    private static class RxBusHolder{
        private static RxBus INSTANCE = new RxBus();
    }

    public static RxBus getInstance() {
        return RxBusHolder.INSTANCE;
    }

    public void post(Object o) {
        bus.onNext(o);
    }

    public <T> Flowable<T> toObservable(Class<T> eventType) {
        return bus.ofType(eventType);
    }
}
