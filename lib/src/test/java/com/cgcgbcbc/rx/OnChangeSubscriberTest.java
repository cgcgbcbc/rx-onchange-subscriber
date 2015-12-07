package com.cgcgbcbc.rx;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guangchen on 12/7/15 20:17.
 */
public class OnChangeSubscriberTest {

    @Test
    public void test() throws Exception {
        final List<Integer> result = new ArrayList<Integer>();
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<Integer>();
        Observable.just(1, 1, 2, 2, 3, 3, 3).subscribe(new OnChangeSubscriber<Integer>(testSubscriber));
        testSubscriber.assertCompleted();
        testSubscriber.assertNoErrors();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValues(1, 2, 3);
    }
}