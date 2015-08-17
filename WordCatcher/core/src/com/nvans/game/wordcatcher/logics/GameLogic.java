package com.nvans.game.wordcatcher.logics;

import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Game logic class
 *
 * Created by nvans on 28.11.2014.
 */
public class GameLogic {

    private Array<String> letters;

    private Random rnd;

    public GameLogic(){

    }


    /**
     *
     * Method return a random english upper-case letter
     *
     */
    public String getRandomLetter(){
        if (letters == null) {
            letters = new Array<String>();
            char a = 'A';
            for (int i = 0; i < 25 ; i++) {
                letters.add(String.valueOf(a++));
            }
        }

        if (rnd == null) {
            rnd = new Random();
        }
        return letters.get(rnd.nextInt(25));
    }
}
