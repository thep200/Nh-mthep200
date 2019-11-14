package com.gdx.game.Component.EnemyType;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.Component.Map.Map.SIDE_BIT_MAP;

public class EnemyManagement {
    private List<Enemy> enemyList;
    private List<Enemy> removeEnemyList;
    private float elapsedTime;

    public EnemyManagement() {
        enemyList=new ArrayList<>();
        removeEnemyList=new ArrayList<>();
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }
    public void spawn(float delta, int j, int i){
        elapsedTime += delta;
        for (int time = 1; time < 10; time++){
            spawnEnemy(delta,time,TypeEnemy.DRAGON,j,i);
        }
    }
    public void drawAndMove(SpriteBatch spriteBatch, List<Point> pointListTrajectory){
        if(enemyList != null) {
            for (Enemy itrE : enemyList) {
                if (itrE.moveFollowTrajectory(pointListTrajectory) ) {

                    removeEnemyList.add(itrE);
                    itrE.setTargetAble(false);
                }
                itrE.draw(spriteBatch);
            }
            enemyList.removeAll(removeEnemyList);
            removeEnemyList.clear();
        }
    }
    public void spawnEnemy(float delta, double time, TypeEnemy typeEnemy,int j,int i){
        if(elapsedTime>=time && elapsedTime-delta<=time){
            switch (typeEnemy) {
                case DRAGON:
                    Dragon dragon = new Dragon((int)((j + 0.5)* SIDE_BIT_MAP),(int)((i+0.5)* SIDE_BIT_MAP));
                    enemyList.add(dragon);
                    break;
            }
        }
    }
}
