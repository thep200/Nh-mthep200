package com.gdx.game.Component.Map.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Component.EnemyType.Enemy;
import com.gdx.game.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.Component.Map.Map.SIDE_BIT_MAP;

public class Tower extends Entity {
    protected int lvlOfTower;
    protected int type;
    protected double range;
    protected double attackSpeed;
    protected double attackTimeRemain = 0;

    protected Enemy targetedEnemy;

    protected List<Bullet> bulletList;
    protected List<Bullet> removeBulletList;
    protected List<TowerOption> towerOptionList=new ArrayList<>();

    public Tower(int row, int column){
        // khoi tao tower lvl0 hay chi don gian la vi tri dat thap chua co j
        //cac chi so cua thap
        range = 300;
        speed = 0;
        type = 1;
        lvlOfTower = 0;
        attackSpeed = 10;
        targetedEnemy = null;

        sprite = new Sprite(new Texture("Tower/Tower1lvl0.png"));
        heightSprite = 80;
        widthSprite = 80;

        bulletList = new ArrayList<>();
        removeBulletList = new ArrayList<>();

        x = column* SIDE_BIT_MAP +0.5* SIDE_BIT_MAP;
        y = row* SIDE_BIT_MAP +0.5* SIDE_BIT_MAP;

        Texture texture1 = new Texture("OptionTower/Option1lvl0.png"); //option 1
        towerOptionList.add(new TowerOption(texture1,(int)(x/ SIDE_BIT_MAP)* SIDE_BIT_MAP,(int)y+3* SIDE_BIT_MAP /5));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option

        Texture texture2 = new Texture("OptionTower/Option2lvl0.png"); //option 2
        towerOptionList.add(new TowerOption(texture2,(int)(x/ SIDE_BIT_MAP)* SIDE_BIT_MAP + SIDE_BIT_MAP,(int)y+3* SIDE_BIT_MAP /5));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option
    }
    public void upgradeTower(int lvl1,int type1){
        texture = new Texture(String.format("Tower/Tower%dlvl%d.png", type1, lvl1));
        sprite = new Sprite(texture);
        lvlOfTower = lvl1;
        switch (lvl1) {
            case 1:
            Texture texture1 = new Texture(String.format("OptionTower/Option1lvl%d.png", lvl1));
            towerOptionList.add(new TowerOption(texture1, (int) (x / SIDE_BIT_MAP) * SIDE_BIT_MAP, (int) y + 3 * SIDE_BIT_MAP / 5));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option

            Texture texture2 = new Texture(String.format("OptionTower/Option2lvl%d.png", lvl1));
            towerOptionList.add(new TowerOption(texture2, (int) (x / SIDE_BIT_MAP) * SIDE_BIT_MAP + SIDE_BIT_MAP, (int) y + 3 * SIDE_BIT_MAP / 5));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option

                if(type1 == 1){
                    attackSpeed = 0.5;
                }
                if(type1 == 2){
                    attackSpeed = 2;
                }
                break;
            case 2:

        }
    }
    public int getLvlOfTower() { return lvlOfTower; }
    public void setTargetedEnemy(Enemy targetedEnemy1) {
        targetedEnemy = targetedEnemy1;
        if(bulletList != null)
            for(Bullet itr : bulletList){
                if(itr.getTargetedEnemy() == null) {
                    itr.setTargetedEnemy(targetedEnemy);
                }
            }
    }
    public Enemy getTargetedEnemy() { return targetedEnemy; }
    public double getRange() { return range; }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite,(int)x- widthSprite /2,(int)y- heightSprite /2, widthSprite, heightSprite);
        if(bulletList != null)
        for (Bullet itr : bulletList){
            itr.draw(spriteBatch);
        }
    }
    public void checkIfTouched(SpriteBatch spriteBatch) {
        if(checkIfTouched((int)x,(int)y, widthSprite, heightSprite)){
            this.setIsTouched();
        }
        for (int i=0;i<towerOptionList.size();i++)
            if(this.isTouched()){                           //neu thap bi click (chuot trai)
                towerOptionList.get(i).draw(spriteBatch);               //ve cac option
                towerOptionList.get(i).checkIfTouched();           //kiem tra  cac option co bi click hay k
                if(towerOptionList.get(i).isTouched()){                //new option bi click chuot trai
                    upgradeTower(++lvlOfTower,i+1);                       //upgrade
                    towerOptionList.get(i).setIsTouched();            //va sau do tat kich hoat option
                    this.setIsTouched();                              //va tat trang thai kich hoat cua tower
                }
        }
    }
    public double getDistanceToAnEnemy(Enemy enemy){
        double dX=Math.abs(enemy.getX()-x)-enemy.getSpeed()/4;
        double dY=Math.abs(enemy.getY()-y)-enemy.getSpeed()/4;
        return Math.sqrt(dX*dX+dY*dY);

    }
    public void loadBullet(float delta){            //nap dan.
        attackTimeRemain-=delta;
    }
    public void shot(){
            if(attackTimeRemain<=0){
                bulletList.add(new Bullet((int)x,(int)y,targetedEnemy));
                attackTimeRemain=attackSpeed;
            }
    }
    public void bulletMoving(){             //cho tat ca cac dan bay den muc tieu
        if(bulletList!=null)
            for(Bullet itr:bulletList){
                if(itr.move()) {
                    removeBulletList.add(itr);
                }
            }
        bulletList.removeAll(removeBulletList);
        removeBulletList.clear();
    }
}
