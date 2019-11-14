package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Map.Map;
import com.gdx.game.MenuAndScreen.createScreen;

import java.util.ArrayList;
import java.util.List;

public class GameField implements Screen {
    protected createScreen game;
    private Map map;
    private SpriteBatch spriteBatch;
    private  int[][] mapStage1 = {
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
            {0, 0, 0, 0, 0, 2, 1, 1, 1, 1,},
            {1, 1, 1, 1, 0, 1, 2, 0, 0, 0,},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1,},
            {1, 1, 1, 1, 0, 1, 1, 0, 1, 1,},
            {1, 1, 0, 0, 0, 1, 1, 0, 1, 1,},
            {1, 1, 0, 1, 1, 1, 1, 0, 0, 1,},
            {1, 1, 0, 0, 0, 0, 1, 1, 0, 1,},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1,},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1,},
    };
    public GameField(createScreen game) {
        spriteBatch=new SpriteBatch();
        map=new Map(mapStage1);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() { }

}
