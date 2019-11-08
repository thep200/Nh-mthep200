package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;

import javax.swing.*;
import java.awt.*;


public abstract class Enemy extends Entity{
    protected int hitPoint;

    public Enemy(int hitPoint,Texture texture) {
        this.hitPoint = hitPoint;
        this.texture  = texture;
        sprite=new Sprite(texture);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition((int)x,(int)y);
        sprite.draw(spriteBatch);
    }
}
