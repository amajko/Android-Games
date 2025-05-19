package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.*;

/*
the arls produced in show are all 52 /2 for ea row: SHUFFLED and produced
the alrs limited by renders debugwhilecounter are as DEALT and shown
 */
public class MyGameScreenTwoCardsUseFact extends ScreenAdapter {

    private boolean debug = true;

    MyGdxGameTwoCards game;

    TextureAtlas atlas;


    Sprite
            front1, front2, front3, front4, front5, front6, front7, front8, front9, front10, front11, front12, front13,
            //row2
            front21, front22, front23, front24, front25, front26, front27, front28, front29, front210, front211, front212, front213,
                    //all cards
                    front31, front32, front33, front34, front35, front36, front37, front38, front39, front310, front311, front312, front313,

    front41, front42, front43, front44, front45, front46, front47, front48, front49, front410, front411, front412, front413

            ;

    Sprite[] spritearray = new Sprite[]{front1, front2, front3, front4, front5, front6, front7, front8, front9, front10, front11, front12, front13,
            /*row2*/
            front21, front22, front23, front24, front25, front26, front27, front28, front29, front210, front211, front212, front213,
            //all cards
            front31, front32, front33, front34, front35, front36, front37, front38, front39, front310, front311, front312, front313,

            front41, front42, front43, front44, front45, front46, front47, front48, front49, front410, front411, front412, front413};
    Float[] spriteXarray = new Float[com.mygdx.maykornercards.MyStaticItems.standarddeck];
    Float[] spriteYarray = new Float[com.mygdx.maykornercards.MyStaticItems.standarddeck];
    //Float[] spriteWarray = new Float[52];//use static.cardw
    //Float[] spriteHarray = new Float[52];//use static.carh

    Sprite back1, back21/*, back3, back4, back5, back6, back7*/;

    List<String> givenListAllCards;

    OrthographicCamera cam;

    MyFactoryScreen myfactoryscreen;

    float cardx;
    float cardy;
    float overlap;

    //debug
    int debugwhilecounter1;
    int debugwhilecounter2;

    int sorthand1 = 0;
    int sorthand2 = 0;

    boolean dealer = true;//setso deal at 1st load
    int dealercardcount = 0;
    boolean redraw = false;

    //redraw
    float retouchXrow1 = 0;
    float retouchYrow1 = 0;
    float retouchXrow2 = 0;
    float retouchYrow2 = 0;
    int row1cardpicked;
    int row2cardpicked;

    String gamesignal = "Deal";





    public MyGameScreenTwoCardsUseFact(final MyGdxGameTwoCards game) {

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
                    game.setScreen(new MyGameScreenTwoCardsUseFact(game));
                }

                //trademark
                if (x< com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()
                        &&
                        renderY< com.mygdx.maykornercards.MyStaticItems.trademark.getHeight())
                {
                    // MyStaticItems.ahahah.stop();
                    //game.setScreen(new MyGameScreenTwo(game));


                }

                //back1
                //cardx = 0;
                //cardy = Gdx.graphics.getHeight()/2-MyStaticItems.cardheight/2+game.heightfrombottom*2;
                if(x>0 && x< com.mygdx.maykornercards.MyStaticItems.cardwidth
                && renderY>Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/2+game.heightfrombottom*2
                        && renderY<Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/2+game.heightfrombottom*2+ com.mygdx.maykornercards.MyStaticItems.cardheight
                ) {
                    if(sorthand1<=1) {
                        sorthand1 += 1;
                    }else{
                        sorthand1 = 0;
                    }
                }

                //back2
                //cardx = Gdx.graphics.getWidth()-MyStaticItems.cardwidth;
                //cardy = Gdx.graphics.getHeight()/2-MyStaticItems.cardheight/*-game.heightfrombottom*/+game.heightfrombottom*5;
                if(x>Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.cardwidth && x<Gdx.graphics.getWidth()
                        && renderY>Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/*-game.heightfrombottom*/+game.heightfrombottom*5
                        && renderY<Gdx.graphics.getHeight()/2+game.heightfrombottom*5
                ) {
                    if(sorthand2<=1) {
                        sorthand2 += 1;
                    }else{
                        sorthand2 = 0;
                    }
                }

