package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Entity implements Touchable{
    protected double x;
    protected double y;
    protected TextureAtlas textureAtlas;
    protected Texture texture;
    protected Sprite sprite;
    protected int speed;
    protected boolean touchActive=false;
    public boolean isTouchActive(){
        return touchActive;
    }
    public void setTouchActive(){
        touchActive= !touchActive;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Texture texture) {
        this.sprite =new Sprite(texture);
    }

    public abstract void draw(SpriteBatch spriteBatch);

    public boolean isTouched(int x, int y, int width, int height) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) &&
                    (Gdx.input.getX() > x && Gdx.input.getX() < x + width && Gdx.input.getY() > 1000-(y+ height) && Gdx.input.getY() < 1000-y )) {
                //System.out.println(Gdx.input.getX()+" "+Gdx.input.getY() );
                return true;
        }
        return false;
    }
}
