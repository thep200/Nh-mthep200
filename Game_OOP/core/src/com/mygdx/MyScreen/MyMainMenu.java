package com.mygdx.MyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MyMainMenu implements Screen {

    CreateScreenGame game ;
    Texture backGround;

    public static final float Width_Button = 400 ;
    public static final float Height_Button = 350 ;

    Texture playActive;
    Texture playWait;

    Texture exitActive;
    Texture exitWait;

    Texture soundActive;
    Texture soundWait;

    Texture instructorActive;
    Texture instructorWait;

    public MyMainMenu(CreateScreenGame game){
        this.game = game ;
        this.show();
    }

    public void setGame(CreateScreenGame game) { this.game = game; }
    public CreateScreenGame getGame() { return game; }


    @Override
    public void show() {
        backGround = new Texture("BackgroundGame.png");

        playActive = new Texture("Play-Active.png");
        playWait = new Texture("Play-Wait.png");

        exitActive = new Texture("Exit-Active.png");
        exitWait = new Texture("Exit-Wait.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(backGround,0,0);

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 550 || Gdx.input.getY() > 650){
            game.batch.draw(playWait,500,200, Width_Button, Height_Button);
        }else{
            game.batch.draw(playActive,500,200, Width_Button, Height_Button);
            if (Gdx.input.isTouched()) { game.setScreen(new MyMainScreenGame(game));}
        }

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 750 || Gdx.input.getY() > 850){
            game.batch.draw(exitWait,500,0, Width_Button, Height_Button);
        }else{
            game.batch.draw(exitActive,500,0, Width_Button, Height_Button);
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
