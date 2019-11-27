package com.gdx.game.component.attribute;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Entity;

public class HitPoint extends Entity {

    private double value;
    private int maxValue;

    public HitPoint(int maxValue1, int width1){
        texture = new Texture("Attribute/HitPoint.png");

        sprite = new Sprite(texture);

        maxValue = maxValue1;
        value = maxValue;

        widthSprite = width1 ;
        heightSprite = 7;
    }

    public void setPosition(int x1,int y1){
        x = x1;
        y = y1;
    }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }
    public int getMaxValue() { return maxValue; }

    public void draw(SpriteBatch spriteBatch, float scaleX) {
        spriteBatch.draw(sprite,(int)x - widthSprite/2,(int)y - heightSprite/2,widthSprite/2,heightSprite/2, widthSprite, heightSprite, scaleX,1,(float)rotation);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
    }



}
