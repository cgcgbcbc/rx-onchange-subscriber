package com.cgcgbcbc.rx;

import org.junit.Test;
import rx.Observable;
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
    public void testWithnotFirst() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<Integer>();
        Observable.just(1, 1, 2, 2, 3, 3, 3).subscribe(new OnChangeSubscriber<Integer>(testSubscriber, false));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValues(2, 3);
    }
}