package com.mygdx.MyScreen.Enemy;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.MyScreen.MovingEntity;
import com.mygdx.MyScreen.Point0;
import com.mygdx.MyScreen.Trajectory;

public class AM extends Enemy {
    public AM(int numberFrameOfMotion1, int numberFrameOfDirect1, String currentAtlasKey1, String textureAtlasNameFile, Trajectory trajectory1) {
        super( numberFrameOfMotion1, currentAtlasKey1, textureAtlasNameFile, 60, trajectory1);
        speed=5;

    }
    public AM( Trajectory trajectory1) {
        super(8, "AM","AMsprite.atlas", 60,trajectory1);
        speed=3;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        moveFollowTrajectory();
    }
}
