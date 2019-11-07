package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class enemy extends Map implements Screen {
    public static int X = 0;
    public static int Y = 110;
    public Animation[] animations;
    public float STAR_TIME;

    private static int HP ;
    public static final float SPEED = 67;

    public enemy(createScreen game) {
        super(game);
    }


    @Override
    public void show() {
        animations = new Animation[7];
        TextureRegion[][] AnimationText = TextureRegion.split(new Texture("Gollum-13.png"), 33, 43);
        animations[1] = new Animation(0.2f, AnimationText[0]);
    }

    @Override
    public void render(float delta) {
        STAR_TIME += delta;

        game.batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            int i = X /110 ;
            int j = (Y +100)/100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] == 0){
                Y += SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            int i = X /110 ;
            int j = (Y + 100)/100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] == 0){
                Y -= SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            int i = (X + 100)/110 ;
            int j = Y /100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] == 0){
                X -= SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            int i = (X + 100)/110 ;
            int j = Y /100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] == 0){
                X += SPEED*Gdx.graphics.getDeltaTime();
            }
        }

        game.batch.draw((TextureRegion) animations[1].getKeyFrame(STAR_TIME, true), X, Y, 80, 95);
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
