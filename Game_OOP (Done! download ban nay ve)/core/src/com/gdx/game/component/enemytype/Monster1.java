package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;

public class Monster1 extends Enemy {

    public Monster1(int x, int y) {
        super("Monster",2, x, y,"Enemy/Monster1");
        speed = 1.75;    // tốc độ của monster
     //   adjustRotation -= 180;      // xoay hình ảnh monster
        hitPoint = new HitPoint(45, widthSprite); // blood monster
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
    @Override
    public void dispose(){ super.dispose(); }
}

