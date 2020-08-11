package com.github.nullptr7.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestMain {


    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>() {{
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
            add(new ArrayList<>());
        }};


        list.stream()
            .flatMap(a -> a.stream()

                           .skip(10))
            .collect(Collectors.toList());


    }
}
