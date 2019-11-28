package com.gdx.game.component.map.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Entity;

public class TowerOption extends Entity {
    public TowerOption(Texture texture1, int x1, int y1){
        texture = texture1;
        sprite = new Sprite(texture);
        x = x1;
        y = y1;
        widthSprite = 40;   // kích thước của option
        heightSprite = 40;
    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition((int)x - widthSprite/2,(int)y - heightSprite/2);
        sprite.draw(spriteBatch);
    }

    public void checkIfTouched() {
        if (checkIfTouched((int) x, (int) y, widthSprite, heightSprite)) { this.setIsTouched(); }
    }

}
