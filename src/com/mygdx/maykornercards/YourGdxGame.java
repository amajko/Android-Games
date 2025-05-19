package com.mygdx.maykornercards;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;

public class YourGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer shapeRenderer;
	Texture img;
	float r = MathUtils.random();
	float g = MathUtils.random();
	float b = MathUtils.random();
	float circleX = 200;
	float circleY = 100;


	@Override
	public void create () {
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		img = new Texture("mbadlogic.jpg");

		//changes color
		Gdx.input.setInputProcessor(new InputAdapter() {

			@Override
			public boolean keyTyped (char key) {
				r = MathUtils.random();
				g = MathUtils.random();
				b = MathUtils.random();
				return true;
			}

			@Override
			public boolean touchDown (int x, int y, int pointer, int button) {
				r = MathUtils.random();
				g = MathUtils.random();
				b = MathUtils.random();
				return true;
			}
		});

}

	@Override
	public void render () {
		//Gdx.gl.glClearColor(1, 0, 0, 1);
		//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//changes color
		if (Gdx.input.isTouched()) {
			circleX = Gdx.input.getX();
			circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
		}

		//moves circle
		if(Gdx.input.isKeyPressed(Input.Keys.W)){
			circleY++;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.S)){
			circleY--;
		}

		if(Gdx.input.isKeyPressed(Input.Keys.A)){
			circleX--;
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.D)){
			circleX++;
		}

		//changes color
		Gdx.gl.glClearColor(r, g, .25f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();

		//moves circle
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		shapeRenderer.setColor(0, 1, 0, 1);
		shapeRenderer.circle(circleX, circleY, 75);
		shapeRenderer.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
