package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class EndScreenLose extends ScreenAdapter {

    MyGdxGameCards game;

    Texture img;

    float r = MathUtils.random();
    float g = MathUtils.random();
    float b = MathUtils.random();

    float circleX = 200;
    float circleY = 100;

    public EndScreenLose(MyGdxGameCards game) {
        this.game = game;
    }

    @Override
    public void show() {
        /*
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean keyDown(int keyCode) {

                if (keyCode == Input.Keys.ENTER) {
                    game.setScreen(new TitleScreen(game));
                }

                return true;
            }
        });
        */

        // replace abv with
        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                r = MathUtils.random();
                g = MathUtils.random();
                b = MathUtils.random();
                if (Gdx.input.isTouched()) {
                    circleX = Gdx.input.getX();
                    circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
                    game.setScreen(new GameScreen(game));
                }
                return true;
            }


        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(.25f, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        img = new Texture("mbadlogic.jpg");

        game.batch.begin();
        game.font.draw(game.batch, "You lose!", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .75f);
        game.font.draw(game.batch, "Touch to restart.", Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .25f);
        game.batch.draw(img, 0, 0);
        game.batch.end();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}
