package com.mygdx.maykornercards;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Frame-by-frame animation example with LibGDX:
 *
 * http://www.pixnbgames.com/blog/libgdx/frame-by-frame-animations-in-libgdx/
 *
 * @author angel
 */
        //dont work
public class GdxAnimationExample extends ApplicationAdapter {

    // Time while each frame keeps on screen
    private static float FRAME_DURATION = .05f;

    // To draw on screen
    private SpriteBatch batch;

    // Atlas with the definition of the frames "charset.atlas"
    private TextureAtlas charset;

    // Frame that must be rendered at each time
    private TextureRegion currentFrame;

    // Running animation
    private Animation runningAnimation;

    // Elapsed time
    private float elapsed_time = 0f;

    // Auxiliar variables to know where to draw the picture to center it on the screen
    private float origin_x, origin_y;


    @Override
    public void create () {
        batch = new SpriteBatch();

        // Frames loading from "charset.atlas"
        charset = new TextureAtlas( Gdx.files.internal("data/spritesheet.atlas") );

        // Frames that compose the animation "running"
        Array<TextureAtlas.AtlasRegion> runningFrames = charset.findRegions("running");

        // Building the animatino
        runningAnimation = new Animation(FRAME_DURATION, runningFrames, Animation.PlayMode.LOOP);

        // Calculates the x and y position to center the image
        TextureRegion firstTexture = runningFrames.first();
        origin_x = (Gdx.graphics.getWidth()  - firstTexture.getRegionWidth())  / 2;
        origin_y = (Gdx.graphics.getHeight() - firstTexture.getRegionHeight()) / 2;
    }


    @Override
    public void render () {
        // Clear the screen
        Gdx.gl.glClearColor(1.0f, .8f, .667f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Elapsed time
        elapsed_time += Gdx.graphics.getDeltaTime();

        // Getting the frame which must be rendered
        currentFrame = (TextureRegion) runningAnimation.getKeyFrame(elapsed_time);//had to cast

        // Drawing the frame
        batch.begin();
        batch.draw(currentFrame, origin_x, origin_y);
        batch.end();
    }


    @Override
    public void dispose() {
        // Resource releasing
        charset.dispose();
        super.dispose();
    }
}


