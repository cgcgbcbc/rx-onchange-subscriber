package com.cgcgbcbc.rx;

import com.cgcgbcbc.rx.internal.OnChangeSerializeObserver;
import rx.Observer;
import rx.Subscriber;

import java.util.Comparator;

/**
 * Created by guangchen on 12/7/15 19:40.
 */
public class OnChangeSubscriber<T> extends Subscriber<T> {
    private final Observer<T> s;

    public OnChangeSubscriber(Subscriber<? super T> s) {
        this(s, true, true, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst) {
        this(s, withFirst, true, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst, boolean shareSubscriptions) {
        this(s, withFirst, shareSubscriptions, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Subscriber<? super T> s, Equal<T> equal) {
        this(s, true, true, equal);
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst, Equal<T> equal) {
        this(s, withFirst, true, equal);
    }

    public OnChangeSubscriber(Subscriber<? super T> s, boolean withFirst, boolean shareSubscriptions, Equal<T> equal) {
        super(s, shareSubscriptions);
        this.s = new OnChangeSerializeObserver<T>(s, withFirst, equal);
    }

    public OnChangeSubscriber(Observer<? super T> s) {
        this(s, true, true, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Observer<? super T> s, boolean withFirst) {
        this(s, withFirst, true, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Observer<? super T> s, boolean withFirst, boolean shareSubscriptions) {
        this(s, withFirst, shareSubscriptions, new GenericEqual<T>());
    }

    public OnChangeSubscriber(Observer<? super T> s, Equal<T> equal) {
        this(s, true, true, equal);
    }

    public OnChangeSubscriber(Observer<? super T> s, boolean withFirst, Equal<T> equal) {
        this(s, withFirst, true, equal);
    }

    public OnChangeSubscriber(final Observer<? super T> s, boolean withFirst, boolean shareSubscriptions, Equal<T> equal) {
        this(new Subscriber<T>() {
            @Override
            public void onCompleted() {
                s.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                s.onError(e);
            }

            @Override
            public void onNext(T o) {
                s.onNext(o);
            }
        }, withFirst, shareSubscriptions, equal);
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

    public static abstract class Equal<E> {
        public abstract boolean equals(E t1, E t2);
    }

    private static final class GenericEqual<E> extends Equal<E> {
        @Override
        public boolean equals(E t1, E t2) {
            if (t1 == null) {
                return t2 == null;
            }
            return t1.equals(t2);
        }
    }
}
