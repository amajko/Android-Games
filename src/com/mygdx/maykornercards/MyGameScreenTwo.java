package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class MyGameScreenTwo extends ScreenAdapter {

    MyGdxGameTwoCards game;

    //circles
    float circleradius = 75;
    float redcircleradius = 75;
    float greencircleradius = 75;

    float greencircleX = 300;
    float greencircleY = 150;
    float greenxSpeed = 4;
    float greenySpeed = 3;

    float redcircleX = 450;
    float redcircleY = 250;
    float redxSpeed = 4;
    float redySpeed = 3;

    //rects
    float whiterectwidth = 150;
    float whiterectheight = 50;
    float whiterectX = Gdx.graphics.getWidth()/2-whiterectwidth/2;
    float whiterectY = Gdx.graphics.getHeight()/2 - whiterectheight/2;
    boolean isrectTouched = false;


    int wins = 0;
    int losses = 0;

    MyFactoryScreen myfactoryscreen;

    public MyGameScreenTwo(final MyGdxGameTwoCards game) {

        this.game = game;
        myfactoryscreen = new MyFactoryScreen(game);

    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;//why - y?: cause touch is y-down but items are drawn y-up!

                game.debugrenderY = renderY;
                game.debugx = x;
                game.debugy = y;

                isrectTouched = false;

                //game play-----
                if (Vector2.dst(greencircleX, greencircleY, x, renderY) < greencircleradius) {
                    if(MyStaticItems.playsound){MyStaticItems.sound.play();}
                    wins++;
                }
                if (Vector2.dst(redcircleX, redcircleY, x, renderY) < redcircleradius) {
                    if(MyStaticItems.playsound){MyStaticItems.losesound.play();}
                    losses++;
                }

                if (Vector2.dst(whiterectX+whiterectwidth/2, whiterectY+whiterectheight/2, x, renderY) <
                        Math.min(whiterectheight,whiterectwidth)){
                    isrectTouched = true;
                }

                //static items-----
                //exit
                if (x>Gdx.graphics.getWidth()-MyStaticItems.exit.getWidth()
                &&
                renderY>Gdx.graphics.getHeight()-MyStaticItems.exit.getHeight())
                {
                    //myfactoryscreen.writeData("test","test.csv");//if no owkr fone dont use! see myfactscrn

                    Gdx.app.exit();
                }

                //continue
                if (x>Gdx.graphics.getWidth()-MyStaticItems.continuation.getWidth()
                        &&
                        renderY<0+MyStaticItems.continuation.getHeight())
                {
                    game.setScreen(new MyGameScreenTwo(game));
                }

                //trademark
                if (x<MyStaticItems.trademark.getWidth()
                        &&
                        renderY<MyStaticItems.trademark.getHeight())
                {
                    //game.setScreen(new MyGameScreenTwoCardsUseFact(game));

                }

                //cheat
                if (x>0+ game.heightfrombottom&&x<0+ game.heightfrombottom*4
                &&renderY>Gdx.graphics.getHeight()- game.heightfrombottom&&renderY<Gdx.graphics.getHeight())
                {
                    wins+=1;
                    if (losses>0){losses-=1;}
                }


                //sshh
                if(x>0 && x<MyStaticItems.sshh.getWidth()
                &&renderY>Gdx.graphics.getHeight()/2/*-MyStaticItems.sshh.getHeight()/2*/ &&
                        renderY<Gdx.graphics.getHeight()/2/*-MyStaticItems.sshh.getHeight()/2*/+MyStaticItems.sshh.getHeight())
                {
                    if(MyStaticItems.playsound) {
                        MyStaticItems.playsound = false;
                    }else if (!MyStaticItems.playsound){
                        MyStaticItems.playsound = true;
                    }
                }

                //center
                //       game.batch.draw(MyStaticItems.center,0,Gdx.graphics.getHeight()/2-MyStaticItems.center.getHeight());
                if(x>0 && x<MyStaticItems.center.getWidth()
                        &&renderY>Gdx.graphics.getHeight()/2-MyStaticItems.center.getHeight() &&
                        renderY<Gdx.graphics.getHeight()/2-MyStaticItems.center.getHeight()+MyStaticItems.center.getHeight())
                {
                    redcircleX = Gdx.graphics.getWidth()/2/*-redcircleradius*/;
                    redcircleY = Gdx.graphics.getHeight()/2/*-redcircleradius*/;
                    greencircleX = Gdx.graphics.getWidth()/2/*-redcircleradius*/;
                    greencircleY = Gdx.graphics.getHeight()/2/*-redcircleradius*/;
                }


                return true;
            }

            //more game play
            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                int renderY = Gdx.graphics.getHeight() - screenY;//why - y?: cause touch is y-down but items are drawn y-up!

                game.debugscreenX = screenX;
                game.debugscreenY = screenY;

                //if(screenX>whiterectX && screenX<whiterectX+whiterectwidth
               /* &&
                screenY>whiterectY && renderY<whiterectY+whiterectheight)*/
                //{
                if(isrectTouched){
                    if(screenX<Gdx.graphics.getWidth()-whiterectwidth/*- game.heightfrombottom*/ && screenX>0+ game.heightfrombottom){
                    whiterectX = screenX;}
                    if(renderY<Gdx.graphics.getHeight()-whiterectheight- game.heightfrombottom && renderY>0+ game.heightfrombottom){
                    whiterectY = renderY;}

                    if(Intersector.overlaps(new Circle(redcircleX,redcircleY, redcircleradius),
                            new Rectangle(whiterectX,whiterectY,whiterectwidth,whiterectheight)))
                    {
                        if(MyStaticItems.playsound){
                        MyStaticItems.huh.play();}
                        redxSpeed += 1;
                        redySpeed += 1;
                    }
                    if(Intersector.overlaps(new Circle(greencircleX,greencircleY, greencircleradius),
                            new Rectangle(whiterectX,whiterectY,whiterectwidth,whiterectheight)))
                    {
                        if(MyStaticItems.playsound){
                        MyStaticItems.go.play();}
                        greenxSpeed += 1;
                        greenySpeed += 1;
                    }
                }

                return super.touchDragged(screenX, screenY, pointer);

            }
        });

            myfactoryscreen.popTextInputPlayer(MyStaticItems.yourname, MyStaticItems.player, "");

      }
    @Override
    public void render(float delta) {

        //movement
        greencircleX += greenxSpeed;
        greencircleY += greenySpeed;
        redcircleX += redxSpeed;
        redcircleY += redySpeed;

        //keep things within edges
        greenxSpeed = myfactoryscreen.checkEdgesCirle(greencircleX-greencircleradius,0,greencircleX+greencircleradius, Gdx.graphics.getWidth(), greenxSpeed);
        greenySpeed = myfactoryscreen.checkEdgesCirle(greencircleY-greencircleradius,0,greencircleY+greencircleradius, Gdx.graphics.getHeight(), greenySpeed);
        redxSpeed = myfactoryscreen.checkEdgesCirle(redcircleX-redcircleradius, 0, redcircleX+redcircleradius,Gdx.graphics.getWidth(), redxSpeed);
        redySpeed = myfactoryscreen.checkEdgesCirle(redcircleY-redcircleradius,0,redcircleY+redcircleradius, Gdx.graphics.getHeight(), redySpeed);
        //here, sound repeats over screens cause in render mthod

        if(Intersector.overlaps(new Circle(redcircleX,redcircleY, redcircleradius),new Circle(greencircleX,greencircleY, greencircleradius)))
        {
            if(MyStaticItems.playsound){
            MyStaticItems.gun.play();}
            if (redcircleradius*2<=Gdx.graphics.getHeight()){
            redcircleradius+=.25;
            }else
            {
                redcircleradius = circleradius;
            }
            if (greencircleradius>=1) {
                greencircleradius -= .25;
            }
            else
            {
                greencircleradius = circleradius;
            }

        }

        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //draw static items (standard for all screens)
        game.batch.begin();
        game.batch.draw(MyStaticItems.trademark, 0, 0);
        game.batch.draw(MyStaticItems.exit,
                Gdx.graphics.getWidth()-MyStaticItems.exit.getWidth(),
                Gdx.graphics.getHeight()-MyStaticItems.exit.getHeight());
        game.batch.draw(MyStaticItems.continuation,
                Gdx.graphics.getWidth()-MyStaticItems.continuation.getWidth(),
                0);
        game.batch.draw(MyStaticItems.sshh,0,Gdx.graphics.getHeight()/2/*-MyStaticItems.sshh.getHeight()/2*/);
        game.batch.draw(MyStaticItems.center,0,Gdx.graphics.getHeight()/2-MyStaticItems.center.getHeight());
        game.font.draw(game.batch, MyStaticItems.wintext+wins+MyStaticItems.losetext+losses+
                MyStaticItems.fortext+MyStaticItems.player,
                0, Gdx.graphics.getHeight());

        //debug
        if(game.debug){
            game.newfont3.draw(game.batch, myfactoryscreen.getdebug()
                            + " xrsp: "+redxSpeed+" yrsp: "+redySpeed
                            + " xgsp: "+greenxSpeed+" ygsp: "+greenySpeed
                    /*+ " sound: "+MyStaticItems.playsound*/
                    + " r: "+isrectTouched
                    ,
                    0, Gdx.graphics.getHeight() - game.heightfrombottom*MyStaticItems.debugheightfactor);
        }

        game.batch.end();

        //draw game

        myfactoryscreen.makeCircle(greencircleX, greencircleY, greencircleradius, ShapeRenderer.ShapeType.Filled, Color.GREEN);
        myfactoryscreen.makeCircle(redcircleX, redcircleY, redcircleradius, ShapeRenderer.ShapeType.Filled, Color.RED);

        myfactoryscreen.makeRectangle(whiterectX, whiterectY,
                whiterectwidth,whiterectheight,
                ShapeRenderer.ShapeType.Filled, Color.GRAY);


    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}