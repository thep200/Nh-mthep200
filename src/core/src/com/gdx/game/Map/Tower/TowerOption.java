package com.gdx.game.Map.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Entity;

public class TowerOption extends Entity {
    private final int widthOfOption=40;
    private final int heightOfOption=40;
    public TowerOption(Texture texture1,int x1,int y1){
        texture=texture1;
        sprite=new Sprite(texture);
        x=x1-widthOfOption/2;y=y1;
    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition((int)x,(int)y);
        sprite.draw(spriteBatch);
    }
    public void isTouched(SpriteBatch spriteBatch) {
        if (isTouched((int) x, (int) y, widthOfOption, heightOfOption)) {
            this.setTouchActive();
        }
    }
}
