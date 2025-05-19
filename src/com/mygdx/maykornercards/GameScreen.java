package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

//import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;


public class GameScreen extends ScreenAdapter {

    MyGdxGameTwoCards game;

    //Music music;
    Sound sound;
    Sound losesound;

    float circleX = 300;
    float circleY = 150;
    float circleRadius = 50;

    float redcircleX = 425;
    float redcircleY = 225;
    float redcircleRadius = 50;

    float xSpeed = 4;
    float ySpeed = 3;

    float redxSpeed = 4;
    float redySpeed = 3;

    public GameScreen(MyGdxGameTwoCards game) {
        this.game = game;
    }

    @Override
    public void show() {
        sound = Gdx.audio.newSound(Gdx.files.internal("meow.mp3"));
        losesound = Gdx.audio.newSound(Gdx.files.internal("Wipeout.mp3"));

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;
                if (Vector2.dst(circleX, circleY, x, renderY) < circleRadius) {
                    sound.play();
                    //game.setScreen(new MyEndScreen(game));
                }
                if (Vector2.dst(redcircleX, redcircleY, x, renderY) < redcircleRadius) {
                    losesound.play();
                    game.setScreen(new EndScreenLose((MyGdxGameCards) game));
                }
                return true;
            }
        });
     }
    @Override
    public void render(float delta) {
       // music = Gdx.audio.newMusic(Gdx.files.internal("banjo.mp3"));
       // music.setLooping(true);
       // music.play();

        circleX += xSpeed;
        circleY += ySpeed;
        redcircleX += redxSpeed;
        redcircleY += redySpeed;

        if (circleX < 0 || circleX > Gdx.graphics.getWidth()) {
            xSpeed *= -1;
        }

        if (circleY < 0 || circleY > Gdx.graphics.getHeight()) {
            ySpeed *= -1;
        }

        if (redcircleX < 0 || redcircleX > Gdx.graphics.getWidth()) {
           redxSpeed *= -1;
        }

        if (redcircleY < 0 || redcircleY > Gdx.graphics.getHeight()) {
            redySpeed *= -1;
        }

        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(0, 1, 0, 1);
        game.shapeRenderer.circle(circleX, circleY, 75);
        game.shapeRenderer.end();

        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        game.shapeRenderer.setColor(1, 0, 0, 1);
        game.shapeRenderer.circle(redcircleX, redcircleY, 75);
        game.shapeRenderer.end();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }
}