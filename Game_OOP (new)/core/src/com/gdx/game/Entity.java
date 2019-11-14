package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Entity {

    protected double x;
    protected double y;

    protected TextureAtlas textureAtlas;
    protected Texture texture;
    protected Sprite sprite;

    protected int currentFrameOfMotion;
    protected int widthSprite;
    protected int heightSprite;
    protected double speed;
    protected float adjustRotation;
    protected double rotation;

    protected boolean isTouched = false;
    public abstract void draw(SpriteBatch spriteBatch);

    public boolean checkIfTouched(int x, int y, int width, int height) {
        if (Gdx.input.getX() > x - width/2 &&
                Gdx.input.getX() < x + width/2 &&
                    Gdx.input.getY() > 1000 - (y+ height/2) &&
                        Gdx.input.getY() < 1000-(y-height/2) )
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                return true;
        return false;
    }

    public boolean isTouched(){
        return isTouched;
    }
    public void setIsTouched(){
        isTouched = !isTouched;
    }
    public double getX() {
        return x;
    }
    public double getSpeed() {
        return speed;
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

}
