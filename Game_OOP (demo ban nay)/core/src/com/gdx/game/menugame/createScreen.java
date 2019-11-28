package com.gdx.game.menugame;

import com.badlogic.gdx.Game ;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class createScreen extends Game {
    public SpriteBatch batch ;
    private menuGame menuGame;



    @Override
    public void create () {
        menuGame=new menuGame(this);
        batch = new SpriteBatch();
        this.setScreen(menuGame);

    }

    @Override
    public void render () { super.render(); }

    @Override
    public void dispose(){
        batch.dispose();

    }

}
