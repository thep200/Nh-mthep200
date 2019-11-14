package com.gdx.game.MenuAndScreen;

import com.badlogic.gdx.Game ;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class createScreen extends Game {
    public SpriteBatch batch ;

    @Override
    public void create () {
        batch = new SpriteBatch();
        this.setScreen(new menuGame(this));
    }

    @Override
    public void render () { super.render(); }

}