                //dealer
                if(x>Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.deal.getWidth()/2 &&
                        x<Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.deal.getWidth()/2+ com.mygdx.maykornercards.MyStaticItems.deal.getWidth()
                &&
                        renderY>game.heightfrombottom  && renderY< game.heightfrombottom+ com.mygdx.maykornercards.MyStaticItems.deal.getHeight()) {

                    dealer = true; gamesignal="Deal"; sorthand1=0; sorthand2=0;
                    if(dealercardcount< com.mygdx.maykornercards.MyStaticItems.standarddeck/4+1/*-1*/){dealercardcount+=1;}else{dealercardcount=0;redraw=false;}//stil crashes at 26
                    //controls counting up thru 14, and redraw

                     if(dealercardcount== com.mygdx.maykornercards.MyStaticItems.standarddeck/4/*-1*//*back*//*..2 wd b the sAME*/){dealer=false; redraw=false; gamesignal = "Sort";}//so can sort after full deal
                    //controls dealer, redraw on/off at 13

                    if(dealercardcount== com.mygdx.maykornercards.MyStaticItems.standarddeck/4+1/*-1*//*back*//*..2 wd b the sAME*/){dealer=false;redraw=true; gamesignal="Draw";}//so can sort after full deal
                    //controls redraw at 14

                 }

                //game-----
                //redraw

                //test ea card sq OR just compare x,renderY to a card drawn?

