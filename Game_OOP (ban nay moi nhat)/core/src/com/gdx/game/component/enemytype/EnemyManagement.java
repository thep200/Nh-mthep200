package com.gdx.game.component.enemytype;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.component.map.Map.SIDE_BIT_MAP;

public class EnemyManagement implements Disposable {

    private List<Enemy> enemyList;
    private List<Enemy> removeEnemyList;
    private float elapsedTime;

    private Texture imageAttackHouseBlood = new Texture("attribute/Attack-HouseBlood.png");

    private int houseHP = 1000 ;
    private int money = 500 ;
    private int WidthBlood = 0 ;

    public EnemyManagement() {
        enemyList = new ArrayList<>();
        removeEnemyList = new ArrayList<>();
    }

    public int getWidthBlood() { return WidthBlood; }
    public void setWidthBlood(int widthBlood) { WidthBlood = widthBlood; }
    public int getHouseHP() { return houseHP; }
    public void setHouseHP(int houseHP) { this.houseHP = houseHP; }
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money;}
    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public void spawn(float delta, int j, int i){   // tốc độ ra quái Enemy đi ra trên màn chơi
        elapsedTime += delta;

        for (int time = 0; time <= 200; time += 1){

            if (time <= 20){spawnEnemy(delta, time, TypeEnemy.MONSTER_1, j, i);}

//            if (time <= 25){ spawnEnemy(delta,time,TypeEnemy.BAT ,j ,i);}

            if (time <= 70 && time >= 60) {
                spawnEnemy(delta, time, TypeEnemy.MONSTER_2, j, i);
                spawnEnemy(delta,time,TypeEnemy.BAT ,j ,i);
                spawnEnemy(delta, time, TypeEnemy.MONSTER_2, j, i);
            }
            if (time <= 150 && time >= 120) {
                spawnEnemy(delta, time, TypeEnemy.MONSTER_2, j, i);
                spawnEnemy(delta, time, TypeEnemy.MONSTER_1, j, i);
            }
            if (time <= 192 && time >= 180) {
                spawnEnemy(delta, time, TypeEnemy.MONSTER_1, j, i);
                spawnEnemy(delta, time, TypeEnemy.DRAGON, j, i);
            }
        }
    }

    public void drawAndMove(SpriteBatch spriteBatch, List<Point> pointListTrajectory, float delta, int houseHP, int money, int WidthBlood){
        if(enemyList != null) {
            for (Enemy itrE : enemyList) {
                if (itrE.moveFollowTrajectory(pointListTrajectory) && enemyList.lastIndexOf(itrE) < enemyList.size()) {
                    houseHP -= 50;
                    this.setHouseHP(houseHP);     // set lai gia tri cua mau

                    WidthBlood += 10 ;                 // set lai gia tri cua thanh hien thi mau
                    this.setWidthBlood(WidthBlood);

                    if (houseHP == -50) { Gdx.app.exit(); }     // mau bang 0 thi ket thuc game
                }

                if (!itrE.isTargetAble()){
                    money += 100 ;
                    this.setMoney(money);        // set lai gia tri cua tien

                    System.out.println("tien la:" + money);
                }
                if (itrE.moveFollowTrajectory(pointListTrajectory) && enemyList.lastIndexOf(itrE) < enemyList.size() || !itrE.isTargetAble()) {
                    removeEnemyList.add(itrE);
                    itrE.setTargetAble(false);
                }
                itrE.draw(spriteBatch, delta);
            }

            spriteBatch.draw(imageAttackHouseBlood, 1595, 800, WidthBlood, 20);  // ve mau

            enemyList.removeAll(removeEnemyList);
            removeEnemyList.clear();
        }
    }
    public void spawnEnemy(float delta, double time, TypeEnemy typeEnemy, int j, int i){
        if(elapsedTime >= time && elapsedTime - delta <= time){
            switch (typeEnemy) {

                case DRAGON:
                    Dragon dragon = new Dragon((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(dragon);
                    break;

                case BAT:
                    Bat bat = new Bat((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(bat);
                    break;

                case MONSTER_1:
                    Monster1 monster1 = new Monster1((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster1);
                    break;

                case MONSTER_2:
                    Monster2 monster2 = new Monster2((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster2);
                    break;

            }
        }
    }

    @Override
    public void dispose(){

        imageAttackHouseBlood.dispose();

        for (Enemy itrE : enemyList){
            itrE.dispose();
        }

    }
}
