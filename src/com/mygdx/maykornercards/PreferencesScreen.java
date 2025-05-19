package com.mygdx.maykornercards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/*
does this even set prefs?
also:
1. the screen is tiny
2. 2 back-and-forth using the 'back' button crashes app

hello, the saving data, preference , does not work on my android phone,
but it works on desktop… Do you have any idea? the code is totally same as yours

Make sure you are using the flush() command each time something is changed as android needs this flush command
 to write the changes to the save file. Also, check the PREFS_NAME and find the file on disk to see what items if any are changing.

The flush () function does not help, the fact is that you can not pull Libgdx every time you need that or other setting.
 Libgdx each time creates a new object whose editor field is null, so flush() function does nothing.
 As an option*: describe the class variable
 */
public class PreferencesScreen implements Screen {

    //AppPreferences ap;

    private Stage stage = new Stage();
    private com.mygdx.maykornercards.Box2DGame parent; // a field to store our orchestrator

    private Label titleLabel;
    private Label volumeMusicLabel;
    private Label volumeSoundLabel;
    private Label musicOnOffLabel;
    private Label soundOnOffLabel;


    // our constructor with a Box2DTutorial argument
    public PreferencesScreen(com.mygdx.maykornercards.Box2DGame box2dGame){
        parent = box2dGame;     // setting the argument to our field.

        //ap = new AppPreferences();
        //ScreenViewport sv = new ScreenViewport();
       // sv.update(5,5,true);//still tiny
        stage = new Stage(new ScreenViewport());
        //stage = new Stage(sv);
        Gdx.input.setInputProcessor(stage);


    }

      @Override
    public void show() {


        // TODO Auto-generated method stub
          stage.clear();//cleares so repeted cliks to prefs screen doesnt recreat the new stage over the old


          // Create a table that fills the screen. Everything else will go inside this table.
          Table table = new Table();
          table.setFillParent(true);
         // table.setHeight(Gdx.graphics.getHeight());//still tiny on screen
         // table.setWidth(Gdx.graphics.getWidth());
          //table.setDebug(true);//inally, let’s get rid off the debug visuals by commenting out the “table.setDebug(true);”
          stage.addActor(table);

          Skin skin = new Skin(Gdx.files.internal("glassy-ui.json"));
          //skin.setScale(4.0f);//still tiny


          //volume
          final Slider volumeMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
          volumeMusicSlider.setValue( parent.getPreferences().getMusicVolume() );
          volumeMusicSlider.addListener( new EventListener() {
              @Override
              public boolean handle(Event event) {
                  parent.getPreferences().getPrefs().flush();//reset

                  parent.getPreferences().setMusicVolume( volumeMusicSlider.getValue() );
                  return false;
              }
          });

          //sound
          final Slider soundMusicSlider = new Slider( 0f, 1f, 0.1f,false, skin );
          soundMusicSlider.setValue( parent.getPreferences().getMusicVolume() );
          soundMusicSlider.addListener( new EventListener() {
              @Override
              public boolean handle(Event event) {
                  parent.getPreferences().getPrefs().flush();//reset

                  parent.getPreferences().setMusicVolume( soundMusicSlider.getValue() );
                  return false;
              }
          });

          //music
          final CheckBox musicCheckbox = new CheckBox(null, skin);
          musicCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
          musicCheckbox.addListener(new EventListener() {
              @Override
              public boolean handle(Event event) {
                  parent.getPreferences().getPrefs().flush();//reset

                  boolean enabled = musicCheckbox.isChecked();
                  parent.getPreferences().setMusicEnabled(enabled);
                  return false;
              }
          });

          //sound
          final CheckBox soundEffectsCheckbox = new CheckBox(null, skin);
          soundEffectsCheckbox.setChecked( parent.getPreferences().isMusicEnabled() );
          soundEffectsCheckbox.addListener(new EventListener() {
              @Override
              public boolean handle(Event event) {
                  parent.getPreferences().getPrefs().flush();//reset

                  boolean enabled = soundEffectsCheckbox.isChecked();
                  parent.getPreferences().setSoundEffectsEnabled (enabled);
                  return false;
              }
          });

          // return to main screen button
          final TextButton backButton = new TextButton("Back", skin, "small"); // the extra argument here "small" is used
          // to set the button to the smaller version instead of the big default version
          backButton.addListener(new ChangeListener() {
              @Override
              public void changed(ChangeEvent event, Actor actor) {
                  parent.changeScreen(Box2DGame.MENU);
              }
          });

          titleLabel = new Label( "Preferences", skin );
          volumeMusicLabel = new Label( "Music Volume", skin );
          volumeSoundLabel = new Label( "Sound Volume", skin );
          musicOnOffLabel = new Label( "Music", skin );
          soundOnOffLabel = new Label( "Sound Effect", skin );

         // titleLabel.setWidth(Gdx.graphics.getWidth());//still tiny


          table.add(titleLabel).colspan(2);
          table.row().pad(30,30,30,30);
          table.add(volumeMusicLabel).left();
          table.add(volumeMusicSlider);
          table.row().pad(30,30,30,30);;
          table.add(musicOnOffLabel).left();
          table.add(musicCheckbox);
          table.row().pad(30,30,30,30);;
          table.add(volumeSoundLabel).left();
          table.add(soundMusicSlider);
          table.row().pad(30,30,30,30);;
          table.add(soundOnOffLabel).left();
          table.add(soundEffectsCheckbox);
          table.row().pad(30,30,30,30);;
          table.add(backButton).colspan(2);

      }

    @Override
    public void render(float delta) {
        // TODO Auto-generated method stub
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub
              /*
        This tells the stage that the screen size has changed and the viewport should be recalculated.
        The true boolean, the last argument is what tells the camera to recenter.
         */
        stage.getViewport().update(width, height, true);

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        stage.dispose();

    }
}
