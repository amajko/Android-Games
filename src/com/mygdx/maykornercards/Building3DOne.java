package com.mygdx.maykornercards;

import com.badlogic.gdx.*;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Building3DOne extends ApplicationAdapter implements InputProcessor {

    public PerspectiveCamera cam;

    public Model model;
    public ModelInstance instance;

    public ModelBatch modelBatch;
    public Environment environment;

    Building3DInputManager inputManager;

    Vector3 tp = new Vector3();
    boolean dragging;




    public void create() {

        //inputManager = new Building3DInputManager();
        //Gdx.input.setInputProcessor(inputManager);


        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        cam.position.set(10f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        Material mat = new
                Material(ColorAttribute.createDiffuse(Color.BLUE));
        model = modelBuilder.createBox(5, 5, 5, mat,
                VertexAttributes.Usage.Position |
                        VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new
                ColorAttribute(ColorAttribute.AmbientLight,
                0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -
                1f, -0.8f, -0.2f));

    }

    public void render() {


        movementkeyssortof();
        rotatekeyssortof();
        //scale();
        //updateTransformation();

       // Gdx.input.setOnscreenKeyboardVisible(true);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();

       //inputManager.update();//when ready

    }

    public void dispose() {
        model.dispose();
    }

    Vector3 position = new Vector3();
    private void movement() {
        instance.transform.getTranslation(position);
        position.x += Gdx.graphics.getDeltaTime();
        //instance.transform.setTranslation(position);
    }

    /*
    how to do with mouse or finger on fone screen?
     */
    private void movementkeys() {
        instance.transform.getTranslation(position);
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.x += Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.z += Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.z -= Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.x -= Gdx.graphics.getDeltaTime();
        }
       // instance.transform.setTranslation(position);
    }

    /*
   how to do with mouse or finger on fone screen?
    */
    private void movementkeyssortof() {
        Input.TextInputListener textListener = new Input.TextInputListener() {
            @Override
            public void input(String text) {

            }

            @Override
            public void canceled() {

            }
        };
        instance.transform.getTranslation(position);
        if (Gdx.input.isTouched()) {
            position.x += Gdx.graphics.getDeltaTime();
            position.y += Gdx.graphics.getDeltaTime();
            position.z += Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            position.x += Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            position.z += Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            position.z -= Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            position.x -= Gdx.graphics.getDeltaTime();
        }
        // instance.transform.setTranslation(position);
    }

    float rotation;
    private void rotate() {
        rotation = (rotation + Gdx.graphics.getDeltaTime() * 100) %
                360;
        instance.transform.setFromEulerAngles(0, 0, rotation).trn(position.x,
                position.y, position.z);
    }

    private void updateTransformation(){
        instance.transform.setFromEulerAngles(0, 0,
                rotation).trn(position.x, position.y,
                position.z).scale(scale,scale,scale);
    }


    /*
    how to do with mouse or finger on fone screen?
     */
    private void rotatekeys() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            instance.transform.rotate(Vector3.X,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            instance.transform.rotate(Vector3.Y,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            instance.transform.rotate(Vector3.Z,
                    Gdx.graphics.getDeltaTime() * 100);
    }

    /*
     how to do with mouse or finger on fone screen?
      */
    private void rotatekeyssortof() {
        if (Gdx.input.isTouched()) {
            instance.transform.rotate(Vector3.X,
                    Gdx.graphics.getDeltaTime() * 100);
            instance.transform.rotate(Vector3.Y,
                    Gdx.graphics.getDeltaTime() * 100);
            instance.transform.rotate(Vector3.Z,
                    Gdx.graphics.getDeltaTime() * 100);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            instance.transform.rotate(Vector3.X,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            instance.transform.rotate(Vector3.Y,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            instance.transform.rotate(Vector3.Z,
                    Gdx.graphics.getDeltaTime() * 100);
    }

    boolean increment = true;
    float scale = 1;
    void scale() {
        if (increment) {
            scale = (scale + Gdx.graphics.getDeltaTime() / 5);
            if (scale >= 1.5f) {
                increment = false;
            } else {
                scale = (scale - Gdx.graphics.getDeltaTime() / 5);
                if (scale <= 0.5f)
                    increment = true;
            }
        }


    }

    public void movementtouch(){

        if (inputManager.touchDragged(Float.valueOf(position.x).intValue(),
                Float.valueOf(position.y).intValue(),
                Building3DInputManagerTwo.pointer)){
            instance.transform.getTranslation(position);
            position.x += Gdx.graphics.getDeltaTime();
            position.y += Gdx.graphics.getDeltaTime();

        }
    }

    public void rotatetouch(){

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        // ignore if its not left mouse button or first touch pointer
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        cam.unproject(tp.set(screenX, screenY, 0));
        dragging = true;
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        if (!dragging) return false;
        cam.unproject(tp.set(position.x, position.y, 0));
        position.x += Gdx.graphics.getDeltaTime();
        position.y += Gdx.graphics.getDeltaTime();


        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        // we can also handle mouse movement without anything pressed
		cam.unproject(tp.set(position.x, position.y, 0));
        position.x += Gdx.graphics.getDeltaTime();
        position.y += Gdx.graphics.getDeltaTime();

        return false;

    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        if (button != Input.Buttons.LEFT || pointer > 0) return false;
        cam.unproject(tp.set(position.x, position.y, 0));
        position.x += Gdx.graphics.getDeltaTime();
        position.y += Gdx.graphics.getDeltaTime();
        dragging = false;
        return true;
    }

}