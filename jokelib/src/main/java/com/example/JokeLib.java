package com.example;

import java.util.Random;

// source: http://www.jokes4us.com/miscellaneousjokes/cleanjokes.html
public class JokeLib {
    public String getJoke() {
        Random random = new Random();
        return cleanJokes[random.nextInt(cleanJokes.length-1)];
        //return "This is a joke!;
    }
    private static final String[] cleanJokes = new String[] {
            "What does a nosey pepper do? Gets jalapeno business!",
            "What do you call a fake noodle? An Impasta",
            "What do you call an alligator in a vest? An Investigator",
            "What happens if you eat yeast and shoe polish? Every morning you'll rise and shine!",
            "What's the difference between a guitar and a fish? You can't tuna fish.",
            "What do you call a pile of kittens? a meowntain",
            "What do you call a baby monkey? A Chimp off the old block.",
            "Did you hear about the race between the lettuce and the tomato? The lettuce was a head and the tomato was trying to ketchup",
            "Did you hear about the hungry clock? It went back four seconds.",
            "Did you hear about that new broom? It's sweeping the nation!",
            "What do you call an elephant that doesn't matter? A: An irrelephant.",
            "What do lawyers wear to court? Lawsuits!"
















    };


}
