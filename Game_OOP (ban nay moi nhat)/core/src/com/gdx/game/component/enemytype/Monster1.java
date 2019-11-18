package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;

public class Monster1 extends Enemy {

    public Monster1(int x, int y) {
        super("Monster",6, x, y,"Enemy/Monster1");
        speed = 0.6;    // tốc độ của monster
     //   adjustRotation -= 180;      // xoay hình ảnh monster
        hitPoint = new HitPoint(850, widthSprite); // blood monster
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
    @Override
    public void dispose(){ super.dispose(); }
}

