package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


public class MyGameScreenTwoCards extends ScreenAdapter {

    MyGdxGameTwoCards game;

    TextureAtlas atlas;

    Sprite front1, front2, front3, front4, front5, front6, front7, front8, front9, front10, front11, front12, front13,
            /*row2*/
            front21, front22, front23, front24, front25, front26, front27, front28, front29, front210, front211, front212, front213
            ;
    Sprite back1, back21/*, back3, back4, back5, back6, back7*/;

    OrthographicCamera cam;

    MyFactoryScreen myfactoryscreen;

    float cardx;
    float cardy;
    float overlap;

    public MyGameScreenTwoCards() {

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
                    game.setScreen(new com.mygdx.maykornercards.MyGameScreenTwoCards());
                }

                //trademark
                if (x< com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()
                        &&
                        renderY< com.mygdx.maykornercards.MyStaticItems.trademark.getHeight())
                {
                    // MyStaticItems.ahahah.stop();
                    //game.setScreen(new MyGameScreenTwo(game));


                }


                return true;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                int renderY = Gdx.graphics.getHeight() - screenY;//why - y?: cause touch is y-down but items are drawn y-up!
                game.debugscreenX = screenX;
                game.debugscreenY = screenY;


                return super.touchDragged(screenX, screenY, pointer);

            }
        });

        //--------------------------------------------cards--------------------------------------------
        atlas = /*new TextureAtlas("carddeck.atlas")*/com.mygdx.maykornercards.MyStaticItems.atlas;

        //pixel perfect bwlo; must acct for smaller resolves on fone, using Gdx.g...getWidth -> see tests in render!
        //no nd 4 height test all screesn can hold 2 cardheights, set at
        //  Mystatixitmes public static int cardrowmax = 2;

        //deal from left
        cardx = 0;
        cardy = Gdx.graphics.getHeight()/2;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        back1 = atlas.createSprite("back", 2);
        //front.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        back1.setPosition(cardx, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        //overlap += MyStaticItems.cardoverlap;//1st overlp is belo

        front2 = atlas.createSprite("clubs", 3);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front2.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front3 = atlas.createSprite("hearts", 6);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front3.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front4 = atlas.createSprite("diamonds", 10);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front4.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front5 = atlas.createSprite("clubs", 11);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front5.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front6 = atlas.createSprite("spades", 1);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front6.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front7 = atlas.createSprite("clubs", 12);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front7.setPosition(cardx-overlap, cardy);
        //after 7 cards (1back + 6 front no more cards can fit across screen- on fone)

        //repeats to test while test
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front8 = atlas.createSprite("diamonds", 8);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front8.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front9 = atlas.createSprite("diamonds", 9);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front9.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front10 = atlas.createSprite("diamonds", 10);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front10.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front11 = atlas.createSprite("diamonds", 11);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front11.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front12 = atlas.createSprite("diamonds", 12);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front12.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front13 = atlas.createSprite("diamonds", 13);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front13.setPosition(cardx-overlap, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        //repeats to test while test

        //row2, deal from right------------------------------
        cardx = Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.cardwidth;
        cardy = Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight-game.heightfrombottom;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        back21 = atlas.createSprite("back", 2);
        //front.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        back21.setPosition(cardx, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        //overlap += MyStaticItems.cardoverlap;//1st overlp is belo

        front22 = atlas.createSprite("clubs", 3);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front22.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front23 = atlas.createSprite("hearts", 6);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front23.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front24 = atlas.createSprite("diamonds", 10);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front24.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front25 = atlas.createSprite("clubs", 11);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front25.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front26 = atlas.createSprite("spades", 1);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front26.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front27 = atlas.createSprite("clubs", 12);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front27.setPosition(cardx+overlap, cardy);
        //after 7 cards (1back + 6 front no more cards can fit across screen- on fone)

        //repeats to test while test
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front28 = atlas.createSprite("diamonds", 8);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front28.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front29 = atlas.createSprite("diamonds", 9);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front29.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front210 = atlas.createSprite("diamonds", 10);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front210.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front211 = atlas.createSprite("diamonds", 11);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front211.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front212 = atlas.createSprite("diamonds", 12);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front212.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        front213 = atlas.createSprite("diamonds", 13);
        //back.setSize(MyStaticItems.CARD_WIDTH, MyStaticItems.CARD_HEIGHT);
        front213.setPosition(cardx+overlap, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        //repeats to test while test


        cam = new OrthographicCamera();


    }
    @Override
    public void render(float delta) {


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
        game.font.draw(game.batch, com.mygdx.maykornercards.MyStaticItems.cardgame, 0, Gdx.graphics.getHeight());
        //game.batch.draw(MyStaticItems.sshh,0,Gdx.graphics.getHeight()/2/*-MyStaticItems.sshh.getHeight()/2*/);
        //game.batch.draw(MyStaticItems.center,0,Gdx.graphics.getHeight()/2-MyStaticItems.center.getHeight());

        //debug
        int debugwhilecounter1;
        int debugwhilecounter2;

        game.batch.end();

        //draw game
        //game.batch.setProjectionMatrix(cam.combined);

        //--------------------------------------------cards--------------------------------------------
        cardx = 0;
       // cardy = Gdx.graphics.getHeight()/2;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

 //       while (cardx-overlap+MyStaticItems.cardwidth < Gdx.graphics.getWidth()
 //               &&
 //               cardy-overlap+MyStaticItems.cardheight < Gdx.graphics.getHeight()){

        game.batch.begin();
            debugwhilecounter1 = 0;
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                back1.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                //overlap += MyStaticItems.cardoverlap;//1st overlp is belo
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front2.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front3.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front4.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front5.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front6.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front7.draw(game.batch);
            }

        //repeats to test while test
            debugwhilecounter1 += 1;
            cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front8.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front9.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front10.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front11.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front12.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                front13.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            }
            //repeats to test while test

        //row2, deal from right------------------------------
        cardx = Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;


        debugwhilecounter2 = 0;
        if(cardx+overlap/*+MyStaticItems.cardwidth*/ > 0) {
            back21.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            //overlap += MyStaticItems.cardoverlap;//1st overlp is belo
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front22.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front23.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front24.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front25.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front26.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front27.draw(game.batch);
        }

        //repeats to test while test
        debugwhilecounter2+=1;
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front28.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front29.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front210.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front211.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front212.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        if(cardx+overlap/*MystaticItems.cardwidth*/ > 0) {
            front213.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        }
        //repeats to test while test

