package com.mygdx.maykornercards;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class MPTScreen  extends ScreenAdapter {

     MyGdxGameTwoCards game;

     Texture img;

     TextField favcolor;
     TextField animal;
     TextField waterfall;
     TextField white;

     TextField whycolor1;
     TextField whycolor2;
     TextField whycolor3;

    TextField whyanimal1;
    TextField whyanimal2;
    TextField whyanimal3;

    TextField whywater1;
    TextField whywater2;
    TextField whywater3;

    TextField whywhite1;
    TextField whywhite2;
    TextField whywhite3;

    Stage stage;

    float r = MathUtils.random();
    float g = MathUtils.random();
    float b = MathUtils.random();

    float circleX = 200;
    float circleY = 100;

    public MPTScreen(MyGdxGameTwoCards game) {
       this.game = game;
    }

    public void show(){

        /*
        Stage stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        //TextField favcolor = new TextField("", skin);

        TextField favcolor = new TextField("", new TextField.TextFieldStyle(game.newfont2,Color.BLACK,null,null,null));
        favcolor.setX(.125f);
        favcolor.setY(.80f);
        favcolor.setWidth(30);
        favcolor.setHeight(30);
        stage.addActor(favcolor);
        */

        Gdx.input.setInputProcessor(new InputAdapter() {

            @Override
            public boolean touchDown (int x, int y, int pointer, int button) {
                r = MathUtils.random();
                g = MathUtils.random();
                b = MathUtils.random();
                if (Gdx.input.isTouched()) {
                    circleX = Gdx.input.getX();
                    circleY = Gdx.graphics.getHeight() - Gdx.input.getY();
                    game.setScreen(new Basic3DScreen(game));
                }
                return true;
            }


        });


    }

    public void render(float delta) {

        /*
        Stage stage = new Stage();
        String underline = ">";
        TextField.TextFieldStyle style = new TextField.TextFieldStyle(game.newfont2,
                Color.BLACK,
                null,
                null,
                null);

        TextField.TextFieldListener listen = new TextField.TextFieldListener() {
            String s = "";

            @Override
            public void keyTyped(TextField textField, char c) {
                if(c != Input.Keys.ENTER) {
                    s += String.valueOf(c);
                    Gdx.app.log("listen", s);
                }
                Gdx.app.log("listen", s);
                textField.setText(s);
            }
        };
         */
        //Gdx.input.setInputProcessor(stage);

        //------------------------------------------------------------------
        /*
        TextField favcolor = new TextField(underline, style);
        favcolor.setX(Gdx.graphics.getWidth() * .125f);
        favcolor.setY(Gdx.graphics.getHeight() * .80f);
        favcolor.setWidth(300);
        favcolor.setHeight(30);
        favcolor.setTextFieldListener(listen);
        stage.addActor(favcolor);
         */

        //-------------------------------------------------------------------
        Gdx.gl.glClearColor(255.0f, 255.0f, 255.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        img = new Texture("mbadlogic.jpg");

        String text1= "The Maykorner Personality Test";
        String text2= "On the internets since 1994";
        GlyphLayout layout1 = new GlyphLayout(game.newfont,text1);
        GlyphLayout layout2 = new GlyphLayout(game.newfont,text2);


        game.batch.begin();
        game.newfont.draw(game.batch, text1, Gdx.graphics.getWidth()/2f - layout1.width/2f, Gdx.graphics.getHeight() * 1.0f);
        game.newfont.draw(game.batch, text2, Gdx.graphics.getWidth()/2f-layout2.width/2f, Gdx.graphics.getHeight() * .95f);

        game.newfont2.draw(game.batch,"What's your favorite color?",Gdx.graphics.getWidth() * .125f, Gdx.graphics.getHeight() * .85f);
        game.newfont2.draw(game.batch,"Give three words that tell why:",Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .85f);
        game.newfont2.draw(game.batch,"How you see yourself",Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .80f);

        game.newfont2.draw(game.batch,"What animal would you like to be?",Gdx.graphics.getWidth() * .125f, Gdx.graphics.getHeight() * .70f);
        game.newfont2.draw(game.batch,"Give three words that tell why:",Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .70f);
        game.newfont2.draw(game.batch,"How others see you",Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .65f);

        game.newfont2.draw(game.batch,"Think about a waterfall...",Gdx.graphics.getWidth() * .125f, Gdx.graphics.getHeight() * .55f);
        game.newfont2.draw(game.batch,"Give three feelings you have now:",Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .55f);
        game.newfont2.draw(game.batch,"How you think of love",Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .50f);

        game.newfont2.draw(game.batch,"Picture the color white...",Gdx.graphics.getWidth() * .125f, Gdx.graphics.getHeight() * .40f);
        game.newfont2.draw(game.batch,"Give three feelings you have now:",Gdx.graphics.getWidth() * .5f, Gdx.graphics.getHeight() * .40f);
        game.newfont2.draw(game.batch,"How you think of death",Gdx.graphics.getWidth() * .25f, Gdx.graphics.getHeight() * .35f);

        game.batch.draw(img, 0, 0);
        game.batch.end();

        //stage.draw();
        //stage.act();


    }

}
