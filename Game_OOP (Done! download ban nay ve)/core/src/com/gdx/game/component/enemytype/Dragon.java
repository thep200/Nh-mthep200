package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;

public class Dragon extends Enemy {


    public Dragon(int x, int y) {
        super("Dragon",3, x, y,"Enemy/Dragon");
        speed = 1.425;    // tốc độ của monster
        adjustRotation =- 90;      // xoay hình ảnh monster
        hitPoint = new HitPoint(1100, widthSprite); // blood monster
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
    @Override
    public void dispose(){ super.dispose(); }
}

