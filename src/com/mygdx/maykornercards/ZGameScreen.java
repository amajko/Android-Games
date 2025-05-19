package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;


public class ZGameScreen extends ScreenAdapter {

    MyGdxGameTwoCards game;

    private com.mygdx.maykornercards.ZGameWorld world;
    private ZGameRenderer renderer;

        public ZGameScreen(MyGdxGameTwoCards game) {
            float screenWidth = Gdx.graphics.getWidth();
            float screenHeight = Gdx.graphics.getHeight();
            float gameWidth = 136;
            float gameHeight = screenHeight / (screenWidth / gameWidth);

            int midPointY = (int) (gameHeight / 2);

            world = new ZGameWorld(midPointY);
            renderer = new ZGameRenderer(world);
        }

        @Override
        public void render(float delta) {
            world.update(delta);
            renderer.render();
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
            // Leave blank
        }

}

