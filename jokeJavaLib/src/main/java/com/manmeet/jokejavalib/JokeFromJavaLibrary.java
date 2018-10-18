package com.manmeet.jokejavalib;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.util.Arrays.asList;

public class JokeFromJavaLibrary {
    private static List<String> jokes = asList(
        "42.7 percent of all statistics are made up on the spot.",
                "99 percent of lawyers give the rest a bad name.",
                "A bartender is just a pharmacist with a limited inventory.",
                "A clean desk is a sign of a cluttered desk drawer.",
                "A clear conscience is usually the sign of a bad memory.",
                "A closed mouth gathers no foot.",
                "A conclusion is the place where you got tired of thinking.",
                "A day without sunshine is like, night.",
                "A diplomat is someone who can tell you to go to hell in such a way that you will look forward to the trip.",
                "A flashlight is a case for holding dead batteries.",
                "All generalisations are false, including this one.",
                "All men are idiots, and I married their King.",
                "Always remember you're unique, just like everyone else.",
                "Always try to be modest and be proud of it!",
                "Anything worth taking seriously is worth making fun of.",
                "Artificial Intelligence usually beats real stupidity.",
                "Assassins do it from behind."
    );

    public String getJoke() {
        String randomJoke = jokes.get(new Random().nextInt(jokes.size()));
        return randomJoke;
    }
}
