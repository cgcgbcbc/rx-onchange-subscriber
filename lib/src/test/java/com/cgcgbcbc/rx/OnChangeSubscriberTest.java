package com.cgcgbcbc.rx;

import org.junit.Test;
import rx.Observable;
import rx.functions.Func0;
import rx.observers.TestSubscriber;

/**
 * Created by guangchen on 12/7/15 20:17.
 */
public class OnChangeSubscriberTest {

    @Test
    public void testWithFirst() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<Integer>();
        Observable.just(1, 1, 2, 2, 3, 3, 3).subscribe(new OnChangeSubscriber<Integer>(testSubscriber));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValues(1, 2, 3);
    }

    @Test
    public void testWithNotFirst() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<Integer>();
        Observable.just(1, 1, 2, 2, 3, 3, 3).subscribe(new OnChangeSubscriber<Integer>(testSubscriber, false));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValues(2, 3);
    }

    @Test
    public void testCustomEqual() throws Exception {
        TestSubscriber<Foo> testSubscriber = new TestSubscriber<Foo>();
        Observable<Foo> observable = Observable.defer(new Func0<Observable<Foo>>() {
            @Override
            public Observable<Foo> call() {
                return Observable.just(new Foo(1, 1), new Foo(1, 2), new Foo(2, 1), new Foo(1, 1));
            }
        });
        observable.subscribe(new OnChangeSubscriber<Foo>(testSubscriber));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(4);

        testSubscriber = new TestSubscriber<Foo>();
        observable.subscribe(new OnChangeSubscriber<Foo>(testSubscriber, new OnChangeSubscriber.Equal<Foo>() {
            @Override
            public boolean equals(Foo t1, Foo t2) {
                return t1.a == t2.a;
            }
        }));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(3);
    }

    static class Foo {
        int a;
        int b;

        Foo(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}