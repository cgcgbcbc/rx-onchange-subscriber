package com.cgcgbcbc.rx.internal;

import rx.Observer;

/**
 * Created by guangchen on 12/7/15 20:11.
 */
public class OnChangeObserver<T> implements Observer<T> {
    private final Observer<? super T> s;
    private T last;
    private boolean withFirst = true;

    public OnChangeObserver(Observer<? super T> s, boolean withFirst) {
        this.s = s;
        this.withFirst = withFirst;
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
            if (!t.equals(last)) {
                last = t;
                s.onNext(t);
            }
        }
    }
}
