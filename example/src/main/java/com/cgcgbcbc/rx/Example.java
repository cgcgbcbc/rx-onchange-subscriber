package com.cgcgbcbc.rx;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observers.Observers;
import rx.observers.Subscribers;

/**
 * Created by guangchen on 12/7/15 21:58.
 */
public class Example {
    public static void main(String[] args) {
        Observer<Integer> observer = Observers.create(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                System.out.println(integer);
            }
        });

        Observable.just(1, 1, 2, 2, 1, 1).subscribe(new OnChangeSubscriber<Integer>(observer));
        // 1
        // 2
        // 1

        // without first
        Observable.just(1, 1, 2, 2, 1, 1).subscribe(new OnChangeSubscriber<Integer>(observer, false));
        // 2
        // 1

        // custom equal function
        Observable.just(1, 3, 2, 1, 4).subscribe(new OnChangeSubscriber<Integer>(observer, new OnChangeSubscriber.Equal<Integer>() {
            @Override
            public boolean equals(Integer t1, Integer t2) {
                return t1 <= t2;
            }
        }));
        // 1
        // 3
        // 4
    }
}
