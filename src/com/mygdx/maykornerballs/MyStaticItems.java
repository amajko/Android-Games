package com.mygdx.maykornerballs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyStaticItems {

    //some static
    public static Sound sound = Gdx.audio.newSound(Gdx.files.internal("meow.mp3"));
    public static Sound losesound = Gdx.audio.newSound(Gdx.files.internal("Wipeout.mp3"));
    public static Sound huh = Gdx.audio.newSound(Gdx.files.internal("huh-2.wav"));
    public static Sound go = Gdx.audio.newSound(Gdx.files.internal("go-go-go.wav"));
    public static Sound ahahah = Gdx.audio.newSound(Gdx.files.internal("dennis_nedry_ahahah.mp3"));
    public static long ahahahID = 0;
    public static Sound drop = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));;
    public static Sound gun = Gdx.audio.newSound(Gdx.files.internal("Gun+357+Magnum.mp3"));;
    public static boolean playsound = true;


    public static Texture trademark = new Texture("mbadlogicr.jpg");
    public static Texture exit = new Texture("exitrb.png");
    public static Texture continuation = new Texture("jetr.jpg");
    public static Texture file = new Texture("folder-filesr.png");
    public static Texture sshh = new Texture("sshhrs.jpg");
    public static Texture zoom = new Texture("zoomrs.jpg");
    public static Texture center = new Texture("centerrs.jpg");

    public static String  gtximagename = "gtx.1.validate.png";
    public static Texture gtximage = new Texture(gtximagename);
    public static String gtximagefilestatus = "";

    public static String wintext = " Wins: ";
    public static String losetext = " Losses: ";

    public static String yourname = "Your name: ";
    public static String player = "Player";
    public static String fortext = " for ";


    public static String myname = "Al Majko";
    public static String links = "links";

    public static String gtxfilenameOne = "links.txt";
    public static String gtxfilenameTwo = "dazeov.txt";
    public static String gtxfilenamenone = "";
    public static String gtxfiletitle = "FileName";

    public static String cantfind = "...file not found...";
    public static String fileexists = "file exists";
    public static String getdirscreen = "get dir list in textarea screens";

    public static float debugheightfactor = 2.5f;
    public static int textfontadjnewfont3 = 10; //to accoun to center text based on newfont3 glyphs

    //cards
    public static float CARD_WIDTH = 1f;
    public static float CARD_HEIGHT = CARD_WIDTH * 277f / 200f;//size of each card png in texels
    public static float cardwidth = 193f;//in pixels
    public static float cardheight = 271f;//in pixels
    public static TextureAtlas atlas = new TextureAtlas("carddeck.atlas");
    public final static float MINIMUM_VIEWPORT_SIZE = 5f;
    public static String cardgame = "MaykornerCards";
    public static float cardoverlap = 50f;
    public static int cardrowmax = 2;
    public static Texture deal = new Texture("dealr.jpg");
    public static Texture dontdoit = new Texture("dontdoitr.png");
    public static int standarddeck = 52;
        //counting cards
    public static ArrayList<String> arlSuit1 = new ArrayList<String>();
    public static ArrayList<Integer> arlPip1 = new ArrayList<Integer>();
    public static ArrayList<String> arlSuit2 = new ArrayList<String>();
    public static ArrayList<Integer> arlPip2 = new ArrayList<Integer>();
    public static ArrayList<String> arlTypesofSuits = new ArrayList<String>();
    public static ArrayList<Integer> arlTypesofPips = new ArrayList<Integer>();
    public static String maxSuit1 = "";
    public static String maxPip1 = "";
    public static String maxSuit2 = "";
    public static String maxPip2 = "";
    //belo cast to arrayllist to avoid unsup operat
    public static List givenListAllCards = new ArrayList<> (Arrays.asList(
            "clubs,1", "clubs,2", "clubs,3", "clubs,4", "clubs,5", "clubs,6", "clubs,7", "clubs,8", "clubs,9", "clubs,10", "clubs,11", "clubs,12", "clubs,13",
            "hearts,1", "hearts,2", "hearts,3", "hearts,4", "hearts,5", "hearts,6", "hearts,7", "hearts,8", "hearts,9", "hearts,10", "hearts,11", "hearts,12", "hearts,13",
            "spades,1", "spades,2", "spades,3", "spades,4", "spades,5", "spades,6", "spades,7", "spades,8", "spades,9", "spades,10", "spades,11", "spades,12", "spades,13",
            "diamonds,1", "diamonds,2", "diamonds,3", "diamonds,4", "diamonds,5", "diamonds,6", "diamonds,7", "diamonds,8", "diamonds,9", "diamonds,10", "diamonds,11", "diamonds,12", "diamonds,13"
            ));
    public static String HighCard1 = "";
    public static String HighCard2 = "";
    public static String CountCard1 = "";
    public static String CountCard2 = "";
    public static String Straight1 = "";
    public static String Straight2 = "";
    public static String FullHouse1 = "";
    public static String FullHouse2 = "";



}


