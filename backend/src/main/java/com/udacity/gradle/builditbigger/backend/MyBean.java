package com.udacity.gradle.builditbigger.backend;

import com.manmeet.jokejavalib.JokeFromJavaLibrary;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    public String getData() {
        myData= new JokeFromJavaLibrary().getJoke();
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}