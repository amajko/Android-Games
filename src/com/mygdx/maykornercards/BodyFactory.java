package com.mygdx.maykornercards;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

/*
otice how we have used “private BodyFactory(World world)” instead of using public like usual.
That is because we’re going to make this a singleton class.
That means only 1 BodyFactory object will ever be created as we will never need more than 1 and instead of creating
a new body factory everytime we want to make a body and using up more ram, we will just use the one already created.

To finish turning this into a singleton class we need a public static method that returns the BodyFactory object
if one exists or creates one if none exist. The code for that is:
 */
public class BodyFactory {

    private static final double DEGTORAD = 0.0174533f;
    private static BodyFactory thisInstance;
    private World world;

    public static final int STEEL = 0;
    public static final int WOOD = 1;
    public static final int RUBBER = 2;
    public static final int STONE = 3;


    private BodyFactory(World world) {
        this.world = world;


    }

    public static BodyFactory getInstance(World world) {
        if (thisInstance == null) {
            thisInstance = new BodyFactory(world);
        }
        return thisInstance;
    }

    static public FixtureDef makeFixture(int material, Shape shape) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        switch (material) {
            case 0:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.3f;
                fixtureDef.restitution = 0.1f;
                break;
            case 1:
                fixtureDef.density = 0.5f;
                fixtureDef.friction = 0.7f;
                fixtureDef.restitution = 0.3f;
                break;
            case 2:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0f;
                fixtureDef.restitution = 1f;
                break;
            case 3:
                fixtureDef.density = 1f;
                fixtureDef.friction = 0.9f;
                fixtureDef.restitution = 0.01f;
            default:
                fixtureDef.density = 7f;
                fixtureDef.friction = 0.5f;
                fixtureDef.restitution = 0.3f;
        }
        return fixtureDef;
    }

    public Body makeCirclePolyBody(float posx, float posy, float radius, int material, BodyDef.BodyType bodyType) {
        return makeCirclePolyBody(posx, posy, radius, material, bodyType, false);
    }

    public Body makeCirclePolyBody(float posx, float posy, float radius, int material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        // create a definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        //create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(radius / 2);
        boxBody.createFixture(makeFixture(material, circleShape));
        circleShape.dispose();
        return boxBody;
    }

    public Body makeBoxPolyBody(float posx, float posy, float width, float height, int material, BodyDef.BodyType bodyType) {
        return makeBoxPolyBody(posx, posy, width, height, material, bodyType, false);
    }

    public Body makeBoxPolyBody(float posx, float posy, float width, float height, int material, BodyDef.BodyType bodyType, boolean fixedRotation) {
        // create a definition
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        boxBodyDef.fixedRotation = fixedRotation;

        //create the body to attach said definition
        Body boxBody = world.createBody(boxBodyDef);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(width / 2, height / 2);
        boxBody.createFixture(makeFixture(material, poly));
        poly.dispose();

        return boxBody;
    }

    /*
    Our first Vector2 would be the line (1,5). The first value being how far left the point is, the second being how far down the point is.
     */
    public Body makePolygonShapeBody(Vector2[] vertices, float posx, float posy, int material, BodyDef.BodyType bodyType) {
        BodyDef boxBodyDef = new BodyDef();
        boxBodyDef.type = bodyType;
        boxBodyDef.position.x = posx;
        boxBodyDef.position.y = posy;
        Body boxBody = world.createBody(boxBodyDef);

        PolygonShape polygon = new PolygonShape();
        polygon.set(vertices);
        boxBody.createFixture(makeFixture(material, polygon));
        polygon.dispose();

        return boxBody;
    }

    /*
    he final method we will add for now is the cone shape.
    This is useful for using as a sensor to act as an enemy’s field of view, which is why it’s been added to BodyFactory.

    will create a cone shaped body using cos and sin to plot a few points of a circle and an initial point of 0,0
    to specify the focal point of the cone.

    Additionally, we need to add the constant DEGTORAD which is a float used to convert degrees into radians.
  */
    public void makeConeSensor(Body body, float size) {

        FixtureDef fixtureDef = new FixtureDef();
        //fixtureDef.isSensor = true; // will add in future

        PolygonShape polygon = new PolygonShape();

        float radius = size;
        Vector2[] vertices = new Vector2[5];
        vertices[0] = new Vector2(0, 0);
        for (int i = 2; i < 6; i++) {
            float angle = (float) (i / 6.0 * 145 * DEGTORAD); // convert degrees to radians
            vertices[i - 1] = new Vector2(radius * ((float) Math.cos(angle)), radius * ((float) Math.sin(angle)));
        }
        polygon.set(vertices);
        fixtureDef.shape = polygon;
        body.createFixture(fixtureDef);
        polygon.dispose();
    }

    /*
    We have created a contact listener and got our world bodies to move when certain bodies collide
    This is good as it allows us to be able to say that if our player character is on the ground we are allowed to jump but
    what if our player was swimming and we wanted to allow them to swim up when they’re in the water but not swim up when they are in the air.
    */
    public void makeAllFixturesSensors(Body bod){
        for(Fixture fix :bod.getFixtureList()){
            fix.setSensor(true);
        }
    }

}