package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;

public class NewBuilding3DGameScreen extends ScreenAdapter {

    MyGdxGameTwoCards game;

    public PerspectiveCamera cam;
    float camx = 10;
    float camy = 10;
    float camz = 10;
    float camfieldY = .67f;
    float camlookx = 0;
    float camlooky = 0;
    float camlookz = 0;
    float camnear = 1;
    float camfar = 300;

    public Model model;
    public ModelInstance instance;
    public ModelBatch modelBatch;
    public Environment environment;

    float boxw = 5;
    float boxh = 5;
    float boxd = 5;

    float ambliter = 0.4f;
    float ambliteg = 0.4f;
    float ambliteb = 0.4f;
    float amblitea = 1;

    float dirliter = .8f;
    float dirliteg = .8f;
    float dirliteb = .8f;
    float dirlitex = -1;
    float dirlitey = -.8f;
    float dirlitez = -.2f;

    public NewBuilding3DGameScreen(MyGdxGameTwoCards game){

        this.game = game;

        cam = new PerspectiveCamera(camfieldY, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        cam.position.set(camx, camy, camz);
        cam.lookAt(camlookx,camlooky, camlookz);
        cam.near = camnear;
        cam.far = camfar;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        Material mat = new
                Material(ColorAttribute.createDiffuse(Color.BLUE));
        model = modelBuilder.createBox(boxw, boxh, boxd, mat,
                VertexAttributes.Usage.Position |
                        VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new
                ColorAttribute(ColorAttribute.AmbientLight,
                ambliter, ambliteg, ambliteb, amblitea));
        environment.add(new DirectionalLight().set(dirliter, dirliteg, dirliteb, -dirlitex, -dirlitey, -dirlitez));

    }

    @Override
    public void show() {


    }

    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT |
                GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(cam);
        modelBatch.render(instance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        model.dispose();
    }
}
