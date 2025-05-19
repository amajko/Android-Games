package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class B2dContactListener implements ContactListener {

    private com.mygdx.maykornercards.B2dModel parent;
    Sound dropsound;
    Sound meowsound;

    public B2dContactListener(com.mygdx.maykornercards.B2dModel parent){
        this.parent = parent;
        dropsound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        meowsound = Gdx.audio.newSound(Gdx.files.internal("meow.mp3"));

    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        dropsound.play();

        //dont want stuff to bounce off water. tho it's static
        if(fa.getBody().getUserData() == com.mygdx.maykornercards.B2dModel.watername){
            // we will ad some code here to say our player is in the water
            parent.isSwimming = true;
            return;
        }else if(fb.getBody().getUserData() == B2dModel.watername){
            // we will ad some code here to say our player is in the water
            parent.isSwimming = true;
            return;
        }

        if(fa.getBody().getType() == BodyDef.BodyType.StaticBody){
            parent.isSwimming = false;
            this.shootUpInAir(fa, fb);
        }else if(fb.getBody().getType() == BodyDef.BodyType.StaticBody){
            parent.isSwimming = false;
            this.shootUpInAir(fb, fa);
        }else{
            // neither a nor b are static so do nothing
        }
    }

    @Override
    public void endContact(Contact contact) {
        meowsound.play(.5f);

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private void shootUpInAir(Fixture staticFixture, Fixture otherFixture){
        //meowsound.play(1.0f);
        otherFixture.getBody().applyForceToCenter(new Vector2(-100000,-100000), true);
    }
}