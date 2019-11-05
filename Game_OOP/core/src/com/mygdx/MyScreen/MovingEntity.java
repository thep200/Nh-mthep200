package com.mygdx.MyScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Timer;

import java.util.ArrayList;
import java.util.List;

public class MovingEntity extends Entity {
    protected int currentFrameOfMotion;
    protected int numberFrameOfMotion;
    protected String currentAtlasKey ;
    protected String textureAtlasNameFile;

    protected int currentStep;
    protected double speed;
    protected double degree;
    protected Point0 direct;
    protected Trajectory trajectory;

    public MovingEntity(int numberFrameOfMotion1, String currentAtlasKey1, String textureAtlasNameFile1, float degree1, Trajectory trajectory1) {
        numberFrameOfMotion = numberFrameOfMotion1;
        currentAtlasKey = currentAtlasKey1;
        trajectory = trajectory1;
        currentStep = 0;
        direct = getDirect();
        currentFrameOfMotion = 1;
        x=trajectory.getListPoint0Des().get(0).getX();
        y=trajectory.getListPoint0Des().get(0).getY();
        // tao TextureAtlas dau tien
        textureAtlas = new TextureAtlas((Gdx.files.internal(textureAtlasNameFile1)));
        String currentAtlasKey0 = String.format("%s%d", currentAtlasKey, currentFrameOfMotion);
        TextureAtlas.AtlasRegion region = textureAtlas.findRegion(currentAtlasKey0);
        sprite = new Sprite(region);
        // dieu chinh anh cho dung goc ngang
        degree=degree1;
        sprite.setRotation((float) degree);
        // dieu chinh anh theo goc ban dau di chuyen
        double canhhuyen=Math.sqrt(direct.getX()*direct.getX()+direct.getY()*direct.getY());
        sprite.rotate((float) (Math.acos(direct.getX()/canhhuyen)*180/Math.PI*Math.signum(direct.getY())));
        //hoat hinh
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                currentFrameOfMotion = (currentFrameOfMotion + 1) % numberFrameOfMotion;
                if(currentFrameOfMotion==0)currentFrameOfMotion=1;
                String currentAtlasKey0 = String.format("%s%d", currentAtlasKey, currentFrameOfMotion);
                sprite.setRegion(textureAtlas.findRegion(currentAtlasKey0));

            }
        }, 0, 1/7.f);
    }
    public int getNumberFrameOfMotion() {
        return numberFrameOfMotion;
    }
    public void setNumberFrameOfMotion(int numberFrameOfMotion) {
        this.numberFrameOfMotion = numberFrameOfMotion;
    }

    public double getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public Trajectory getTrajectory() {
        return trajectory;
    }
    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }
    public int getCurrentFrameOfMotion() {
        return currentFrameOfMotion;
    }
    public void setCurrentFrameOfMotion(int currentFrameOfMotion) {
        this.currentFrameOfMotion = currentFrameOfMotion;
    }
    public String getCurrentAtlasKey() {
        return currentAtlasKey;
    }
    public void setCurrentAtlasKey(String currentAtlasKey) {
        this.currentAtlasKey = currentAtlasKey;
    }
    public String getTextureAtlasNameFile() {
        return textureAtlasNameFile;
    }
    public void setTextureAtlasNameFile(String textureAtlasNameFile) {
        this.textureAtlasNameFile = textureAtlasNameFile;
    }
    public TextureAtlas getTextureAtlas() {
        return textureAtlas;
    }
    public void setTextureAtlas(TextureAtlas textureAtlas) {
        this.textureAtlas = textureAtlas;
    }
    public int getCurrentStep() {
        return currentStep;
    }
    public void setCurrentStep(int currentStep) {
        this.currentStep = currentStep;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public Point0 getDirect() {
        if(currentStep==trajectory.getListPoint0Des().size()-1)return new Point0(0,0);
        return new Point0(trajectory.getListPoint0Des().get(currentStep+1).getX()-trajectory.getListPoint0Des().get(currentStep).getX(),
                trajectory.getListPoint0Des().get(currentStep+1).getY()-trajectory.getListPoint0Des().get(currentStep).getY());
    }
    public void setDirect(Point0 direct) {
        this.direct = direct;
    }
    @Override
    public void draw(SpriteBatch batch) {
        sprite.setPosition((int)x,(int)y);
        sprite.draw(batch);
    }
    public void moveFollowTrajectory(){
        if (currentStep<trajectory.getListPoint0Des().size()-1)
            move(trajectory.getListPoint0Des().get(currentStep),trajectory.getListPoint0Des().get(currentStep+1));
    }
    public boolean move(Point0 a, Point0 b){
        //vector noi a vs b.
        double dX=b.getX()-a.getX();
        double dY=b.getY()-a.getY();
        //van toc theo truc x,y
        double sqrt = Math.sqrt(dX * dX + dY * dY);
        double speedX=dX / sqrt * speed;
        double speedY=dY / sqrt * speed;
        //neu khoang cach giua diem hien tai va diem dich qua gan thi dich chuyen diem hien tai den dich luon
        //nguoc lai cho chay binh thg
        if(Math.abs(b.getX()-x)>=Math.abs(speedX) && Math.abs(b.getY()-y)>=Math.abs(speedY)) {
            setX(x +speedX);
            setY(y+ speedY );
            return false;
        }
        else {
            setX(b.getX());
            setY(b.getY());
            currentStep++;
            //neu vat o cuoi qua trinh chuyen dong thi dung chuyen dong
            if (currentStep == trajectory.getListPoint0Des().size() - 1)
                return false;
            //neu khong thi cho chuyen huong
            direct = getDirect();
            double canhhuyen = Math.sqrt(direct.getX() * direct.getX() + direct.getY() * direct.getY());
            sprite.setRotation((float) degree);
            if(direct.getY()>=0)
            sprite.rotate((float) (Math.acos(direct.getX() / canhhuyen) * 180 / Math.PI ));
            else sprite.rotate((float) (-Math.acos(direct.getX() / canhhuyen) * 180 / Math.PI ));

            return true;
        }
    }
}
