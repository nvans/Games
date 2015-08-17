package com.nvans.game.wordcatcher.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.nvans.game.wordcatcher.WordCatcher;
import com.nvans.game.wordcatcher.logics.Catcher;
import com.nvans.game.wordcatcher.logics.GameLogic;
import com.nvans.game.wordcatcher.logics.Letter;

import java.util.Iterator;

/**
 * Created by nvans on 28.11.2014.
 */
public class GameScreen
            implements Screen {

    final WordCatcher game;

    final GameLogic gameLogic;

    Texture catcherTexture;
    Catcher catcher;

    Texture letterTexture;

    Array<Letter> letters;

    long lastDropTime;

    OrthographicCamera camera;

    public GameScreen(WordCatcher game) {
        this.game = game;
        this.gameLogic = new GameLogic();

        letterTexture = new Texture(Gdx.files.internal("Textures/droplet.png"));
        catcherTexture = new Texture(Gdx.files.internal("Textures/catcher.png"));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 480, 852);


        catcher = new Catcher();
        catcher.x = 480/2 - 80;
        catcher.y = 20;

        catcher.width = 80;
        catcher.height = 92;

        letters = new Array<Letter>();
        spawnLetters();
    }

    private void spawnLetters() {
        Letter letter = new Letter(gameLogic.getRandomLetter());

        letter.x = MathUtils.random(0, 480 - 64);
        letter.y = 852;
        letter.width = 64;
        letter.height = 64;

        letters.add(letter);

        lastDropTime = TimeUtils.millis();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(catcherTexture, catcher.x, catcher.y, 80, 92);

        for (Letter letterDrop : letters) {
            game.batch.draw(letterTexture, letterDrop.x, letterDrop.y);
            game.font.draw(game.batch, letterDrop.getLetter(), letterDrop.x + 22, letterDrop.y + 40);
        }

        game.batch.end();

        //processing of user action
        catcher.setPosition(camera);


        //check if we need to create a new raindrop
        if (TimeUtils.millis() - lastDropTime > MathUtils.random(150, 10000)) {
            spawnLetters();
        }

        // move the raindrops, remove any that are beneath the bottom edge of
        // the screen or that hit the bucket. In the later case we increase the
        // value our drops counter and add a sound effect.
        Iterator<Letter> iterator = letters.iterator();

        while (iterator.hasNext()) {
            Letter letterDrop = iterator.next();
            letterDrop.y -= 200 * Gdx.graphics.getDeltaTime();

            if (letterDrop.y + 64 < 0) {
                iterator.remove();
            }

            if (letterDrop.overlaps(catcher)) {

                catcher.addLetter(letterDrop.getLetter());
                iterator.remove();
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        letterTexture.dispose();
        catcherTexture.dispose();
    }
}
