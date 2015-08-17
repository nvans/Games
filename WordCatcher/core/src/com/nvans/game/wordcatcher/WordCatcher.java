package com.nvans.game.wordcatcher;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.nvans.game.wordcatcher.screens.GameScreen;

public class WordCatcher
        extends Game {

    private WordCatcher game;
    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setScale(2, 2.0f);

        setScreen(new GameScreen(this));
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        super.dispose();
        batch.dispose();
    }
}
