package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Map implements Screen {
    protected createScreen game;
    public  enemy myenemy ;
    public Animation[] animations;
    public float STAR_TIME;

    private Texture img1;
    private Texture img2;


    public static int X = 0;
    public static int Y = 110;
    public static final float SPEED = 200;




     protected  int[][] map = {
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1,},
            {1, 1, 1, 1, 0, 1, 1, 0, 0, 0,},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1,},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1,},
            {1, 1, 0, 0, 0, 1, 1, 0, 1, 1,},
            {1, 1, 0, 1, 1, 1, 1, 0, 0, 1,},
            {1, 1, 0, 0, 0, 0, 1, 1, 0, 1,},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
    };

    public Map(createScreen game) {
        this.game = game;
        this.show();
    }

    @Override
    public void show() {
        img1 = new Texture("Gollum-100.jpg");
        img2 = new Texture("Gollum-101.jpg");

        animations = new Animation[7];
        TextureRegion[][] AnimationText = TextureRegion.split(new Texture("Gollum-13.png"), 33, 43);
        animations[1] = new Animation(0.2f, AnimationText[0]);
        //myenemy.show();
    }

    @Override
    public void render(float delta) {
        game.batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        STAR_TIME += delta ;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++){
                if (map[i][j] == 0){
                    game.batch.draw(img1, j * 110, i * 100, 110, 100);
                } else {
                    game.batch.draw(img2, j * 110, i * 100, 110, 100);
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            int i = X /110 ;
            int j = (Y + 80)/100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] != 1){
                Y += SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            int i = X /110 ;
            int j = (Y + 100)/100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] != 1){
                Y -= SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            int i = (X + 80)/110 ;
            int j = Y /100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] != 1){
                X -= SPEED*Gdx.graphics.getDeltaTime();
            }

        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            int i = (X + 80)/110 ;
            int j = Y /100 ;
            System.out.println(" i = " + i + "   " + "j = " + j + "    " +"tilemap[j][i] = " + map[j][i] + "   " + "x = " + X + "   " + "y = " + Y);
            if (map[j][i] != 1){
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
