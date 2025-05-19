package com.mygdx.maykornerballs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

public class MyGdxGameBalls extends MyGdxGameTwoBalls {

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	BitmapFont newfont;
	BitmapFont newfont2;
	Float standscale;
	Float nonstandscale;

	public static Stage stage;
	TextButton button;
	TextButton.TextButtonStyle textButtonStyle;

	int heightfrombottom = 20;


	@Override
	public void create () {
		//these are used bt subsequent screens

		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		standscale = 3.0f;
		font.getData().setScale(standscale);

		newfont = new BitmapFont();
		newfont.setColor(Color.BLACK);//color change for MPTScreen
		newfont.getData().setScale(standscale);

		nonstandscale = 2.0f;
		newfont2 = new BitmapFont();
		newfont2.setColor(Color.BLACK);//color change for MPTScreen
		newfont2.getData().setScale(nonstandscale);

		//add stage for future called screens here?
		Stage stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = this.newfont2;
		button = new TextButton("Continue", textButtonStyle);
		button.setPosition(Gdx.graphics.getWidth() / 2,heightfrombottom);
		stage.addActor(button);

        boolean button_pressed = button.addListener(new ChangeListener() {
			private MyGdxGameBalls game;

			@Override
            public void changed(ChangeEvent event, Actor actor) {
                //game.setScreen(new GameScreen(game));
            }
        });

		stage.draw();

		//setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose () {
		batch.dispose();
		shapeRenderer.dispose();
		font.dispose();
	}

	//DONT put a render method in here



}