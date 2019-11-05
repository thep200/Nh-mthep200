package com.mygdx.MyScreen.Tower;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.MyScreen.Entity;
import com.mygdx.MyScreen.Touchable;

public class Spot extends Entity implements Touchable {

    public Spot() {
        super();
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition((int)x,(int)y);
        sprite.draw(batch);
    }

    @Override
    public boolean isTouched() {

        return false;
    }

    @Override
    public boolean doIfTouched() {
        return false;
    }
}
