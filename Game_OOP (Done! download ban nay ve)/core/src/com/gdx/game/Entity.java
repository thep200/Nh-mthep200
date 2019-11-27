package com.gdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Disposable;

import java.awt.*;

public abstract class Entity implements Disposable{

    protected double x;
    protected double y;

    protected static final int GAME_TIME_END = 10000 ;

    protected static int money = 105 ;
    protected static int houseHP = 1000 ;
    protected static int WidthBlood = 0 ;

    protected String score = "" ;
    protected BitmapFont myBitMap = new BitmapFont(Gdx.files.internal("myfont/font.fnt"));

    protected final int PRICE_TOWER_1 = 80 ;
    protected final int PRICE_UPGRADE_TOWER_1_TURN_1 = 150 ;
    protected final int PRICE_UPGRADE_TOWER_1_TURN_2 = 390 ;

    protected final int PRICE_TOWER_2 = 100 ;
    protected final int PRICE_UPGRADE_TOWER_2_TURN_1 = 180 ;
    protected final int PRICE_UPGRADE_TOWER_2_TURN_2 = 410 ;

    protected final int PRICE_TOWER_3 = 350 ;
    protected final int PRICE_UPGRADE_TOWER_3_TURN_1 = 450 ;
    protected final int PRICE_UPGRADE_TOWER_3_TURN_2 = 700 ;

    protected TextureAtlas textureAtlas;
    protected int currentFrameOfMotion;

    protected Texture texture;
    protected Sprite sprite;

    protected int widthSprite;
    protected int heightSprite;

    protected double speed;
    protected float adjustRotation;
    protected double rotation;

    public abstract void draw(SpriteBatch spriteBatch);

    protected boolean isTouched = false;

    public void buyTower(int price){
        this.setMoney(this.getMoney() - price);
    }

    public void bonusMoney(int bonus){
        this.setMoney(this.getMoney() + bonus);
    }

    public void subBlood(int value){
        this.setHouseHP(this.getHouseHP() - value);
        if (this.getHouseHP() < -1){
            Gdx.app.exit();
        }
    }

    public void subWidthBlood(int value){
        this.setWidthBlood(this.getWidthBlood() + value);
    }

    public int getWidthBlood() { return WidthBlood; }
    public void setWidthBlood(int widthBlood) { WidthBlood = widthBlood; }
    public int getHouseHP() { return houseHP; }
    public void setHouseHP(int houseHP) { this.houseHP = houseHP; }
    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money;}
    public boolean isTouched(){ return isTouched; }
    public void setIsTouched(){ isTouched = !isTouched; }
    public double getX() { return x; }
    public int getHeightSprite() { return heightSprite; }
    public int getWidthSprite() { return widthSprite; }
    public double getSpeed() { return speed; }
    public void setX(double x) { this.x = x; }
    public double getY() { return y; }
    public void setY(double y) { this.y = y; }

    public boolean checkIfTouched(int x, int y, int width, int height) {
        if (Gdx.input.getX() > x - width/2 && Gdx.input.getX() < x + width/2 && Gdx.input.getY() > 1000 - (y + height/2) && Gdx.input.getY() < 1000 - (y - height/2) )
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                return true;
        return false;
    }

    public boolean checkCollision(Point point,double adjust){
        if(x + widthSprite/2/adjust > point.getX() && y + heightSprite/2/adjust > point.getY()
        && x - widthSprite/2/adjust < point.getX() && y - heightSprite/2/adjust < point.getY()) //phai tren vs trai duoi
        {
            return true;
        }
        return false;
    }

    public boolean checkCollision(Entity entity,double adjust){
        return (
                  this.checkCollision(new Point((int)(entity.getX()-entity.getWidthSprite()/2),(int)(entity.getY()-entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()+entity.getWidthSprite()/2),(int)(entity.getY()+entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()-entity.getWidthSprite()/2),(int)(entity.getY()+entity.getHeightSprite()/2)),adjust)
                ||this.checkCollision(new Point((int)(entity.getX()+entity.getWidthSprite()/2),(int)(entity.getY()-entity.getHeightSprite()/2)),adjust)

                ||entity.checkCollision(new Point((int)(this.getX()+this.getWidthSprite()/2),(int)(this.getY()+this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()+this.getWidthSprite()/2),(int)(this.getY()-this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()-this.getWidthSprite()/2),(int)(this.getY()+this.getHeightSprite()/2)),adjust)
                ||entity.checkCollision(new Point((int)(this.getX()-this.getWidthSprite()/2),(int)(this.getY()-this.getHeightSprite()/2)),adjust)
        );
    }

    @Override
    public void dispose(){
        texture.dispose();
        textureAtlas.dispose();
        sprite.getTexture().dispose();
        myBitMap.dispose();
    }
}
