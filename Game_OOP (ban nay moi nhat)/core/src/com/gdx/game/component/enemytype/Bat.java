package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;

public class Bat extends Enemy {

    public Bat(int x,int y) {
        super("Bat",5, x, y,"Enemy/Bat");
        speed = 1.15;
        adjustRotation = 90;
        hitPoint = new HitPoint(200,widthSprite);
    }
    @Override
    public void draw(SpriteBatch spriteBatch) { super.draw(spriteBatch); }

    @Override
    public void dispose(){
        super.dispose();
    }
}