                //row1
                if(redraw) {
                    if (x > spritearray[0].getX()
                            &&
                            x < debugwhilecounter1 * (com.mygdx.maykornercards.MyStaticItems.cardwidth - com.mygdx.maykornercards.MyStaticItems.cardoverlap)
                            &&
                            renderY > spritearray[0].getY() + com.mygdx.maykornercards.MyStaticItems.cardheight - com.mygdx.maykornercards.MyStaticItems.cardoverlap * 1.5
                            &&
                            renderY < spritearray[0].getY() + com.mygdx.maykornercards.MyStaticItems.cardheight) {
                        //get the y that is top part of row 1 wo hitting row2 card, row2 also OK to limit: no harm

                        retouchXrow1 = x;
                        retouchYrow1 = renderY;
                        row1cardpicked =
                                Float.valueOf(
                                        (x)
                                                /
                                                (com.mygdx.maykornercards.MyStaticItems.cardwidth - com.mygdx.maykornercards.MyStaticItems.cardoverlap)
                                ).intValue()
                        ;//use debugwhilecountet to get car-sprite
                    }

                    //row2
                    if (x > Gdx.graphics.getWidth() - debugwhilecounter2 * (com.mygdx.maykornercards.MyStaticItems.cardwidth - com.mygdx.maykornercards.MyStaticItems.cardoverlap)
                            &&
                            x < Gdx.graphics.getWidth() - com.mygdx.maykornercards.MyStaticItems.cardwidth + com.mygdx.maykornercards.MyStaticItems.cardoverlap
                            &&
                            renderY > spritearray[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2].getY()
                            &&
                            renderY < spritearray[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2].getY() + com.mygdx.maykornercards.MyStaticItems.cardoverlap * 1.5) {
                        //get the y that is top part of row 1 wo hitting row2 card, row2 also OK to limit: no harm

                        retouchXrow2 = x;
                        retouchYrow2 = renderY;
                        row2cardpicked =
                                debugwhilecounter2 -
                                        Float.valueOf(
                                                (x)
                                                        /
                                                        (com.mygdx.maykornercards.MyStaticItems.cardwidth - com.mygdx.maykornercards.MyStaticItems.cardoverlap)
                                        ).intValue()
                        ;
                        ;//use debugwhilecountet to get car-sprite
                    }
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
        //spritearray = myfactoryscreen.doShuffle(atlas, givenListAllCards, cardx, cardy, overlap, back1, back21, spritearray,spriteXarray, spriteYarray);

        atlas = com.mygdx.maykornercards.MyStaticItems.atlas;

        //pixel perfect bwlo; must acct for smaller resolves on fone, using Gdx.g...getWidth -> see tests in render!
        //no nd 4 height test all screesn can hold 2 cardheights, set at
        //  Mystatixitmes public static int cardrowmax = 2;

        Random rand = new Random();

         //belo cast to arrayllist to avoid unsup operat

        givenListAllCards = new ArrayList<> (Arrays.asList(
                "clubs,1", "clubs,2", "clubs,3", "clubs,4", "clubs,5", "clubs,6", "clubs,7", "clubs,8", "clubs,9", "clubs,10", "clubs,11", "clubs,12", "clubs,13",
                "hearts,1", "hearts,2", "hearts,3", "hearts,4", "hearts,5", "hearts,6", "hearts,7", "hearts,8", "hearts,9", "hearts,10", "hearts,11", "hearts,12", "hearts,13",
                "spades,1", "spades,2", "spades,3", "spades,4", "spades,5", "spades,6", "spades,7", "spades,8", "spades,9", "spades,10", "spades,11", "spades,12", "spades,13",
                "diamonds,1", "diamonds,2", "diamonds,3", "diamonds,4", "diamonds,5", "diamonds,6", "diamonds,7", "diamonds,8", "diamonds,9", "diamonds,10", "diamonds,11", "diamonds,12", "diamonds,13"
                ));


        String element = null;
        ArrayList<String> arl = null;
        String suit = null;
        int pip = 0;


        //shuffle as if to deal from left
        cardx = 0;
        cardy = Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/2+game.heightfrombottom*2;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        back1 = myfactoryscreen.createcardsprite(atlas, back1, "back", 2, cardx, cardy);
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
        //overlap += MyStaticItems.cardoverlap;//1st overlp is belo

         com.mygdx.maykornercards.MyStaticItems.arlSuit1.clear();
         com.mygdx.maykornercards.MyStaticItems.arlPip1.clear();

//         spritearray = myfactoryscreen.doShuffle(0, MyStaticItems.standarddeck/2, atlas, givenListAllCards, cardx, cardy, overlap,
  //               spritearray, spriteXarray, spriteYarray, rand, element, arl, suit, pip, MyStaticItems.arlSuit1, MyStaticItems.arlPip1);

         for (int i = 0; i < com.mygdx.maykornercards.MyStaticItems.standarddeck/2; i++) {//just 1/2 cars from left
            //int randomIndexSuit = rand.nextInt(givenListSuit.size());
            //int randomIndexPip = rand.nextInt(pipList.get(randomIndexSuit).size());

            int randomIndexCard = rand.nextInt(givenListAllCards.size());
            element = givenListAllCards.get(randomIndexCard);
            arl = MyFactoryScreen.Split(element, ",");
            suit = arl.get(0);
            pip = Integer.valueOf(arl.get(1));

             //how to ck that this suit.pip is still avail after remove()?
             spritearray[i] = myfactoryscreen.createcardsprite(atlas, spritearray[i],
                    suit,
                    pip,
                    cardx - overlap, cardy);
             com.mygdx.maykornercards.MyStaticItems.arlSuit1.add(suit);
             com.mygdx.maykornercards.MyStaticItems.arlPip1.add(pip);

            //how to remove pip fot THIS suit and not remove it for the other suits?
            // pipList.get(randomIndexSuit).remove(randomIndexPip);
             givenListAllCards.remove(randomIndexCard);

            spriteXarray[i] = cardx - overlap;
            spriteYarray[i] = cardy;
            cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }

        //row2, shuffle as if to deal from right------------------------------
        cardx = Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.cardwidth;
        cardy = Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight+game.heightfrombottom*5;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        back21 = myfactoryscreen.createcardsprite(atlas, back21,"back", 2, cardx, cardy);
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
        //overlap += MyStaticItems.cardoverlap;//1st overlp is belo

         com.mygdx.maykornercards.MyStaticItems.arlSuit2.clear();
         com.mygdx.maykornercards.MyStaticItems.arlPip2.clear();

//         spritearray = myfactoryscreen.doShuffle(MyStaticItems.standarddeck/2, MyStaticItems.standarddeck, atlas, givenListAllCards, cardx, cardy, overlap,
  //               spritearray, spriteXarray, spriteYarray, rand, element, arl, suit, pip, MyStaticItems.arlSuit2, MyStaticItems.arlPip2);

         for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck/2; i < com.mygdx.maykornercards.MyStaticItems.standarddeck; i++) {//just 1/2 cars from right
            //int randomIndexSuit = rand.nextInt(givenListSuit.size());
            //int randomIndexPip = rand.nextInt(pipList.get(randomIndexSuit).size());

            int randomIndexCard = rand.nextInt(givenListAllCards.size());
            element = givenListAllCards.get(randomIndexCard);
            arl = MyFactoryScreen.Split(element, ",");
            suit = arl.get(0);
            pip = Integer.valueOf(arl.get(1));

            //how to ck that this suit.pip is still avail after remove()?
            spritearray[i] = myfactoryscreen.createcardsprite(atlas, spritearray[i],
                    suit,
                    pip,
                    cardx + overlap, cardy);
            com.mygdx.maykornercards.MyStaticItems.arlSuit2.add(suit);
            com.mygdx.maykornercards.MyStaticItems.arlPip2.add(pip);

            //how to remove pip fot THIS suit and not remove it for the other suits?
            //pipList.get(randomIndexSuit).remove(randomIndexPip);
            givenListAllCards.remove(randomIndexCard);

            spriteXarray[i] = cardx - overlap;
            spriteYarray[i] = cardy;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }

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

