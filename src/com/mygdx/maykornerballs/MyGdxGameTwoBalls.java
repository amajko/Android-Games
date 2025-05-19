package com.mygdx.maykornerballs;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MyGdxGameTwoBalls extends Game {

	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	BitmapFont font;
	BitmapFont newfont;
	BitmapFont newfont2;
	BitmapFont newfont3;
	Float standscale;
	Float nonstandscale;

	int heightfrombottom = 20;
	int heightfromtop = 20;

	boolean debug = true;
	int debugrenderY = 0;
	int debugx = 0;
	int debugy = 0;
	int debugscreenX = 0;
	int debugscreenY = 0;

	Stage stage;


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

		newfont3 = new BitmapFont();
		//newfont3.setColor(Color.BLACK);//color change for MPTScreen
		newfont3.getData().setScale(nonstandscale);

		setScreen(new MyGameScreenTwo(this));
		//setScreen(new MyGameScreenTwoCardsUseFact(this));
		//setScreen(new NewBuilding3DGameScreen(this));
	}

	@Override
	public void dispose () {//calld automagicaly
		batch.dispose();
		shapeRenderer.dispose();
		stage.dispose();
	}


	//DONT put a render method in here



}