//            break;
//        }

        game.batch.end();

        game.batch.begin();
        if(game.debug){//at bot cause to caputer variable changes in while abv
            game.newfont3.draw(game.batch, myfactoryscreen.getdebug()
                            +" cw: " + cardx + " ovl: " + com.mygdx.maykornercards.MyStaticItems.cardoverlap + " co: " + overlap + " dbc: " + debugwhilecounter1 +"," + debugwhilecounter2
                    + " testw: " + /*(cardx-overlap+MyStaticItems.cardwidth)*/(cardx+overlap/*+MyStaticItems.cardwidth*/)

                    ,
                    0, Gdx.graphics.getHeight() - game.heightfrombottom* com.mygdx.maykornercards.MyStaticItems.debugheightfactor);
        }
        game.batch.end();


    }

    @Override
    /*
    The viewportWidth and viewportHeight properties of the Camera class define the portion of the world, in world units,
    that the camera sees. Unfortunately the term "viewport" is often used in two different ways: the portion of the world
    (in world units) the camera sees and the portion of the screen (in pixels) is being rendered onto.
    These are two different things, make sure to understand the difference.
     */
    public void resize(int width, int height) {

        if (width > height) {
            cam.viewportHeight = com.mygdx.maykornercards.MyStaticItems.MINIMUM_VIEWPORT_SIZE;
            cam.viewportWidth = cam.viewportHeight * (float)width / (float)height;
        } else {
            cam.viewportWidth = MyStaticItems.MINIMUM_VIEWPORT_SIZE;
            cam.viewportHeight = cam.viewportWidth * (float)height / (float)width;
        }
        //cam.update();
    }

    public void dispose(){
        atlas.dispose();
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
    }

}