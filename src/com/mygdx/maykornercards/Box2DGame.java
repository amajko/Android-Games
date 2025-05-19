package com.mygdx.maykornercards;

import com.badlogic.gdx.Game;

public class Box2DGame extends Game {

    private com.mygdx.maykornercards.LoadingScreen loadingScreen;
    private PreferencesScreen preferencesScreen;
    private MenuScreen menuScreen;
    private MainScreen mainScreen;
    private EndScreen endScreen;

    public final static int MENU = 0;
    public final static int PREFERENCES = 1;
    public final static int APPLICATION = 2;
    public final static int ENDGAME = 3;

    com.mygdx.maykornercards.AppPreferences preferences;

    public Box2DGame(){
       //ap = new AppPreferences();

    }

    @Override
    public void create() {

        preferences = new com.mygdx.maykornercards.AppPreferences();

        loadingScreen = new LoadingScreen(this);//must load this with new constructor!
        setScreen(loadingScreen);//must load this with new constructor!



    }

    public void changeScreen(int screen){
        switch(screen){
            case MENU:
                if(menuScreen == null) menuScreen = new MenuScreen(this);//cant stage.dispose cause it becomes null?
                this.setScreen(menuScreen);
                break;
            case PREFERENCES:
                if(preferencesScreen == null) preferencesScreen = new PreferencesScreen(this);
                this.setScreen(preferencesScreen);
                break;
            case APPLICATION:
                if(mainScreen == null) mainScreen = new MainScreen(this);
                this.setScreen(mainScreen);
                break;
            case ENDGAME:
                if(endScreen == null) endScreen = new EndScreen(this);
                this.setScreen(endScreen);
                break;
        }
    }

    public AppPreferences getPreferences(){
        return this.preferences;
    }


}