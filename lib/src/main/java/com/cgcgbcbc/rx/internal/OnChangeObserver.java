package com.cgcgbcbc.rx.internal;

import com.cgcgbcbc.rx.OnChangeSubscriber;
import rx.Observer;

/**
 * Created by guangchen on 12/7/15 20:11.
 */
public class OnChangeObserver<T> implements Observer<T> {
    private final Observer<? super T> s;
    private T last;
    private boolean withFirst = true;
    private OnChangeSubscriber.Equal<T> equal;

    public OnChangeObserver(Observer<? super T> s, boolean withFirst, OnChangeSubscriber.Equal<T> equal) {
        this.s = s;
        this.withFirst = withFirst;
        this.equal = equal;
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
        if (last == null) {
            last = t;
            if (withFirst) {
                s.onNext(t);
            }
        } else {
            if (!equal.equals(t, last)) {
                last = t;
                s.onNext(t);
            }
        }
    }
}
