package com.mygdx.MyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation ;

public class MyMainScreenGame implements Screen {
    CreateScreenGame game ;

    public static final float speed = 67 ;
    float x = 0;
    float y = 880;

    private GameField gameField;
    Texture img;
    Animation[] animations ;
    float startTime;

    public MyMainScreenGame(CreateScreenGame game){
        this.game = game ;
        this.show();
    }

    public void setGame(CreateScreenGame game) { this.game = game; }
    public CreateScreenGame getGame() { return game; }

    @Override
    public void show() {

        img = new Texture("New - Map1.jpg");
        gameField=new GameField();
        animations = new Animation[7] ;
        TextureRegion[][] AnimationText = TextureRegion.split(new Texture("001.png"),33, 43);
        animations[1] = new Animation(0.2f, AnimationText[0]);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        startTime += delta ;

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed*Gdx.graphics.getDeltaTime() ;

        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed*Gdx.graphics.getDeltaTime();

        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed*Gdx.graphics.getDeltaTime() ;

        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed*Gdx.graphics.getDeltaTime() ;
        }

        game.batch.begin();

        game.batch.draw(img,0,0);
        game.batch.draw((TextureRegion) animations[1].getKeyFrame(startTime,true), x, y,50,65);
        gameField.draw(game.batch);

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