        game.font.draw(game.batch, gamesignal, Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.deal.getWidth()/2 - game.heightfrombottom*6,
                game.heightfrombottom*3);
        game.batch.draw(com.mygdx.maykornercards.MyStaticItems.deal, Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.deal.getWidth()/2,game.heightfrombottom);
        game.font.draw(game.batch, gamesignal, Gdx.graphics.getWidth()/2- com.mygdx.maykornercards.MyStaticItems.deal.getWidth()/2
                        + com.mygdx.maykornercards.MyStaticItems.deal.getWidth()+game.heightfrombottom*2,
                game.heightfrombottom*3);

        game.batch.end();

        //draw game
        //game.batch.setProjectionMatrix(cam.combined);

        //--------------------------------------------cards--------------------------------------------
        cardx = 0;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        game.batch.begin();
            debugwhilecounter1 = 0;
            if(cardx-overlap+ com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                back1.draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                //overlap += MyStaticItems.cardoverlap;//1st overlp is belo
            }
if(!dealer && !redraw) {
    if (sorthand1 == 0) {//as dealt
        for (int i = 0; i < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i++) {//just 1/2 cars from left
            if (cardx - overlap + com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                spritearray[i].draw(game.batch);
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}
            }
        }
    }
    if (sorthand1 == 1) {// by suit
        Sprite[] halfspritearray = new Sprite[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2];
        for (int i = 0; i < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i++) {//just 1/2 cars from left
            if (cardx - overlap + com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                //spritearray[i].draw(game.batch);//dont draw yet, just count debuwhile so get # cards
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}

                //create new sprite array of JUST these 26 cars from left OR from right
                halfspritearray[i] = spritearray[i];
            }
        }

        //use new arls so dont corrupt orig arlSuit or arlPip

        //resets
        cardx = 0;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;//where back is drawn

        //arl that can be sorted is arlSuit|Pip
        //arl that is the deaalable-showable sprites is spritearray
        Sprite[] sortedsprite = myfactoryscreen.sortbySuit(atlas, halfspritearray, com.mygdx.maykornercards.MyStaticItems.arlSuit1, com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1 - 1);


