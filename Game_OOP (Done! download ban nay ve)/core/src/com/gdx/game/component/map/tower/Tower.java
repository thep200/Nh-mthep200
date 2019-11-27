package com.gdx.game.component.map.tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.enemytype.Enemy;
import com.gdx.game.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.component.map.Map.SIDE_BIT_MAP;

public class Tower extends Entity {

    protected int lvlOfTower;
    protected int type;

    protected double range;

    protected double attackSpeed;
    protected double attackTimeRemain = 0;

    protected Enemy targetedEnemy;

    protected List <Bullet> bulletList;
    protected List <Bullet> removeBulletList;
    protected List <TowerOption> towerOptionList = new ArrayList<>();

    public Enemy getTargetedEnemy() {
        return targetedEnemy;
    }
    public double getRange() {
        return range;
    }
    public int getLvlOfTower() {
        return lvlOfTower;
    }
    public void setLvlOfTower(int lvlOfTower) { this.lvlOfTower = lvlOfTower; }

    public Tower(int row, int column){
        speed = 0;
        type = 0;
        lvlOfTower = 0;
        targetedEnemy = null;

        sprite = new Sprite(new Texture("Tower/Tower0lvl0.png")); // chỗ trống để xây tháp

        heightSprite = 100;     // set kích thước chỗ trống để xây tháp
        widthSprite = 100;

        bulletList = new ArrayList<>();
        removeBulletList = new ArrayList<>();
        towerOptionList = new ArrayList<>();

        x = column*SIDE_BIT_MAP + 0.5*SIDE_BIT_MAP;
        y = row*SIDE_BIT_MAP + 0.5*SIDE_BIT_MAP;

        Texture texture1 = new Texture("OptionTower/Option1lvl0.png");    //option 1
        towerOptionList.add(new TowerOption(texture1,(int)(x/SIDE_BIT_MAP)*SIDE_BIT_MAP,(int)y + 3*SIDE_BIT_MAP/5));
                                              //se dc dieu chinh them bot = 1 luong nua chieu rong cua option

        Texture texture2 = new Texture("OptionTower/Option2lvl0.png");    //option 2
        towerOptionList.add(new TowerOption(texture2,(int)(x/SIDE_BIT_MAP)*SIDE_BIT_MAP + SIDE_BIT_MAP,(int)y + 3*SIDE_BIT_MAP/5));
                                              //se dc dieu chinh them bot = 1 luong nua chieu rong cua option

        Texture texture3 = new Texture("OptionTower/Option3lvl0.png");    //option 3
        towerOptionList.add(new TowerOption(texture3,(int)(x/SIDE_BIT_MAP)*SIDE_BIT_MAP + SIDE_BIT_MAP/2,(int)y - 3*SIDE_BIT_MAP/5));
                                             //se dc dieu chinh them bot = 1 luong nua chieu rong cua option

    }

    public void upgradeTower(int lvl1, int type1){

        texture = new Texture(String.format("Tower/Tower%dlvl%d.png", type1, lvl1));
        sprite = new Sprite(texture);

        lvlOfTower = lvl1;
        setLvlOfTower(lvl1);

        type = type1;
        towerOptionList.clear();

        Texture texture1 = new Texture(String.format("OptionTower/Option%dlvl%d.png", type1, lvl1));
        towerOptionList.add(new TowerOption(texture1, (int)x , (int)y + 3*SIDE_BIT_MAP/5));

        switch (lvl1) {                // thay doi chi so khi nang cap

            case 1:
                if(type == 1){
                    this.buyTower(PRICE_TOWER_1);
                    range = 200;         // tầm bắn
                    attackSpeed = 0.7;   // tốc độ ra đạn trong 1 giây
                }else if(type == 2){
                    this.buyTower(PRICE_TOWER_2);
                    range = 170;
                    attackSpeed = 1.2;
                }else if(type == 3){        // tháp 3 bắn đạn thẳng
                    this.buyTower(PRICE_TOWER_3);
                    range = 150 ;
                    attackSpeed = 2;
                }
                break;

            case 2:
                if(type == 1){
                    this.buyTower(PRICE_UPGRADE_TOWER_1_TURN_1);
                    range = 220 ;
                    attackSpeed = 0.5;
                }else if(type == 2){
                    this.buyTower(PRICE_UPGRADE_TOWER_2_TURN_1);
                    range = 200;
                    attackSpeed = 0.9;
                }else if(type == 3){
                    this.buyTower(PRICE_UPGRADE_TOWER_3_TURN_1);
                    range = 170 ;
                    attackSpeed = 1.6;
                }
                break;

            case 3:
                if(type == 1){
                    this.buyTower(PRICE_UPGRADE_TOWER_1_TURN_2);
                    range = 300 ;
                    attackSpeed = 0.35;
                }else if(type == 2){
                    this.buyTower(PRICE_UPGRADE_TOWER_2_TURN_2);
                    range = 230;
                    attackSpeed = 0.55;
                }else if(type == 3){
                    this.buyTower(PRICE_UPGRADE_TOWER_3_TURN_2);
                    range = 190 ;
                    attackSpeed = 1.1;
                }
              break;
        }
    }

