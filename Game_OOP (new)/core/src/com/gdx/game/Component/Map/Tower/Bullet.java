package com.gdx.game.Component.Map.Tower;

import com.gdx.game.Component.EnemyType.Enemy;
import com.gdx.game.MovingEntity;

public class Bullet extends MovingEntity {
    private Enemy targetedEnemy;
    public Bullet(int x1, int y1, Enemy targetedEnemy1){
        super("Bullet",1,x1,y1,"Bullet");
        targetedEnemy = targetedEnemy1;
        x = x1;
        y = y1;
        speed = 10;
        widthSprite = 10;
        heightSprite = 10;
    }

    public Enemy getTargetedEnemy() {
        return targetedEnemy;
    }
    public void setTargetedEnemy(Enemy targetedEnemy) {
        this.targetedEnemy = targetedEnemy;
    }

    public boolean move() {
        return moveTo((int)targetedEnemy.getX(),(int)targetedEnemy.getY());
    }
}
