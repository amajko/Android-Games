package com.mygdx.maykornercards;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public class GTXMyGameScreenTwobWithZoomTry1 extends ScreenAdapter {

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

    com.mygdx.maykornercards.MyFactoryScreen myfactoryscreen;

    //Stage stage;
    //Table tableAll;
    //TextArea textArealinks;
    //TextArea textAreadaze;
    //ScrollPane scrolllinks;
    //ScrollPane scrolldaze;
    float prefrows = 1000;

    ArrayList<String> arlimagenames;
    int nextimage = 0;

    //camera
    OrthographicCamera cam;

    public GTXMyGameScreenTwobWithZoomTry1(final MyGdxGameTwoCards game) {

        this.game = game;
        myfactoryscreen = new MyFactoryScreen(game);
        arlimagenames = myfactoryscreen.getFilesList("gtx.", new String[] {".bmp","jpg",".png"});

        //camera
        // Constructs a new OrthographicCamera, using the given viewport width and height
        // Height is multiplied by aspect ratio.
        float width = (float) Gdx.graphics.getWidth() , height, aspectRatio;
        aspectRatio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        height = width * aspectRatio;

        cam = new OrthographicCamera(width, height);
        cam.position.set(width/2 , height/2 , 0);
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();

        cam.zoom = .5f;

    }

    @Override
    public void show() {

        //MyStaticItems.ahahahID=0;
        com.mygdx.maykornercards.MyStaticItems.gtximagename = arlimagenames.get(nextimage);
        com.mygdx.maykornercards.MyStaticItems.gtximage = new Texture(com.mygdx.maykornercards.MyStaticItems.gtximagename);

        InputProcessor touchProc = (new InputAdapter() {
            @Override
            public boolean touchDown(int x, int y, int pointer, int button) {
                int renderY = Gdx.graphics.getHeight() - y;//why - y?: cause touch is y-down but items are drawn y-up!

                game.debugrenderY = renderY;
                game.debugx = x;
                game.debugy = y;

                isrectTouched = false;

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
                    game.setScreen(new GTXMyGameScreenTwobWithZoomTry1(game));
                }

                //trademark
                if (x< com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()
                        &&
                        renderY< com.mygdx.maykornercards.MyStaticItems.trademark.getHeight())
                {
                    // MyStaticItems.ahahah.stop();
                    // game.setScreen(new MyGameScreenTwo(game));


                }

                //files
                if(x>Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.file.getWidth()/2
                &&
                        x<Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.file.getWidth()/2+ com.mygdx.maykornercards.MyStaticItems.file.getWidth()
                        &&
                        renderY>game.heightfrombottom*2
                        &&
                        renderY<game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.file.getHeight()
                )
                {
                    myfactoryscreen.popTextInputFileIMAGE(com.mygdx.maykornercards.MyStaticItems.gtxfiletitle, com.mygdx.maykornercards.MyStaticItems.gtxfilenamenone, "bmp|jpg|png");
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
                    //myfactoryscreen.popTextInputFileTWO(MyStaticItems.gtxfiletitle, MyStaticItems.gtxfilenameTwo, "");
                }


                /*
                       myfactoryscreen.makeImage(MyStaticItems.gtximage,
                MyStaticItems.trademark.getWidth(),MyStaticItems.trademark.getHeight(),
        Gdx.graphics.getWidth()-MyStaticItems.continuation.getWidth()*2,
                Gdx.graphics.getHeight()-MyStaticItems.exit.getHeight()*2
                );
                 */
                 if(x> com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()&&
                        x<Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.continuation.getWidth()&&
                renderY> com.mygdx.maykornercards.MyStaticItems.trademark.getHeight()&&
                renderY<Gdx.graphics.getHeight()- com.mygdx.maykornercards.MyStaticItems.exit.getHeight())
                {
                    if(nextimage<arlimagenames.size()-1) {
                        nextimage += 1;
                    }else{
                        nextimage = 0;
                    }

                    com.mygdx.maykornercards.MyStaticItems.gtximagename = arlimagenames.get(nextimage);//cant extract this for method parameter
                    com.mygdx.maykornercards.MyStaticItems.gtximage = new Texture(com.mygdx.maykornercards.MyStaticItems.gtximagename);
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
                 }

                return super.touchDragged(screenX, screenY, pointer);

            }
        });

         //was for textareas
        /*
        stage = new Stage();
        Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));
        InputProcessor stageProc = stage;

        textArealinks = myfactoryscreen.createtextarea(MyStaticItems.gtxfilenameOne, skin,
                MyStaticItems.trademark.getWidth(), MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2-MyStaticItems.trademark.getWidth()- game.heightfrombottom*4,
                Gdx.graphics.getHeight()-MyStaticItems.trademark.getHeight()-MyStaticItems.exit.getHeight()-game.heightfrombottom*4);

        textAreadaze = myfactoryscreen.createtextarea(MyStaticItems.gtxfilenameTwo, skin,
                Gdx.graphics.getWidth()/2, MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2-MyStaticItems.trademark.getWidth()- game.heightfrombottom*4,
                Gdx.graphics.getHeight()-MyStaticItems.trademark.getHeight()-MyStaticItems.exit.getHeight()- game.heightfrombottom*4);

        textArealinks.setPrefRows(prefrows);
        textAreadaze.setPrefRows(prefrows);

        tableAll = myfactoryscreen.createtable(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        scrolllinks = myfactoryscreen.createscrollpane(textArealinks, skin,
                MyStaticItems.trademark.getWidth(), MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2-MyStaticItems.trademark.getWidth(), Gdx.graphics.getHeight()-MyStaticItems.trademark.getHeight()-MyStaticItems.exit.getHeight(),
                true, true, true);
        scrolldaze = myfactoryscreen.createscrollpane(textAreadaze, skin,
                Gdx.graphics.getWidth()/2, MyStaticItems.trademark.getHeight(),
                Gdx.graphics.getWidth()/2-MyStaticItems.trademark.getWidth(),
                Gdx.graphics.getHeight()-MyStaticItems.trademark.getHeight()-MyStaticItems.exit.getHeight(),
                true, true, true);
        
        tableAll.addActor(scrolllinks);
        tableAll.addActor(scrolldaze);
        stage.addActor(tableAll);
        */

        InputMultiplexer multiplexer = new InputMultiplexer();
        //multiplexer.addProcessor(touchProc);
        //multiplexer.addProcessor(stageProc);
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

        //camera
        cam.update();

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
        /*
        game.font.draw(game.batch, MyStaticItems.wintext+wins+MyStaticItems.losetext+losses+
                MyStaticItems.fortext+MyStaticItems.player,
                0, Gdx.graphics.getHeight());
         */
        game.font.draw(game.batch, com.mygdx.maykornercards.MyStaticItems.myname + " " + com.mygdx.maykornercards.MyStaticItems.gtximage, 0, Gdx.graphics.getHeight());
        String text = myfactoryscreen.checkFile(com.mygdx.maykornercards.MyStaticItems.gtximagename);
        /*place this inn show so not looped if playing 'file not found'
         ->ahaha sound play*/

        float adjust = text.length()/2* com.mygdx.maykornercards.MyStaticItems.textfontadjnewfont3;
        game.newfont3.draw(game.batch, text,
                (Gdx.graphics.getWidth()/2 - adjust),
                game.heightfrombottom*1.25f);

        //debug
        if(game.debug){
            game.newfont3.draw(game.batch, myfactoryscreen.getdebug()
                            /*+ " "+textArealinks.getLines()*/+" "/*+slinks*/
                   /* +" btl: " +text.length() + " 3adj: "+adjust + " sid: " + MyStaticItems.ahahahID*/
                    + " arl: " + arlimagenames.size()+ " ni: "+nextimage
                    ,
                    0, Gdx.graphics.getHeight() - game.heightfrombottom* com.mygdx.maykornercards.MyStaticItems.debugheightfactor);
        }

        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.file, Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.file.getWidth()/2,game.heightfrombottom*2);
       // game.batch.draw(MyStaticItems.file, Gdx.graphics.getWidth()-MyStaticItems.exit.getWidth()*2-game.heightfrombottom,game.heightfrombottom*2);

        game.batch.end();

        //draw game

       // myfactoryscreen.makeCircle(greencircleX, greencircleY, circleradius, ShapeRenderer.ShapeType.Filled, Color.GREEN);
        //myfactoryscreen.makeCircle(redcircleX, redcircleY, circleradius, ShapeRenderer.ShapeType.Filled, Color.RED);

        /*myfactoryscreen.makeRectangle(whiterectX, whiterectY,
                whiterectwidth,whiterectheight,
                ShapeRenderer.ShapeType.Filled, Color.GRAY);*/

         //myfactoryscreen.showtextPlain("links.txt", MyStaticItems.trademark.getWidth(), Gdx.graphics.getHeight() - game.heightfrombottom*4);

       // stage.act(Gdx.graphics.getDeltaTime());
       // stage.draw();

        myfactoryscreen.makeImage(com.mygdx.maykornercards.MyStaticItems.gtximage,
                com.mygdx.maykornercards.MyStaticItems.trademark.getWidth(), com.mygdx.maykornercards.MyStaticItems.trademark.getHeight(),
        Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.continuation.getWidth()*2,
                Gdx.graphics.getHeight()- MyStaticItems.exit.getHeight()*2
                );

    }

    //camera
    public void handleCam(){

        cam.zoom = MathUtils.clamp(cam.zoom, 50f, 250/cam.viewportWidth);

        float effectiveViewportWidth = cam.viewportWidth * cam.zoom;
        float effectiveViewportHeight = cam.viewportHeight * cam.zoom;

        cam.position.x = MathUtils.clamp(cam.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        cam.position.y = MathUtils.clamp(cam.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);

    }

    //camera
    public void resize(int width, int height){

        cam.viewportWidth = width;
        cam.viewportHeight = height/width;
        cam.update();

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

    public void dispose(){}//call this manly if nec


}