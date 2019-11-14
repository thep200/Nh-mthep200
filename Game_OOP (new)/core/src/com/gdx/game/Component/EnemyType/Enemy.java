package com.gdx.game.Component.EnemyType;

import com.gdx.game.MovingEntity;


public abstract class Enemy extends MovingEntity {
    protected int hitPoint;
    protected boolean targetAble=true;

    public boolean isTargetAble() {
        return targetAble;
    }

    public void setTargetAble(boolean targetAble) {
        this.targetAble = targetAble;
    }

    public Enemy(final String textureAtlasNameFile1, final int numberFrameOfMotion, int x1, int y1, String textureAtlasNameFileParent){
        super(textureAtlasNameFile1,numberFrameOfMotion,x1,y1,textureAtlasNameFileParent );
        heightSprite = 80;
        widthSprite = 80;
    }

}
