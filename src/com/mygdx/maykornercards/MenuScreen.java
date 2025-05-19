package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    private Stage stage = new Stage();
    private com.mygdx.maykornercards.Box2DGame parent; // a field to store our orchestrator




    // our constructor with a Box2DTutorial argument
    public MenuScreen(com.mygdx.maykornercards.Box2DGame box2dGame){
        parent = box2dGame;     // setting the argument to our field.
        stage = new Stage(new ScreenViewport());
        //Gdx.input.setInputProcessor(stage);



    }

    @Override
    public void show() {
        // TODO Auto-generated method stub
                      /*
          Let’s see, we were unable to exit the application with the exit button after we viewed the preferences screen.
          This is because we only set the stage as the input processor when we create the screen. This is not what we want.
          We want it to be the controller every time we show the screen. So to fix this issue we just move the “Gdx.input.setInputProcessor(stage);”
          from the constructor to the show method.
           */
        Gdx.input.setInputProcessor(stage);

        // Create a table that fills the screen. Everything else will go inside this table.
        Table table = new Table();
        table.setFillParent(true);
        //table.setDebug(true);//inally, let’s get rid off the debug visuals by commenting out the “table.setDebug(true);”
        stage.addActor(table);

        Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));

        TextButton newGame = new TextButton("New Game", skin);
        TextButton preferences = new TextButton("Preferences - use only once", skin);
        TextButton exit = new TextButton("Exit", skin);

        table.add(newGame).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(preferences).fillX().uniformX();
        table.row();
        table.add(exit).fillX().uniformX();


        // create button listeners
        exit.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(com.mygdx.maykornercards.Box2DGame.APPLICATION);
            }
        });

        preferences.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                parent.changeScreen(Box2DGame.PREFERENCES);
            }
        });

    }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

        /*
        This tells the stage that the screen size has changed and the viewport should be recalculated.
        The true boolean, the last argument is what tells the camera to recenter.
         */
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
