package com.nvans.game.wordcatcher.logics;

import com.badlogic.gdx.math.Rectangle;

/**
 *
 * Letter class
 *
 * Created by nvans on 28.11.2014.
 */
public class Letter extends Rectangle {
    private String letter;

    public Letter(String letter) {
        super();
        this.letter = letter;
    }

    public String getLetter() {
        return letter;
    }
}
