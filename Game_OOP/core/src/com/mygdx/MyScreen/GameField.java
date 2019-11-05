package com.mygdx.MyScreen;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.MyScreen.Enemy.AM;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;

public class GameField {
    public List<Entity> entityList=new ArrayList<>();
    //cai nay chua lam
    public List<Touchable> touchableList=new ArrayList<>();
    //quy dao quai vat man` 1
    private Trajectory trajectoryStage1=new Trajectory();
    public List<Point0> getTrajectoryStage1() {
        List<Point0> listPointStage1 = new ArrayList<Point0>();
        listPointStage1.add(new Point0(500, 1000));
        listPointStage1.add(new Point0(300, 200));
        trajectoryStage1.setListPoint0Des(listPointStage1);
        return trajectoryStage1.getListPoint0Des();
    }
    //contructor khoi tao cac quai vat , thap trong game
    public GameField(){
        trajectoryStage1.setListPoint0Des(getTrajectoryStage1());
        entityList.add(new AM(trajectoryStage1));
    }
    //ve
    public void draw(SpriteBatch batch){
        for (Entity itr : entityList) {
            itr.draw(batch);
        }
    }
}
