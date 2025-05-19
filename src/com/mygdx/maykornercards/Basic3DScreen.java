package com.mygdx.maykornercards;

import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;

public class Basic3DScreen extends ScreenAdapter {

    float r = MathUtils.random();
    float g = MathUtils.random();
    float b = MathUtils.random();

    float circleX = 200;
    float circleY = 100;

    MyGdxGameTwoCards game;

    public PerspectiveCamera cam;
    public Model model;
    public ModelInstance instance;
    ModelBatch modelBatch;
    Environment environment;
    CameraInputController camController;

        public Basic3DScreen(MyGdxGameTwoCards game) {
            this.game = game;

            modelBatch = new ModelBatch();

            cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            cam.position.set(10f, 10f, 10f);
            cam.lookAt(0,0,0);
            cam.near = 1f;
            cam.far = 300f;
            cam.update();

            ModelBuilder modelBuilder = new ModelBuilder();
            model = modelBuilder.createBox(5f, 5f, 5f,
                    new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            instance = new ModelInstance(model);


            environment = new Environment();
            environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
            environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, 20f, -20f, -50f));

            camController = new CameraInputController(cam);
            Gdx.input.setInputProcessor(camController);

        }

    @Override
    public void render (float delta) {
        Gdx.gl.glClearColor(0f, 0, 0, 1);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        camController.update();

        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();

    }



    @Override
    public void dispose () {
        model.dispose();
    }


}




