package com.gdx.game.Component.Map.Tower;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Component.EnemyType.Enemy;

import java.util.ArrayList;
import java.util.List;

public class TowerManagement
{
    private List<Tower> towerList;
    public TowerManagement(){
        towerList = new ArrayList<>();
    }
    public List<Tower> getTowerList() {
        return towerList;
    }

    public void draw(SpriteBatch spriteBatch){
        for (Tower itr: towerList){
            itr.draw(spriteBatch);
            itr.checkIfTouched(spriteBatch);
        }
    }
    public void shot(List<Enemy> enemyList, float delta){
        for(Tower itrT:towerList) {
            itrT.loadBullet(delta);
            itrT.bulletMoving();
            for (Enemy itrE : enemyList) {
                if(itrT.getDistanceToAnEnemy(itrE)<itrT.getRange() && itrT.getTargetedEnemy() == null && itrE.isTargetAble() && itrT.getLvlOfTower()!=0){
                    itrT.setTargetedEnemy(itrE);
                }
                if(itrT.getDistanceToAnEnemy(itrE)<itrT.getRange() &&  itrT.getTargetedEnemy() == itrE && itrE.isTargetAble()){
                    itrT.shot();
                }
                if((itrT.getDistanceToAnEnemy(itrE)>itrT.getRange() )&&itrT.getTargetedEnemy() == itrE){
                    itrT.setTargetedEnemy(null);
                }
            }
            if(itrT.getTargetedEnemy() != null&& !itrT.getTargetedEnemy().isTargetAble())
            itrT.setTargetedEnemy(null);
        }
    }

}
