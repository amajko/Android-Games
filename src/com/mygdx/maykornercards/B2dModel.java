package com.mygdx.maykornercards;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

public class B2dModel {
    public World world;
    private Body bodyd;//dynamic body
    private Body bodys;//static body (floor)
    private Body bodyk;//kinetic body
    public static String watername = "IAMTHESEA";
    public boolean isSwimming;
    private Body player;

    //x,y 0,0 for bodies in center of screen like crosshairs

    public B2dModel(){
        world = new World(new Vector2(0,-10f), true);
        world.setContactListener(new B2dContactListener(this));

        //createObject(); //1) this is original tutorial
        createFloor();//1) and 2)
        //createMovingObject(); //1) this is original tutorial

        // get our body factory singleton and store it in bodyFactory
        BodyFactory bodyFactory = BodyFactory.getInstance(world);

        /*
        //1) this is original tutorial
        // add a new rubber ball at position 1, 1
        bodyFactory.makeCirclePolyBody(1, 1, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody);

        // add a new steel ball at position 4, 1
        bodyFactory.makeCirclePolyBody(4, 1, 2, BodyFactory.STEEL, BodyDef.BodyType.DynamicBody);

        // add a new stone at position -4,1
        bodyFactory.makeCirclePolyBody(-4, 1, 2, BodyFactory.STONE, BodyDef.BodyType.DynamicBody);
        //1)
        */

        //2) this creates water action
        // add a player
            player = bodyFactory.makeBoxPolyBody(1, 1, 2, 2, BodyFactory.RUBBER, BodyDef.BodyType.DynamicBody,false);

        // add some water
        Body water =  bodyFactory.makeBoxPolyBody(1, -8, 40, 4, BodyFactory.RUBBER, BodyDef.BodyType.StaticBody,false);

        /*
        Did you notice our player gets shot up in the air like it did previously?
        That is because our water body is a static body and we’ve still got our contact listener shooting up any bodies that hit a static body.
        How do we fix this? we can use the body’s setUserData method to set a unique name for our body which we will
        then check for in our contact listener.
         */
        water.setUserData(watername);
        // make the water a sensor so it doesn't obstruct our player
        bodyFactory.makeAllFixturesSensors(water);
        //2)
    }

    // our game logic here
    public void logicStep(float delta){
        if(isSwimming){
            player.applyForceToCenter(0, 50, true);
        }

        world.step(delta , 3, 3);//time, time, time...
    }

    /*
  This private method can only be called in our model our screens or orchestrator class have no access to this method because it is private.
   It creates a BodyDef which is like the data for the physical object, it will hold information such as,
    type of body, location, speed, rotation etc. Next we add it to the world so the world knows about the object and can update it.
     We then create a FixtureDef which is the data for a physical body part.
      We could create a body with multiple parts like an ice cream has a cone and the ice cream part,
       we would want the cone to have a low density because it’s light whereas the ice cream should be denser as it is heavier.
        We then use the bodies createFixture method to add that fixture to the body.
         We finally dispose of the shape as it is no longer needed and we want our memory back
   */
    private void createObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(0,0);


        // add it to the world
        bodyd = world.createBody(bodyDef);

        // set the shape (here we use a box 1 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyd.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    /*
    The static body is almost identical to our dynamic body except 
    we don’t need to define a density and we can just use the shape in our body’s createFixture method.
     */
    private void createFloor() {
        // create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, -10);
        // add it to the world
        bodys = world.createBody(bodyDef);
        // set the shape (here we use a box 50 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(50, 1);
        // create the physical object in our body)
        // without this our body would just be data in the world
        bodys.createFixture(shape, 0.0f);
        // we no longer use the shape object here so dispose of it.
        shape.dispose();
    }

    /*
    Finally, we can make our Kinematic Body
    This is exactly the same as our dynamic code except we have set the linear velocity.
      */
    private void createMovingObject(){

        //create a new body definition (type and location)
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(0,-12);


        // add it to the world
        bodyk = world.createBody(bodyDef);

        // set the shape (here we use a box 1 meters wide, 1 meter tall )
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(1,1);

        // set the properties of the object ( shape, weight, restitution(bouncyness)
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;

        // create the physical object in our body)
        // without this our body would just be data in the world
        bodyk.createFixture(shape, 0.0f);

        // we no longer use the shape object here so dispose of it.
        shape.dispose();

        bodyk.setLinearVelocity(0, 0.75f);
    }

}