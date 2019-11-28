package com.gdx.game.menugame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.GameField;
import com.gdx.game.component.enemytype.EnemyManagement;
import com.gdx.game.component.map.Map;
import com.gdx.game.component.map.tower.TowerManagement;

public class menuGame implements Screen {
    public createScreen game ;


    public static final float WIDTH_BUTTON = 400 ;
    public static final float HEIGHT_BUTTON = 350 ;

    private Texture Background;
    private Texture PlayActive ;
    private Texture PlayWait ;
    private Texture ExitActive ;
    private Texture ExitWait ;

    private GameField gameField;

    public menuGame(createScreen game){
        this.game = game ;
        this.show();
        gameField=new GameField(game);
    }

    @Override
    public void show() {
        Background = new Texture("menu/BackgroundGame.png");

        PlayActive = new Texture("menu/Play-Active.png");
        PlayWait = new Texture("menu/Play-Wait.png");

        ExitActive = new Texture("menu/Exit-Active.png");
        ExitWait = new Texture("menu/Exit-Wait.png");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(Background,0,0);

        if (Gdx.input.getX() < 700 || Gdx.input.getX() > 1070 || Gdx.input.getY() < 550 || Gdx.input.getY() > 650){
            game.batch.draw(PlayWait ,700,200, WIDTH_BUTTON, HEIGHT_BUTTON);
        }else{
            game.batch.draw(PlayActive ,700,200, WIDTH_BUTTON, HEIGHT_BUTTON);
            if (Gdx.input.isTouched()) { game.setScreen(gameField);}
        }

        if (Gdx.input.getX() < 700 || Gdx.input.getX() > 1070 || Gdx.input.getY() < 750 || Gdx.input.getY() > 850){
            game.batch.draw(ExitWait ,700,0, WIDTH_BUTTON, HEIGHT_BUTTON);
        }else{
            game.batch.draw(ExitActive ,700,0, WIDTH_BUTTON, HEIGHT_BUTTON);
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
    public void dispose() {

        PlayActive.dispose();
        PlayWait.dispose();

        Background.dispose();

        ExitActive.dispose();
        ExitWait.dispose();


    }
}
