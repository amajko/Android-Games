package com.mygdx.maykornercards;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class EndScreen implements Screen {

    private Stage stage = new Stage();
    private com.mygdx.maykornercards.Box2DGame parent; // a field to store our orchestrator

    // our constructor with a Box2DTutorial argument
    public EndScreen(Box2DGame box2dGame){
        parent = box2dGame;     // setting the argument to our field.
    }


    @Override
    public void show() {
        // TODO Auto-generated method stub
    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
    }
}
