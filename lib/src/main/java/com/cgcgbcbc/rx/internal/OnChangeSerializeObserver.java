package com.cgcgbcbc.rx.internal;

import com.cgcgbcbc.rx.OnChangeSubscriber;
import rx.Observer;
import rx.observers.SerializedObserver;

/**
 * Created by guangchen on 12/7/15 20:08.
 */
public class OnChangeSerializeObserver<T> extends SerializedObserver<T> {

    public OnChangeSerializeObserver(Observer<? super T> s, boolean withFirst, OnChangeSubscriber.Equal<T> equal) {
        super(new OnChangeObserver<T>(s, withFirst, equal));
    }
}
