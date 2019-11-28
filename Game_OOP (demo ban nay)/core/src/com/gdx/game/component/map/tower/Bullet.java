package com.gdx.game.component.map.tower;

import com.gdx.game.component.enemytype.Enemy;
import com.gdx.game.MovingEntity;

import java.awt.*;

public class Bullet extends MovingEntity {

    private Enemy targetedEnemy;
    protected Point targetedPosition;

    protected int damage;
    private int type;

    public void setTargetedPosition(Point targetedPosition) { this.targetedPosition = targetedPosition; }
    public Enemy getTargetedEnemy() { return targetedEnemy; }
    public void setTargetedEnemy(Enemy targetedEnemy) { this.targetedEnemy = targetedEnemy; }
    public int getDamage() { return damage; }
    public void setDamage(int damage) { this.damage = damage; }


    public Bullet(int x1, int y1, Enemy targetedEnemy1, int type1, int numberFrameOfMotion){

        super("Bullet", numberFrameOfMotion, x1, y1, String.format("Bullet/Bullet%d", type1));
        type = type1;
        targetedEnemy = targetedEnemy1;
        targetedPosition = null;
        x = x1;
        y = y1;

        if(type == 1){

            speed = 3.5;
            damage = 20;

            widthSprite = 40;  // kich thuoc dan
            heightSprite = 40;
        }

        if(type == 2){

            speed = 3;
            damage = 45;

            widthSprite = 40;
            heightSprite = 40;
        }
    }

    public Bullet(int x1, int y1, int xDes, int yDes, Enemy targetedEnemy1){   // tower2 ban dan thang
        super("Bullet",4, x1, y1,"Bullet/Bullet3");

        setTargetedPosition(new Point(xDes, yDes));
        targetedEnemy = targetedEnemy1;
        x = x1;
        y = y1;

        speed = 5.5;
        damage = 110;

        widthSprite = 40;
        heightSprite = 40;
    }

    public boolean move() {
        if(targetedPosition != null) {
            return moveTo((int)targetedPosition.getX(),(int)targetedPosition.getY());
        }else
            return moveTo((int) targetedEnemy.getX(), (int) targetedEnemy.getY());
    }

    @Override
    public void dispose(){
        super.dispose();
    }
}
