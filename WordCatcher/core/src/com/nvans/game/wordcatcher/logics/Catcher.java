package com.nvans.game.wordcatcher.logics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 *
 *
 *
 * Created by nvans on 28.11.2014.
 */
public class Catcher extends Rectangle {

    private String currentWord;
    private WordFinder wordFinder;
    private Vector3 position;


    /**
     * Constructors
     */
    // >>>>>
    public Catcher() {
        super();
        currentWord = "";
        wordFinder = WordFinder.getInstance();
        wordFinder.setFileName("Text/words.txt");
    }

    public Catcher(float x, float y, float width, float height) {
        super(x, y, width, height);
        currentWord = "";
        wordFinder = WordFinder.getInstance();
    }

    public Catcher(Rectangle rect) {
        super(rect);
        currentWord = "";
        wordFinder = WordFinder.getInstance();
    }
    // <<<<<


    /**
     *
     * Build word from collected letters
     *
     * @param letter
     */
    public void addLetter(String letter) {
        currentWord += letter;
        System.out.println(currentWord);
        if (currentWord.length() == 3) {
            if (wordFinder.isContain(currentWord)) {
                System.out.println("SUCCESS!");
            } else
                System.out.println("FAIL!");
            currentWord = "";
        }
    }

    /**
     *
     * User actions processing
     *
     * @param camera
     */
    public void setPosition(OrthographicCamera camera) {
        if (position == null) {
            position = new Vector3();
        }

        if (Gdx.input.isTouched()) {
            position.set(Gdx.input.getX(), 0, 0);
            camera.unproject(position);
            this.x = position.x - 80 / 2;
        }
    }
}
