package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class menuGame implements Screen {
    public createScreen game ;
    public enemy myenemy ;

    public static final float WIDTH_BUTTON = 400 ;
    public static final float HEIGHT_BUTTON = 350 ;

    private Texture Background;
    private Texture PlayActive ;
    private Texture PlayWait ;
    private Texture ExitActive ;
    private Texture ExitWait ;

    public menuGame(createScreen game){
        this.game = game ;
        this.show();
    }

    @Override
    public void show() {
        Background = new Texture("BackgroundGame.png");

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
        game.batch.draw(Background,0,0);

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 550 || Gdx.input.getY() > 650){
            game.batch.draw(PlayWait ,500,200, WIDTH_BUTTON, HEIGHT_BUTTON);
        }else{
            game.batch.draw(PlayActive ,500,200, WIDTH_BUTTON, HEIGHT_BUTTON);
            if (Gdx.input.isTouched()) { game.setScreen(new Map(game));}
        }

        if (Gdx.input.getX() < 500 || Gdx.input.getX() > 870 || Gdx.input.getY() < 750 || Gdx.input.getY() > 850){
            game.batch.draw(ExitWait ,500,0, WIDTH_BUTTON, HEIGHT_BUTTON);
        }else{
            game.batch.draw(ExitActive ,500,0, WIDTH_BUTTON, HEIGHT_BUTTON);
            if (Gdx.input.isTouched()){Gdx.app.exit();}
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }
}
