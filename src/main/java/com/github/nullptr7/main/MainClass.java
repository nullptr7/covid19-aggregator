package com.github.nullptr7.main;

import io.reactivex.Flowable;

public class MainClass {

    public static void main(String[] args) {

        Flowable<Integer> flow1 = Flowable.just(1, 2, 3, 4, 5);
        Flowable<Integer> flow2 = Flowable.just(1, 5, 2, 6, 7, 9, 10);

    }
}
