package com.mygdx.maykornercards;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class GTXMyGameScreenTwo extends ScreenAdapter {

    MyGdxGameTwoCards game;

    //circles
    float circleradius = 75;

    float greencircleX = 300;
    float greencircleY = 150;
    float greenxSpeed = 4;
    float greenySpeed = 3;

    float redcircleX = 425;
    float redcircleY = 225;
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

    //Stage stage;
    Table tableAll;
    TextArea textArealinks;
    TextArea textAreadaze;
    ScrollPane scrolllinks;
    ScrollPane scrolldaze;
    float prefrows = 1000;

    public GTXMyGameScreenTwo(final MyGdxGameTwoCards game) {

        this.game = game;
        myfactoryscreen = new MyFactoryScreen(game);

    }

    @Override
    public void show() {

        //MyStaticItems.ahahahID=0;

        InputProcessor touchProc = (new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;//why - y?: cause touch is y-down but items are drawn y-up!

                game.debugrenderY = renderY;
                game.debugx = x;
                game.debugy = y;

                 //game play-----
                if (Vector2.dst(greencircleX, greencircleY, x, renderY) < circleradius) {
                    //MyStaticItems.sound.play();
                    wins++;
                }
                if (Vector2.dst(redcircleX, redcircleY, x, renderY) < circleradius) {
                    //MyStaticItems.losesound.play();
                    losses++;
                }

                if (Vector2.dst(whiterectX+whiterectwidth/2, whiterectY+whiterectheight/2, x, renderY) <
                        Math.min(whiterectheight,whiterectwidth)){
                    isrectTouched = true;
                }

                //static items-----
                //exit
                if (x>Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.exit.getWidth()
                &&
                renderY>Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight())
                {
                    //myfactoryscreen.writeData("test","test.csv");//if no owkr fone dont use! see myfactscrn

                    Gdx.app.exit();
                }

                //continue
                if (x>Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.continuation.getWidth()
                        &&
                        renderY< com.mygdx.maykornercards.MyStaticItems.continuation.getHeight())
                {
                    game.setScreen(new GTXMyGameScreenTwo(game));
                }

                //trademark
                if (x< com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()
                        &&
                        renderY< com.mygdx.maykornercards.MyStaticItems.trademark.getHeight())
                {
                   // MyStaticItems.ahahah.stop();
                   // game.setScreen(new GTXMyGameScreenTwob(game));


                }

                //files
                if(x> com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()+game.heightfrombottom
                &&
                        x< com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()+game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.file.getWidth()
                        &&
                        renderY>game.heightfrombottom*2
                        &&
                        renderY<game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.file.getHeight()
                )
                {
                    myfactoryscreen.popTextInputFileONE(com.mygdx.maykornercards.MyStaticItems.gtxfiletitle, com.mygdx.maykornercards.MyStaticItems.gtxfilenameOne, "no entry=dirlist");
                }

                if(x>Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.exit.getWidth()*2-game.heightfrombottom
                        &&
                        x<Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.exit.getWidth()*2-game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.file.getWidth()
                        &&
                        renderY>game.heightfrombottom*2
                        &&
                        renderY<game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.file.getHeight()
                )
                {
                    myfactoryscreen.popTextInputFileTWO(com.mygdx.maykornercards.MyStaticItems.gtxfiletitle, com.mygdx.maykornercards.MyStaticItems.gtxfilenameTwo, "no entry=dirlist");
                }

                //sshh
                if(x>0 && x< com.mygdx.maykornercards.MyStaticItems.sshh.getWidth()
                        &&renderY>Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.sshh.getHeight()/2 &&
                        renderY<Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.sshh.getHeight()/2+ com.mygdx.maykornercards.MyStaticItems.sshh.getHeight())
               {
                    if(com.mygdx.maykornercards.MyStaticItems.playsound) {
                        com.mygdx.maykornercards.MyStaticItems.playsound = false;
                    }else if (!com.mygdx.maykornercards.MyStaticItems.playsound){
                        com.mygdx.maykornercards.MyStaticItems.playsound = true;
                    }
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
                    whiterectX = screenX;
                    whiterectY = renderY;
                    isrectTouched = false;
                }

                return super.touchDragged(screenX, screenY, pointer);

            }
        });

        //is for textareas
        game.stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));
        InputProcessor stageProc = game.stage;

        textArealinks = myfactoryscreen.createtextarea(com.mygdx.maykornercards.MyStaticItems.gtxfilenameOne, skin,
                com.mygdx.maykornercards.MyStaticItems.trademark.getWidth(), com.mygdx.maykornercards.MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()- game.heightfrombottom*4,
                Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.trademark.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight()-game.heightfrombottom*4);

        textAreadaze = myfactoryscreen.createtextarea(com.mygdx.maykornercards.MyStaticItems.gtxfilenameTwo, skin,
                Gdx.graphics.getWidth()/2/*-MyStaticItems.trademark.getWidth()*/, com.mygdx.maykornercards.MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()- game.heightfrombottom*4,
                Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.trademark.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight()- game.heightfrombottom*4);

        textArealinks.setPrefRows(prefrows);
        textAreadaze.setPrefRows(prefrows);

        tableAll = myfactoryscreen.createtable(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scrolllinks = myfactoryscreen.createscrollpane(textArealinks, skin,
                com.mygdx.maykornercards.MyStaticItems.trademark.getWidth(), com.mygdx.maykornercards.MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.trademark.getWidth(), Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.trademark.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight(),
                true, true, true);
        scrolldaze = myfactoryscreen.createscrollpane(textAreadaze, skin,
                Gdx.graphics.getWidth()/2/*-MyStaticItems.trademark.getWidth()*/, com.mygdx.maykornercards.MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.trademark.getWidth(),
                Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.trademark.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight(),
                true, true, true);
        
        tableAll.addActor(scrolllinks);
        tableAll.addActor(scrolldaze);
        game.stage.addActor(tableAll);

        InputMultiplexer multiplexer = new InputMultiplexer();
        //multiplexer.addProcessor(touchProc);
        multiplexer.addProcessor(stageProc);
        multiplexer.addProcessor(touchProc);//this order to get both to work
        Gdx.input.setInputProcessor(multiplexer);

        /*
        try {
            slinks = myfactoryscreen.getLinesFromFile("links.txt",1,20);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

    }
    @Override
    public void render(float delta) {

        //movement
        /*
        greencircleX += greenxSpeed;
        greencircleY += greenySpeed;
        redcircleX += redxSpeed;
        redcircleY += redySpeed;

        //keep things within edges
        greenxSpeed = myfactoryscreen.checkEdgesCirle(greencircleX,Gdx.graphics.getWidth(), greenxSpeed);
        greenySpeed = myfactoryscreen.checkEdgesCirle(greencircleY,Gdx.graphics.getHeight(), greenySpeed);
        redxSpeed = myfactoryscreen.checkEdgesCirle(redcircleX,Gdx.graphics.getWidth(), redxSpeed);
        redySpeed = myfactoryscreen.checkEdgesCirle(redcircleY,Gdx.graphics.getHeight(), redySpeed);
        */
        Gdx.gl.glClearColor(0, 0, .25f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        //draw static items (standard for all screens)
        game.batch.begin();
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.trademark, 0, 0);
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.exit,
                Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.exit.getWidth(),
                Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight());
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.continuation,
                Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.continuation.getWidth(),
                0);
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.sshh,0,Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.sshh.getHeight()/2);
      /*
        game.font.draw(game.batch, MyStaticItems.wintext+wins+MyStaticItems.losetext+losses+
                MyStaticItems.fortext+MyStaticItems.player,
                0, Gdx.graphics.getHeight());
         */
        game.font.draw(game.batch, com.mygdx.maykornercards.MyStaticItems.myname, 0, Gdx.graphics.getHeight());

        //debug
        if(game.debug){
            game.newfont3.draw(game.batch, myfactoryscreen.getdebug()
                            /*+ " "+textArealinks.getLines()*/+" "/*+slinks*/
                            /*+ " sid: " + MyStaticItems.ahahahID*/
                            /*+ " sound: "+MyStaticItems.playsound*/
                    ,
                    0, Gdx.graphics.getHeight() - game.heightfrombottom* com.mygdx.maykornercards.MyStaticItems.debugheightfactor);
        }

        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.file, com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()+game.heightfrombottom,game.heightfrombottom*2);
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.file, Gdx.graphics.getWidth()- MyStaticItems.exit.getWidth()*2-game.heightfrombottom,game.heightfrombottom*2);

        game.batch.end();

        //draw game

       // myfactoryscreen.makeCircle(greencircleX, greencircleY, circleradius, ShapeRenderer.ShapeType.Filled, Color.GREEN);
        //myfactoryscreen.makeCircle(redcircleX, redcircleY, circleradius, ShapeRenderer.ShapeType.Filled, Color.RED);

        /*myfactoryscreen.makeRectangle(whiterectX, whiterectY,
                whiterectwidth,whiterectheight,
                ShapeRenderer.ShapeType.Filled, Color.GRAY);*/

         //myfactoryscreen.showtextPlain("links.txt", MyStaticItems.trademark.getWidth(), Gdx.graphics.getHeight() - game.heightfrombottom*4);

        game.stage.act(Gdx.graphics.getDeltaTime());
        game.stage.draw();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }


    public void dispose(){}//call this manly if nec

}