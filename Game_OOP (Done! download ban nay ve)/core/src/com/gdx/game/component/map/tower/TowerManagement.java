package com.gdx.game.component.map.tower;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.component.enemytype.Enemy;

import java.util.ArrayList;
import java.util.List;

public class TowerManagement implements Disposable {
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
        for(Tower itrT : towerList) {
            itrT.loadBullet(delta);
            itrT.bulletMoving(enemyList);
            for (Enemy itrE : enemyList) {
                if(itrT.getDistanceToAnEnemy(itrE) < itrT.getRange() && itrT.getTargetedEnemy() == null && itrE.isTargetAble() && itrT.getLvlOfTower() != 0 ){
                    itrT.setTargetedEnemy(itrE);
                }
                if(itrT.getDistanceToAnEnemy(itrE) < itrT.getRange() &&  itrT.getTargetedEnemy() == itrE && itrE.isTargetAble()){
                        itrT.shot();
                }
                if((itrT.getDistanceToAnEnemy(itrE) > itrT.getRange()) && itrT.getTargetedEnemy() == itrE){
                    itrT.setTargetedEnemy(null);
                }

            }
            if(itrT.getTargetedEnemy() != null && !itrT.getTargetedEnemy().isTargetAble())
            itrT.setTargetedEnemy(null);
        }
    }

    @Override
    public void dispose() {

        for (Tower itrT: towerList){
            itrT.dispose();
        }
    }
}
