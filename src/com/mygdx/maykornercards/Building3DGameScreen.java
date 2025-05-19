package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

public class Building3DGameScreen implements Screen {
    /*Building3DCore*/ MyGdxGameTwoCards game;
    Building3DGameWorld gameWorld;

    public Building3DGameScreen(/*Building3DCore*/MyGdxGameTwoCards game) {
        this.game = game;
        gameWorld = new Building3DGameWorld();
        Gdx.input.setCursorCatched(true);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        gameWorld.render(delta);
    }
    @Override
    public void resize(int width, int height) {
        gameWorld.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        gameWorld.dispose();
    }
    // empty methods from Screen
}
