package com.cgcgbcbc.rx;

import com.cgcgbcbc.rx.internal.OnChangeSerializeObserver;
import rx.Observer;
import rx.Subscriber;

/**
 * Created by guangchen on 12/7/15 19:40.
 */
public class OnChangeSubscriber<T> extends Subscriber<T> {
    private final Observer<T> s;

    public OnChangeSubscriber(Subscriber<? super T> s) {
        this(s, true, true);
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst) {
        this(s, withFirst, true);
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst, boolean shareSubscriptions) {
        super(s, shareSubscriptions);
        this.s = new OnChangeSerializeObserver<T>(s, withFirst);
    }

    @Override
    public void onCompleted() {
        s.onCompleted();
    }

    @Override
    public void onError(Throwable e) {
        s.onError(e);
    }

    @Override
    public void onNext(T t) {
        s.onNext(t);
    }
}
