package com.gdx.game.Component.EnemyType;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Dragon extends Enemy {

    public Dragon(int x,int y) {
        super("Dragon",3, x, y,"Enemy");
        speed = 5;
        adjustRotation =-90;
    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
