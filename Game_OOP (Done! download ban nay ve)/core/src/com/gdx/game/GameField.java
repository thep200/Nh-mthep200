package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.enemytype.EnemyManagement;
import com.gdx.game.component.map.Map;
import com.gdx.game.component.map.tower.TowerManagement;
import com.gdx.game.menugame.createScreen;
import com.gdx.game.menugame.controlGamePlay ;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class GameField implements Screen {

    private Map map;
    private controlGamePlay myControlGamePlayer ;
    private SpriteBatch spriteBatch;
    private EnemyManagement enemyManagement;
    private TowerManagement towerManagement;

    private List<Point> pointList = new ArrayList<>();

    public GameField(createScreen game) {
        myControlGamePlayer = new controlGamePlay() ;
        spriteBatch = new SpriteBatch();
        towerManagement = new TowerManagement();
        map = new Map(map.loadTileMap(),towerManagement.getTowerList());
        enemyManagement = new EnemyManagement();
        pointList = road(map.loadTileMap(),0,2,14,7);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        myControlGamePlayer.draw(spriteBatch);                                   // draw control

        map.draw(spriteBatch, map.loadTileMap());                                // draw map

        if (myControlGamePlayer.statusGamePlay){                                 // Start

            if (myControlGamePlayer.status){                                     // PauseGame And Continue

                enemyManagement.spawn(delta, 0, 2, myControlGamePlayer.gameStage);                // draw enemy

                towerManagement.draw(spriteBatch);                               // draw tower

                towerManagement.shot(enemyManagement.getEnemyList(), delta);     // draw monster

                enemyManagement.drawAndMove(spriteBatch,  pointList, delta);     // monster move
            }
        }

        spriteBatch.end();
    }

    public List<Point> road(int[][] arr, int j1, int i1,int j2,int i2){

        List<Point> pointList = new ArrayList<Point>();
        while (i1 != i2 || j1 != j2){
            if (arr[arr.length - i1 - 1][j1] == 1 ){ // right
                j1++ ;
                pointList.add(new Point(j1, i1));
            }
            if (arr[arr.length - i1 - 1][j1] == 3){ // left
                j1--  ;
                pointList.add(new Point(j1, i1));
            }
            if (arr[arr.length - i1 - 1][j1] == 2){ // down
                i1--;
                pointList.add(new Point(j1, i1));
            }
            if (arr[arr.length - i1 - 1][j1] == 4){ // up
                i1++;
                pointList.add(new Point(j1, i1));
            }
        }
        return pointList ;
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
    public void dispose() {
        map.dispose();
        myControlGamePlayer.dispose();
        spriteBatch.dispose();
        towerManagement.dispose();
        enemyManagement.dispose();
    }

}