    public void setTargetedEnemy(Enemy targetedEnemy1) {
        targetedEnemy = targetedEnemy1;

        if(bulletList != null && type != 2)
            for(Bullet itr: bulletList){
                if(itr.getTargetedEnemy() == null) {
                    itr.setTargetedEnemy(targetedEnemy);
                }
            }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {

        spriteBatch.draw(sprite,(int)x - widthSprite/2,(int)y - heightSprite/2, widthSprite, heightSprite);

        if(bulletList != null)
        for (Bullet itr : bulletList){
            itr.draw(spriteBatch);
        }
    }

    public void checkIfTouched(SpriteBatch spriteBatch) {
        if(checkIfTouched((int)x, (int)y, widthSprite, heightSprite)){ this.setIsTouched(); }

        for (int i = 0; i < towerOptionList.size(); i++) {

            if (this.isTouched()) {       //neu thap bi click (chuot trai)

                //--------------------------------------- control buy tower and upgrade tower

                if (type == 0 && lvlOfTower == 0 && this.getMoney() >= PRICE_TOWER_1){ towerOptionList.get(0).draw(spriteBatch);}
                if (type == 1 && lvlOfTower == 1 && this.getMoney() >= PRICE_UPGRADE_TOWER_1_TURN_1){ towerOptionList.get(0).draw(spriteBatch);}
                if (type == 1 && lvlOfTower == 2 && this.getMoney() >= PRICE_UPGRADE_TOWER_1_TURN_2){ towerOptionList.get(0).draw(spriteBatch);}

                //---------------------------------------

                if (type == 0 && lvlOfTower == 0 && this.getMoney() >= PRICE_TOWER_2){ towerOptionList.get(1).draw(spriteBatch);}
                if (type == 2 && lvlOfTower == 1 && this.getMoney() >= PRICE_UPGRADE_TOWER_2_TURN_1){ towerOptionList.get(0).draw(spriteBatch);}
                if (type == 2 && lvlOfTower == 2 && this.getMoney() >= PRICE_UPGRADE_TOWER_2_TURN_2){ towerOptionList.get(0).draw(spriteBatch); }

                //---------------------------------------

                if (type == 0 && lvlOfTower == 0 && this.getMoney() >= PRICE_TOWER_3){ towerOptionList.get(2).draw(spriteBatch);}
                if (type == 3 && lvlOfTower == 1 && this.getMoney() >= PRICE_UPGRADE_TOWER_3_TURN_1){ towerOptionList.get(0).draw(spriteBatch);}
                if (type == 3 && lvlOfTower == 2 && this.getMoney() >= PRICE_UPGRADE_TOWER_3_TURN_2){ towerOptionList.get(0).draw(spriteBatch); }


                towerOptionList.get(i).checkIfTouched();           //kiem tra  cac option co bi click hay k

                if (towerOptionList.get(i).isTouched()) {       //new option bi click chuot trai

                    towerOptionList.get(i).setIsTouched();    //upgrade

                    if (lvlOfTower == 0) { upgradeTower(++lvlOfTower, i + 1); } //va sau do tat kich hoat option
                    else { upgradeTower(++lvlOfTower, type); }

                    this.setIsTouched();                     //va tat trang thai kich hoat cua tower
                }
            }
        }
    }

    public double getDistanceToAnEnemy(Enemy enemy){
        double dX = Math.abs(enemy.getX() - x) - enemy.getSpeed()/4;
        double dY = Math.abs(enemy.getY() - y) - enemy.getSpeed()/4;
        return Math.sqrt(dX*dX + dY*dY);
    }

    public void loadBullet(float delta){            //nap dan.
        attackTimeRemain -= delta;
    }

    public void shot(){
            if(attackTimeRemain <= 0){

                if (type == 1) {
                    bulletList.add(new Bullet((int)x, (int)y, targetedEnemy, type, 4)); // đạn đuổi
                }else if(type == 2){
                    bulletList.add(new Bullet((int)x, (int)y, targetedEnemy, type, 4));  // đạn đuổi
                }else if(type == 3) {
                    bulletList.add(new Bullet((int)x, (int)y, (int) targetedEnemy.getX(), (int) targetedEnemy.getY(), targetedEnemy)); // đạn thẳng
                }
                attackTimeRemain = attackSpeed;
            }
    }

    public void bulletMoving( List <Enemy> enemyList){             // cho tất cả các đạn bay đến mục tiêu đạn thẳng
        if(bulletList != null)

            for(Bullet itrB : bulletList){
                itrB.move();
                for (Enemy itrE:enemyList){
                    if(itrE.checkCollision(itrB,1) ){  // 1 : liên quan đến tốc độ đạn bắn thẳng

                        itrE.setJustGotHit(true);
                        itrE.getHitPoint().setValue(itrE.getHitPoint().getValue() - itrB.getDamage());  // trừ máu của Enemy

                        if(itrE.getHitPoint().getValue() <= 0){

                            itrE.setTargetAble(false);
                        }
                        removeBulletList.add(itrB);
                    }
                }
            }

        bulletList.removeAll(removeBulletList);
        removeBulletList.clear();
    }

    @Override
    public void dispose(){

        super.dispose();

        for (TowerOption itrO : towerOptionList){
            itrO.dispose();
        }

        for (Bullet itrB : bulletList){
            itrB.dispose();
        }

        for (Bullet itrB : removeBulletList){
            itrB.dispose();
        }

    }


}
