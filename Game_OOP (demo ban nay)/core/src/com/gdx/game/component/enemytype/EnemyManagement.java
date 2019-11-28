package com.gdx.game.component.enemytype;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.Entity;
import com.gdx.game.GameField;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.component.map.Map.SIDE_BIT_MAP;

public class EnemyManagement extends Entity implements Disposable {

    private List<Enemy> enemyList;
    private List<Enemy> removeEnemyList;
    private float elapsedTime;
    private  Sound sound = GameField.assetManager.get("Sound/die.ogg");
    private Texture imageAttackHouseBlood = new Texture("attribute/Attack-HouseBlood.png");

    public EnemyManagement() {
        enemyList = new ArrayList<>();
        removeEnemyList = new ArrayList<>();
    }

    public List<Enemy> getEnemyList() { return enemyList; }

    public void spawn(float delta, int j, int i, int gameStage) {   // tốc độ ra quái Enemy đi ra trên màn chơi
        elapsedTime += delta;

        switch (gameStage) {
            case 1:
                for (int time = 0 ; time <= GAME_TIME_END; time += 1){
                    if (time >= 0 && time < 30)spawnEnemy(delta, time, TypeEnemy.MONSTER_1, j, i);
                    if (time % 2 == 0 && time < 30 && time > 15)spawnEnemy(delta, time, TypeEnemy.MONSTER_4, j, i);
                    if (time % 3 == 0 && time < 40 && time > 15)spawnEnemy(delta, time, TypeEnemy.MONSTER_6, j, i);
                }
                break;

            case 2:
                if (elapsedTime >= 100) {
                    for (int Time = 0; Time <= GAME_TIME_END; Time += 1) {
                        if (Time % 2 == 0 && Time < 140) spawnEnemy(delta, Time, TypeEnemy.MONSTER_2, j, i);
                        if (Time % 1 == 0 && Time < 120) spawnEnemy(delta, Time, TypeEnemy.MONSTER_1, j, i);
                        if (Time % 5 == 0 && Time < 160) spawnEnemy(delta, Time, TypeEnemy.MONSTER_7, j, i);
                    }
                }
                break;

            case 3:
                if (elapsedTime >= 200) {
                    for (int Time = 0; Time <= GAME_TIME_END; Time += 1) {
                        if (Time % 1 == 0 && Time < 220) spawnEnemy(delta, Time, TypeEnemy.MONSTER_5, j, i);
                        if (Time % 4 == 0 && Time < 230) spawnEnemy(delta, Time, TypeEnemy.MONSTER_BAT, j, i);
                        if (Time % 2 == 0 && Time < 225) spawnEnemy(delta, Time, TypeEnemy.MONSTER_3, j, i);
                        if (Time % 10 == 0) spawnEnemy(delta, Time, TypeEnemy.MONSTER_DRAGON, j, i);
                        if (Time % 5 == 0) spawnEnemy(delta, Time, TypeEnemy.MONSTER_7, j, i);
                    }
                }
                break;

        }
    }

    public void drawAndMove(SpriteBatch spriteBatch, List<Point> pointListTrajectory, float delta){

            score += this.getMoney() ;

        if(enemyList != null) {

            spriteBatch.draw(imageAttackHouseBlood, 1580, 850, WidthBlood, 20);  // ve mau
            myBitMap.draw(spriteBatch, score, 1620, 960) ;                              // ve tien

            score = "" ;

            for (Enemy itrE : enemyList) {

                if (itrE.getHitPoint().getValue()<=0){              // tien

                    if (itrE instanceof Bat) { this.bonusMoney(15); }
                    if (itrE instanceof Dragon){ this.bonusMoney(100); }
                    if (itrE instanceof Monster1){ this.bonusMoney(10); }
                    if (itrE instanceof Monster2){ this.bonusMoney(20); }
                    if (itrE instanceof Monster3){ this.bonusMoney(15); }
                    if (itrE instanceof Monster4){ this.bonusMoney(10); }
                    if (itrE instanceof Monster5){ this.bonusMoney(15); }
                    if (itrE instanceof Monster6){ this.bonusMoney(50); }
                    if (itrE instanceof Monster7){ this.bonusMoney(70); }

                }

                if ((itrE.moveFollowTrajectory(pointListTrajectory) && enemyList.lastIndexOf(itrE) < enemyList.size() )|| itrE.getHitPoint().getValue()<=0) {
                    removeEnemyList.add(itrE);
                    itrE.setTargetAble(false);
                    sound.play();
                }
                itrE.draw(spriteBatch, delta);
            }

            enemyList.removeAll(removeEnemyList);
            //removeEnemyList.clear();
        }
    }

    public void spawnEnemy(float delta, double time, TypeEnemy typeEnemy, int j, int i){
        if(elapsedTime >= time && elapsedTime - delta <= time){
            switch (typeEnemy) {

                case MONSTER_DRAGON:
                    Dragon dragon = new Dragon((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(dragon);
                    break;

                case MONSTER_BAT:
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

                case MONSTER_3:
                    Monster3 monster3 = new Monster3((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster3);
                    break;

                case MONSTER_4:
                    Monster4 monster4 = new Monster4((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster4);
                    break;

                case MONSTER_5:
                    Monster5 monster5 = new Monster5((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster5);
                    break;

                case MONSTER_6:
                    Monster6 monster6 = new Monster6((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster6);
                    break;

                case MONSTER_7:
                    Monster7 monster7 = new Monster7((int)((j + 0.5)*SIDE_BIT_MAP), (int)((i + 0.5)*SIDE_BIT_MAP));
                    enemyList.add(monster7);
                    break;

            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) { }

    @Override
   public void dispose(){

        if(removeEnemyList!=null)
        for (Enemy itrE : removeEnemyList){
            itrE.dispose();
        }

    }
}
