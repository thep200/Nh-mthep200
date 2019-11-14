package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Component.EnemyType.EnemyManagement;
import com.gdx.game.Component.Map.Map;
import com.gdx.game.Component.Map.Tower.TowerManagement;
import com.gdx.game.MenuAndScreen.createScreen;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameField implements Screen {
    //protected createScreen game;
    private Map map;
    private SpriteBatch spriteBatch;
    private EnemyManagement enemyManagement;
    private TowerManagement towerManagement;
    //Dragon dragon;
    private  int[][] mapStage1 = {

         //  0  1  2  3  4  5  6  7  8  9 10 11 12 13 14
            {0, 6, 6, 0, 0, 6, 6, 6, 6, 0, 0, 0, 0, 0, 0}, // 9
            {0, 5, 6, 5, 0, 5, 0, 6, 5, 0, 6, 6, 0, 6, 6}, // 8
            {1, 1, 1, 1, 1, 1, 1, 2, 0, 0, 6, 6, 6, 6, 0}, // 7
            {0, 5, 0, 5, 0, 6, 6, 2, 0, 0, 6, 0, 6, 6, 0}, // 6
            {6, 6, 0, 6, 0, 6, 6, 2, 5, 0, 0, 0, 6, 6, 6}, // 5
            {0, 6, 6, 6, 0, 0, 5, 1, 1, 1, 1, 2, 0, 6, 6}, // 4
            {0, 0, 6, 6, 0, 6, 0, 0, 0, 0, 5, 2, 0, 6, 0}, // 3
            {0, 6, 0, 0, 6, 6, 0, 6, 6, 6, 0, 2, 5, 0, 0}, // 2
            {0, 6, 6, 6, 6, 6, 0, 0, 6, 6, 0, 1, 1, 1, 1}, // 1
            {0, 0, 6, 0, 6, 6, 0, 0, 0, 0, 5, 0, 5, 6, 0}, // 0

    };
    private List<Point> pointList = new ArrayList<>();

    public GameField(createScreen game) {
        spriteBatch = new SpriteBatch();
        towerManagement = new TowerManagement();
        map = new Map(mapStage1,towerManagement.getTowerList());
        enemyManagement = new EnemyManagement();
        pointList = road(mapStage1,0,7,14,1);
    }

    @Override
    public void show() { }

    @Override
    public void render(float delta) {

        spriteBatch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        map.draw(spriteBatch,mapStage1);
        towerManagement.shot(enemyManagement.getEnemyList(),delta);
        towerManagement.draw(spriteBatch);
        enemyManagement.spawn(delta, 0, 7);
        enemyManagement.drawAndMove(spriteBatch,  pointList);
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
    public List<Point> road(int[][] arr, int j1, int i1,int j2,int i2){

        List<Point> pointList = new ArrayList<Point>();
        while (i1!=i2 || j1!=j2){
            if (arr[arr.length - i1 - 1][j1] == 1){ // right
                j1++ ;
                pointList.add(new Point(j1, i1));
             //   System.out.println("right");
            }
            if (arr[arr.length - i1 - 1][j1] == 3){ // left
                j1--  ;
                pointList.add(new Point(j1, i1));
             //   System.out.println("lefft");
            }
            if (arr[arr.length - i1 - 1][j1] == 2){ // down
                i1--;
                pointList.add(new Point(j1, i1));
                //System.out.println("down");
            }
            if (arr[arr.length - i1 - 1][j1] == 4){ // up
                i1++;
                pointList.add(new Point(j1,i1));
              //  System.out.println("up");
            }

        }
        return pointList ;
    }

}