        //Sprite[] sortedsprite = myfactoryscreen.callsortbySuit(MyStaticItems.arlSuit1, MyStaticItems.arlPip1,0, cardx, overlap, debugwhilecounter1, spritearray, atlas);

        for (int i = 0; i < debugwhilecounter1 - 1; i++) {
            sortedsprite[i].draw(game.batch);
            //cardx += MyStaticItems.cardwidth;//ndd?
            //overlap += MyStaticItems.cardoverlap;//ndd?
        }

    }
    if (sorthand1 == 2) {//by pip
        Sprite[] halfspritearray = new Sprite[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2];
        for (int i = 0; i < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i++) {//just 1/2 cars from left
            if (cardx - overlap + com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
                //spritearray[i].draw(game.batch);//dont draw yet, just count debuwhile so get # cards
                debugwhilecounter1 += 1;
                cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}

                //create new sprite array of JUST these 26 cars from left OR from right
                halfspritearray[i] = spritearray[i];
            }
        }

        //use new arls so dont corrupt orig arlSuit or arlPip

        //resets
        cardx = 0;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;//where back is drawn

        //arl that can be sorted is arlSuit|Pip
        //arl that is the deaalable-showable sprites is spritearray
        Sprite[] sortedsprite = myfactoryscreen.sortbyPip(atlas, halfspritearray, com.mygdx.maykornercards.MyStaticItems.arlPip1, com.mygdx.maykornercards.MyStaticItems.arlSuit1, debugwhilecounter1 - 1);

        //Sprite[] sortedsprite = myfactoryscreen.callsortbyPip( MyStaticItems.arlPip1, MyStaticItems.arlSuit1, 0, cardx, overlap, debugwhilecounter1, spritearray, atlas);

        for (int i = 0; i < debugwhilecounter1 - 1; i++) {
            sortedsprite[i].draw(game.batch);
            //cardx += MyStaticItems.cardwidth;//ndd?
            //overlap += MyStaticItems.cardoverlap;//ndd?
        }

    }
}
        //row2, deal from right------------------------------
        cardx = Gdx.graphics.getWidth()- com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;

        debugwhilecounter2 = 0;
        if(cardx+overlap > 0) {
            back21.draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            //overlap += MyStaticItems.cardoverlap;//1st overlp is belo
        }
if(!dealer && !redraw) {
    if (sorthand2 == 0) {//as dealt
        for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i < com.mygdx.maykornercards.MyStaticItems.standarddeck; i++) {//just 1/2 cars from left
            if (cardx + overlap > 0) {
                spritearray[i].draw(game.batch);
                debugwhilecounter2 += 1;
                cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}
            }
        }
    }
    if (sorthand2 == 1) {// by suit
        Sprite[] halfspritearray = new Sprite[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2];
        for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i < com.mygdx.maykornercards.MyStaticItems.standarddeck; i++) {//just 1/2 cars from right
            if (cardx + overlap > 0) {
                //spritearray[i].draw(game.batch);//dont draw yet, just count debuwhile so get # cards
                debugwhilecounter2 += 1;
                cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}

                //create new sprite array of JUST these 26 cars from left OR from right
                halfspritearray[i - com.mygdx.maykornercards.MyStaticItems.standarddeck / 2] = spritearray[i];//!!
            }
        }

        //use new arls so dont corrupt orig arlSuit or arlPip

        //resets
        cardx = Gdx.graphics.getWidth() - com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;

        //arl that can be sorted is arlSuit|Pip
        //arl that is the deaalable-showable sprites is spritearray
        Sprite[] sortedsprite = myfactoryscreen.sortbySuit(atlas, halfspritearray, com.mygdx.maykornercards.MyStaticItems.arlSuit2, com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2 - 1);

        for (int i = 0; i < debugwhilecounter2 - 1; i++) {
            sortedsprite[i].draw(game.batch);
            //cardx += MyStaticItems.cardwidth;//ndd?
            //overlap += MyStaticItems.cardoverlap;//ndd?
        }

    }
    if (sorthand2 == 2) {//by pip

        Sprite[] halfspritearray = new Sprite[com.mygdx.maykornercards.MyStaticItems.standarddeck / 2];
        for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i < com.mygdx.maykornercards.MyStaticItems.standarddeck; i++) {//just 1/2 cars from right
            if (cardx + overlap > 0) {
                //spritearray[i].draw(game.batch);//dont draw yet, just count debuwhile so get # cards
                debugwhilecounter2 += 1;
                cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
                overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
                //if(game.debug){System.out.println(spritearray[i].getX());}

                //create new sprite array of JUST these 26 cars from left OR from right
                halfspritearray[i - com.mygdx.maykornercards.MyStaticItems.standarddeck / 2] = spritearray[i];//!!
            }
        }

        //use new arls so dont corrupt orig arlSuit or arlPip

        //resets
        cardx = Gdx.graphics.getWidth() - com.mygdx.maykornercards.MyStaticItems.cardwidth;
        overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
        cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;

        //arl that can be sorted is arlSuit|Pip
        //arl that is the deaalable-showable sprites is spritearray
        Sprite[] sortedsprite = myfactoryscreen.sortbyPip(atlas, halfspritearray, com.mygdx.maykornercards.MyStaticItems.arlPip2, com.mygdx.maykornercards.MyStaticItems.arlSuit2, debugwhilecounter2 - 1);

        for (int i = 0; i < debugwhilecounter2 - 1; i++) {
            sortedsprite[i].draw(game.batch);
            //cardx += MyStaticItems.cardwidth;//ndd?
            //overlap += MyStaticItems.cardoverlap;//ndd?
        }

    }
}

