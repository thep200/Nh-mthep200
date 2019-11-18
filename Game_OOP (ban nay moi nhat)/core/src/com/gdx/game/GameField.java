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

    private  int[][] mapStage1 = {

       //    0  1  2  3  4  5  6  7  8  9 10 11 12 13 14  -----> j

            {6, 0, 6, 6, 6, 0, 6, 6, 0, 6, 6, 6, 0, 6, 6}, // 9
            {6, 6, 5, 0, 0, 6, 5, 6, 6, 5, 0, 6, 6, 0, 6}, // 8
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 6, 6, 6, 6, 0}, // 7
            {6, 6, 6, 6, 5, 0, 5, 6, 5, 2, 6, 6, 6, 0, 0}, // 6
            {0, 6, 6, 0, 6, 6, 6, 5, 6, 2, 5, 6, 6, 6, 6}, // 5
            {6, 0, 6, 5, 2, 3, 3, 3, 3, 3, 6, 0, 6, 0, 6}, // 4
            {0, 6, 6, 0, 2, 5, 6, 6, 6, 5, 6, 6, 6, 6, 0}, // 3
            {6, 6, 6, 6, 2, 6, 0, 5, 0, 6, 0, 5, 6, 0, 6}, // 2
            {0, 6, 0, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // 1
            {6, 6, 0, 6, 6, 5, 6, 0, 6, 6, 5, 6, 6, 0, 0}, // 0

                                       //                     |
                                       //                     i

    };
    private List<Point> pointList = new ArrayList<>();

    public GameField(createScreen game) {

        myControlGamePlayer = new controlGamePlay() ;

        spriteBatch = new SpriteBatch();

        towerManagement = new TowerManagement();

        map = new Map(mapStage1,towerManagement.getTowerList());

        enemyManagement = new EnemyManagement();

        pointList = road(mapStage1,0,7,14,1);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        spriteBatch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        myControlGamePlayer.draw(spriteBatch);   // ve control

        map.draw(spriteBatch, mapStage1); // ve map

        if (myControlGamePlayer.statusGamePlay){  //Start

            if (myControlGamePlayer.status){      // PauseGame And Continue

                enemyManagement.spawn(delta, 0, 7);  // ve enemy

                towerManagement.draw(spriteBatch); // ve thap

                towerManagement.shot(enemyManagement.getEnemyList(), delta);  // ve dan

                enemyManagement.drawAndMove(spriteBatch,  pointList, delta, enemyManagement.getHouseHP(), enemyManagement.getMoney(), enemyManagement.getWidthBlood()); // monster move
            }
        }

        spriteBatch.end();

    }

    public List<Point> road(int[][] arr, int j1, int i1,int j2,int i2){

        List<Point> pointList = new ArrayList<Point>();
        while (i1 != i2 || j1 != j2){
            if (arr[arr.length - i1 - 1][j1] == 1){ // right
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
