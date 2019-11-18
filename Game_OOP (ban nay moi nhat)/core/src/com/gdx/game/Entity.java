package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import java.awt.*;

public abstract class Entity implements Disposable{
    protected double x;
    protected double y;

    protected TextureAtlas textureAtlas;
    protected int currentFrameOfMotion;

    protected Texture texture;
    protected Sprite sprite;

    protected int widthSprite;
    protected int heightSprite;

    protected double speed;
    protected float adjustRotation;
    protected double rotation;

    protected boolean isTouched = false;

    public boolean isTouched(){
        return isTouched;
    }
    public void setIsTouched(){
        isTouched = !isTouched;
    }
    public double getX() { return x; }
    public int getHeightSprite() {
        return heightSprite;
    }
    public int getWidthSprite() {
        return widthSprite;
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

    public abstract void draw(SpriteBatch spriteBatch);

    public boolean checkIfTouched(int x, int y, int width, int height) {
        if (Gdx.input.getX() > x - width/2 && Gdx.input.getX() < x + width/2 && Gdx.input.getY() > 1000 - (y + height/2) && Gdx.input.getY() < 1000 - (y - height/2) )
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                return true;
        return false;
    }

    public boolean checkCollision(Point point,double adjust){
        if(x + widthSprite/2/adjust > point.getX() && y + heightSprite/2/adjust > point.getY()
        && x - widthSprite/2/adjust < point.getX() && y - heightSprite/2/adjust < point.getY()) //phai tren vs trai duoi
        {
            return true;
        }
        return false;

    }
    public boolean checkCollision(Entity entity,double adjust){
        return (

                  this.checkCollision(new Point((int)(entity.getX()-entity.getWidthSprite()/2),(int)(entity.getY()-entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()+entity.getWidthSprite()/2),(int)(entity.getY()+entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()-entity.getWidthSprite()/2),(int)(entity.getY()+entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()+entity.getWidthSprite()/2),(int)(entity.getY()-entity.getHeightSprite()/2)),adjust)

                ||entity.checkCollision(new Point((int)(this.getX()+this.getWidthSprite()/2),(int)(this.getY()+this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()+this.getWidthSprite()/2),(int)(this.getY()-this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()-this.getWidthSprite()/2),(int)(this.getY()+this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()-this.getWidthSprite()/2),(int)(this.getY()-this.getHeightSprite()/2)),adjust)

        );

    }
    @Override
    public void dispose(){

        texture.dispose();
        textureAtlas.dispose();
        sprite.getTexture().dispose();
    }

}