if(dealer && !redraw) {
    //=========using dealer========
    //====NO SORTING=====

    //manly call show to get diff rand shuffle?
    //show();

    //--from left
    //if (sorthand1 == 0) {//as dealt
    cardx = 0;
    overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
    cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;//the back
    int ioffset = com.mygdx.maykornercards.MyStaticItems.standarddeck / 8;//gets more rand cards than the orig full deal, but x,y's already set in shuffle!

    for (int i = 0/*+ioffset*/; i /*< MyStaticItems.standarddeck / 2*/ <= dealercardcount/*+ioffset*/; i++) {//just 1/2 cars from left
        if (cardx - overlap + com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth() && dealercardcount < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2/*-ioffset*/) {
            spritearray[i].draw(game.batch);
            debugwhilecounter1 += 1;
            cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }
    }
    //}

    //--from rite
    //if (sorthand2 == 0) {//as dealt
    cardx = Gdx.graphics.getWidth() - com.mygdx.maykornercards.MyStaticItems.cardwidth;
    overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
    cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;//the back

    for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck / 2/*+ioffset*/; i /*< MyStaticItems.standarddeck*/ <= com.mygdx.maykornercards.MyStaticItems.standarddeck / 2 + dealercardcount/*+ioffset*/; i++) {//just 1/2 cars from right
        if (cardx + overlap > 0 && dealercardcount < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2/*-ioffset*/) {
            spritearray[i].draw(game.batch);
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }
    }
}
    //}
