package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;
import java.util.List;

import static com.gdx.game.component.map.Map.SIDE_BIT_MAP;

public class MovingEntity extends Entity {
    protected int currentStep;

    public MovingEntity(final String textureAtlasNameFile1, final int numberFrameOfMotion, int x1, int y1, String textureAtlasNameFileParent) {
        currentFrameOfMotion = 1;
        currentStep = -1;
        x = x1;
        y = y1;

        textureAtlas = new TextureAtlas((Gdx.files.internal(String.format(textureAtlasNameFileParent + "/" + textureAtlasNameFile1 + "Sprite.atlas"))));
        String currentAtlasKey = String.format(textureAtlasNameFile1 + "%d" ,currentFrameOfMotion);
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(currentAtlasKey);
        sprite = new Sprite(region);

        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                currentFrameOfMotion = (currentFrameOfMotion + 1) % (numberFrameOfMotion+1);
                if (currentFrameOfMotion == 0) currentFrameOfMotion = 1;
                String currentAtlasKey = String.format( textureAtlasNameFile1 + "%d", currentFrameOfMotion);
            //    System.out.println(currentAtlasKey);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey));
            }
        }, 0, 1/5.0f);

    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite,(int)x - widthSprite/2,(int)y - heightSprite/2,widthSprite/2,heightSprite/2,widthSprite,heightSprite,1,1,(float)rotation);
    }
    public boolean moveTo(int x1, int y1){
        double dX = x1 - x;
        double dY = y1 - y;

        double sqrt = Math.sqrt(dX * dX + dY * dY);

        double speedX = dX / sqrt * speed;
        double speedY = dY / sqrt * speed;

        double v = Math.acos(dX / sqrt)*180/Math.PI ;

        if(dY < 0) v =- v;
        rotation = v + adjustRotation;

        if(Math.abs(x1 - x) >= Math.abs(speedX) && Math.abs(y1 - y) >= Math.abs(speedY)) {

            setX(x + speedX);
            setY(y + speedY );
            return false;

        } else {

            setX(x1);
            setY(y1);
            return true;
        }
    }
    public boolean moveFollowTrajectory(List<Point> pointList){
        if(currentStep < pointList.size() - 1){
            if(moveTo((int)((pointList.get(currentStep + 1).getX() + 0.5)* SIDE_BIT_MAP),(int)((pointList.get(currentStep + 1).getY() + 0.5)* SIDE_BIT_MAP))){
                currentStep++;
                return false;
            }
        }
        if(currentStep == pointList.size() - 1){
            this.subBlood(200);
            this.subWidthBlood(40);
            return true;

        }

        return false;
    }

    @Override
    public void dispose(){super.dispose();}
}
