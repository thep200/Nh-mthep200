package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;

public class Monster5 extends Enemy {

    public Monster5(int x, int y) {
        super("Monster",2, x, y,"Enemy/Monster5");
        speed = 2.5;    // tốc độ của monster
        //   adjustRotation -= 180;      // xoay hình ảnh monster
        hitPoint = new HitPoint(40, widthSprite); // blood monster
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
    @Override
    public void dispose(){ super.dispose(); }
}