package com.gdx.game.component.enemytype;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.component.attribute.HitPoint;
import com.gdx.game.MovingEntity;


public abstract class Enemy extends MovingEntity {
    protected HitPoint hitPoint;
    protected boolean targetAble = true;
    protected boolean justGotHit = false;
    protected float time_justGotHit = 2;
    protected float timeRemain_justGotHit = time_justGotHit;

    public boolean isJustGotHit() {
        return justGotHit;
    }
    public void setJustGotHit(boolean justGotHit) {
        time_justGotHit = timeRemain_justGotHit;
        this.justGotHit = justGotHit;
    }

    public boolean isTargetAble() {
        return targetAble;
    }
    public void setTargetAble(boolean targetAble) {
        this.targetAble = targetAble;
    }
    public HitPoint getHitPoint() {
        return hitPoint;
    }
    public void setHitPoint(HitPoint hitPoint) {
        this.hitPoint = hitPoint;
    }

    public Enemy(final String textureAtlasNameFile1, final int numberFrameOfMotion, int x1, int y1, String textureAtlasNameFileParent){
        super(textureAtlasNameFile1, numberFrameOfMotion, x1, y1, textureAtlasNameFileParent );

        heightSprite = 80;   // kích thước qoái
        widthSprite = 80;
    }

    public void draw(SpriteBatch spriteBatch ,float delta) {
        super.draw(spriteBatch);
        if(isJustGotHit() && isTargetAble()) {
            hitPoint.setPosition((int) x, (int)y + heightSprite/2);
            hitPoint.draw(spriteBatch,(float)hitPoint.getValue()/hitPoint.getMaxValue());
            time_justGotHit -= delta;
            if(time_justGotHit <= 0){ setJustGotHit(false); };
        }
    }

    @Override
    public void dispose(){super.dispose();}
}
