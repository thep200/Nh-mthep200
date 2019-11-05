package com.mygdx.MyScreen.Enemy;

import com.mygdx.MyScreen.MovingEntity;
import com.mygdx.MyScreen.Trajectory;

public class Enemy extends MovingEntity {

    public Enemy(int numberFrameOfMotion1, String currentAtlasKey1, String textureAtlasNameFile1, float degree1, Trajectory trajectory1) {
        super(numberFrameOfMotion1, currentAtlasKey1, textureAtlasNameFile1, degree1, trajectory1);
    }
}
