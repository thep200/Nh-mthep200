package com.mygdx.MyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MyMainMenu implements Screen {

    CreateScreenGame game ;
    Texture Backgroud ;

    public static final float Width_Button = 400 ;
    public static final float Height_Button = 350 ;

    Texture PlayActive ;
    Texture PlayWait ;

    Texture ExitActive ;
    Texture ExitWait ;

    Texture SoundActive ;
    Texture SoundWait ;

    Texture InstructorActive ;
    Texture InstructorWait ;

    public MyMainMenu(CreateScreenGame game){
        this.game = game ;
        this.show();
    }

    public void setGame(CreateScreenGame game) { this.game = game; }
    public CreateScreenGame getGame() { return game; }


    @Override
    public void show() {
        Backgroud = new Texture("BackgroundGame.png");

        PlayActive = new Texture("Play-Active.png");
        PlayWait = new Texture("Play-Wait.png");

        ExitActive = new Texture("Exit-Active.png");
        ExitWait = new Texture("Exit-Wait.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(Backgroud,0,0);

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 550 || Gdx.input.getY() > 650){
            game.batch.draw(PlayWait ,500,200, Width_Button, Height_Button);
        }else{
            game.batch.draw(PlayActive ,500,200, Width_Button, Height_Button);
            if (Gdx.input.isTouched()) { game.setScreen(new MyMainScreenGame(game));}
        }

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 750 || Gdx.input.getY() > 850){
            game.batch.draw(ExitWait ,500,0, Width_Button, Height_Button);
        }else{
            game.batch.draw(ExitActive ,500,0, Width_Button, Height_Button);
           if (Gdx.input.isTouched()){Gdx.app.exit();}
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