if(redraw) {
    //fr left row1
    cardx = 0;
    overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
    cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;//the back

    for (int i = 0; i < com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i++) {//just 1/2 cars from left
        if (cardx - overlap + com.mygdx.maykornercards.MyStaticItems.cardwidth < Gdx.graphics.getWidth()) {
            if(row1cardpicked == i+1){
                if(row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 /*add 13*/ <  com.mygdx.maykornercards.MyStaticItems.standarddeck / 2 /*less than this 1/2 pack*/){
                    spritearray[row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ].setX(spritearray[i].getX());
                    spritearray[row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ].setY(spritearray[i].getY());
                    spritearray[i]=spritearray[row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ];
                    com.mygdx.maykornercards.MyStaticItems.arlSuit1.set(i, com.mygdx.maykornercards.MyStaticItems.arlSuit1.get(row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4));
                    com.mygdx.maykornercards.MyStaticItems.arlPip1.set(i, com.mygdx.maykornercards.MyStaticItems.arlPip1.get(row1cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4));
                    //spritearray[row1cardpicked+MyStaticItems.standarddeck / 4 ].draw(game.batch);
                    spritearray[i].draw(game.batch);
                }

            }else {
                spritearray[i].draw(game.batch);
            }
            debugwhilecounter1 += 1;
            cardx += com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }
    }
    //fr rite row2
    cardx = Gdx.graphics.getWidth() - com.mygdx.maykornercards.MyStaticItems.cardwidth;
    overlap = com.mygdx.maykornercards.MyStaticItems.cardoverlap;
    cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;//the back
    for (int i = com.mygdx.maykornercards.MyStaticItems.standarddeck / 2; i < com.mygdx.maykornercards.MyStaticItems.standarddeck; i++) {//just 1/2 cars from right
        if (cardx + overlap > 0) {
            if(row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 2 == i+1){
                if(row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 2+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 /*add 13*/ <  com.mygdx.maykornercards.MyStaticItems.standarddeck /*less than this 1/2 pack*/){
                    spritearray[row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 2+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ].setX(spritearray[i].getX());
                    spritearray[row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 2+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ].setY(spritearray[i].getY());
                    spritearray[i]=spritearray[row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 2+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4 ];
                    com.mygdx.maykornercards.MyStaticItems.arlSuit2.set(i- com.mygdx.maykornercards.MyStaticItems.standarddeck / 2, com.mygdx.maykornercards.MyStaticItems.arlSuit2.get(row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4));
                    com.mygdx.maykornercards.MyStaticItems.arlPip2.set(i- com.mygdx.maykornercards.MyStaticItems.standarddeck / 2, com.mygdx.maykornercards.MyStaticItems.arlPip2.get(row2cardpicked+ com.mygdx.maykornercards.MyStaticItems.standarddeck / 4));
                    //spritearray[row1cardpicked+MyStaticItems.standarddeck / 4 ].draw(game.batch);
                    spritearray[i].draw(game.batch);
                }

            }else {
                spritearray[i].draw(game.batch);
            }
            debugwhilecounter2 += 1;
            cardx -= com.mygdx.maykornercards.MyStaticItems.cardwidth;
            overlap += com.mygdx.maykornercards.MyStaticItems.cardoverlap;
            //if(game.debug){System.out.println(spritearray[i].getX());}
        }
    }
}

        myfactoryscreen.setTypesofSuits(com.mygdx.maykornercards.MyStaticItems.givenListAllCards, 0);
        myfactoryscreen.setTypesofPips(com.mygdx.maykornercards.MyStaticItems.givenListAllCards, 1);
        myfactoryscreen.countCardsbySuit1(com.mygdx.maykornercards.MyStaticItems.arlSuit1, debugwhilecounter1-1);
        myfactoryscreen.countCardsbyPip1(com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1-1);
        myfactoryscreen.countCardsbySuit2(com.mygdx.maykornercards.MyStaticItems.arlSuit2, debugwhilecounter2-1);
        myfactoryscreen.countCardsbyPip2(com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2-1);
        myfactoryscreen.getHighCard1(com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1-1);
        myfactoryscreen.getHighCard2(com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2-1);
        myfactoryscreen.getCountCard1(com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1-1);
        myfactoryscreen.getCountCard2(com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2-1);
        myfactoryscreen.getStraight1(com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1-1);
        myfactoryscreen.getStraight2(com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2-1);
        myfactoryscreen.getFullHouse1(com.mygdx.maykornercards.MyStaticItems.arlPip1, debugwhilecounter1-1);
        myfactoryscreen.getFullHouse2(com.mygdx.maykornercards.MyStaticItems.arlPip2, debugwhilecounter2-1);

        //printDebug();

        game.newfont3.draw(game.batch, com.mygdx.maykornercards.MyStaticItems.maxSuit1 + " / " + com.mygdx.maykornercards.MyStaticItems.maxPip1
                +" / " + com.mygdx.maykornercards.MyStaticItems.Straight1 + " / " + com.mygdx.maykornercards.MyStaticItems.FullHouse1
                + " / " + com.mygdx.maykornercards.MyStaticItems.HighCard1  + " / " + com.mygdx.maykornercards.MyStaticItems.CountCard1
                , com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()+game.heightfrombottom,
                Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/2+game.heightfrombottom*2.5f
                        + com.mygdx.maykornercards.MyStaticItems.cardheight+ game.heightfrombottom*2f/*accts for font height nxtln*/);

        game.newfont3.draw(game.batch, com.mygdx.maykornercards.MyStaticItems.maxSuit2 + " / " + com.mygdx.maykornercards.MyStaticItems.maxPip2
                        +" / " + com.mygdx.maykornercards.MyStaticItems.Straight2 + " / " + com.mygdx.maykornercards.MyStaticItems.FullHouse2
                        + " / " + com.mygdx.maykornercards.MyStaticItems.HighCard2  + " / " + com.mygdx.maykornercards.MyStaticItems.CountCard2
                , com.mygdx.maykornercards.MyStaticItems.trademark.getWidth()+ game.heightfrombottom,
                Gdx.graphics.getHeight()/2- com.mygdx.maykornercards.MyStaticItems.cardheight/*-game.heightfrombottom*/+game.heightfrombottom*5
                        -game.heightfrombottom);

        game.batch.end();

        game.batch.begin();
        if(game.debug){//at bot cause to caputer variable changes in while abv
            game.newfont3.draw(game.batch, myfactoryscreen.getdebug()
                           // +" cw: " + cardx + " ovl: " + MyStaticItems.cardoverlap + " co: " + overlap
                          //  + " dbc: "
                              +" "+ debugwhilecounter1 +"," + debugwhilecounter2
                    //+ " testw: " + /*(cardx-overlap+MyStaticItems.cardwidth)*/(cardx+overlap/*+MyStaticItems.cardwidth*/)
                      //  + " cw " + MyStaticItems.cardwidth + " ch " + MyStaticItems.cardheight
                           // + " back1h " + (Gdx.graphics.getHeight()/2-MyStaticItems.cardheight/2+game.heightfrombottom*2)
                       // + " back2h " +
                          //  + " " + sorthand1 + "," + sorthand2
                            //+ " "+dealer
                            +" "   +dealercardcount + " go13"
                   // + " "+(Gdx.graphics.getWidth()/2-MyStaticItems.deal.getWidth()/2)
                   // + " "+ (Gdx.graphics.getWidth()/2-MyStaticItems.deal.getWidth()/2)
                //    + " -> "+Float.valueOf(retouchXrow1).intValue()+", "+Float.valueOf(retouchYrow1).intValue()+" ; "
                  //          +Float.valueOf(retouchXrow2).intValue()+", "+Float.valueOf(retouchXrow2).intValue()
                    +" ; "+row1cardpicked+ " "+row2cardpicked

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
            cam.viewportWidth = com.mygdx.maykornercards.MyStaticItems.MINIMUM_VIEWPORT_SIZE;
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

    private void printDebug(){

        if(debug) {
                System.out.println("********************");
                System.out.println(com.mygdx.maykornercards.MyStaticItems.arlTypesofSuits);
                System.out.println(com.mygdx.maykornercards.MyStaticItems.arlTypesofPips);

                 System.out.println("--------------------");
                System.out.println(com.mygdx.maykornercards.MyStaticItems.arlSuit1.toString());
                 System.out.println(com.mygdx.maykornercards.MyStaticItems.arlPip1.toString());
                 System.out.println(com.mygdx.maykornercards.MyStaticItems.arlSuit2.toString());
                System.out.println(com.mygdx.maykornercards.MyStaticItems.arlPip2.toString());

                System.out.println("////////////////////");
                System.out.println(com.mygdx.maykornercards.MyStaticItems.maxSuit1);
                System.out.println(MyStaticItems.maxSuit2);
        }

    }

}