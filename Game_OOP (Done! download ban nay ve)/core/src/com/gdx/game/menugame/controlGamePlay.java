package com.gdx.game.menugame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public class controlGamePlay implements Disposable {

    public boolean status = true ;
    public boolean statusGamePlay = false ;

    public static int gameStage = 1 ;
    public String stage = "" ;
    public BitmapFont myStage = new BitmapFont(Gdx.files.internal("myFont/font1.fnt"));

    private Texture control = new Texture("menu/ScreenControl.png");

    private Texture imageExitPlayActive = new Texture("menu/ExitPlay-Active.png");
    private Texture imageExitPlayWait = new Texture("menu/ExitPlay-Wait.png");

    private Texture imagePauseActive = new Texture("menu/Pause-Active.png");
    private Texture imagePauseWait = new Texture("menu/Pause-Wait.png");

    private Texture imageStartActive = new Texture("menu/Start-Active.png");
    private Texture imageStartWait = new Texture("menu/Start-Wait.png");

    private Texture imageMusicWait = new Texture("menu/Music-Wait.png");
    private Texture imageMusicActive = new Texture("menu/Music-Active.png");

    private Texture imageStageActive = new Texture("menu/Stage-Active.png") ;
    private Texture imageStageWait = new Texture("menu/Stage-Wait.png") ;

    private Texture imageBloodHouse = new Texture("attribute/House-Blood.png");
    private Texture imageCoinBonus = new Texture("attribute/CoinBonus.png");
    private Texture imageHeart = new Texture("attribute/Heart.png");

    public controlGamePlay(){} ;

    public void draw(SpriteBatch spriteBatch){
        stage += this.gameStage ;

        spriteBatch.draw(control,1500,0,300,1000) ;

        spriteBatch.draw(imageBloodHouse, 1580, 850, 200, 20) ;

        spriteBatch.draw(imageHeart,1520, 830, 50, 50);

        spriteBatch.draw(imageCoinBonus, 1500, 890, 100, 100) ;

        if (Gdx.input.getX() < 1550 || Gdx.input.getX() > 1750 || Gdx.input.getY() < 850 || Gdx.input.getY() > 950){   // exit
            spriteBatch.draw(imageExitPlayWait, 1550, 0, 200,200);
        }else{
            spriteBatch.draw(imageExitPlayActive, 1550, 0, 200,200);
            if (Gdx.input.isTouched()){ Gdx.app.exit(); }

        }

        if (Gdx.input.getX() < 1550 || Gdx.input.getX() > 1750 || Gdx.input.getY() < 700 || Gdx.input.getY() > 800){   // continue
            spriteBatch.draw(imagePauseWait,1550, 150, 200, 200);
                if (Gdx.input.isTouched()){ status = true ; }
        }else{
            spriteBatch.draw(imagePauseActive,1550, 150, 200, 200);   // pause
                if (Gdx.input.isTouched()){ status = false ; }
        }

        if (Gdx.input.getX() < 1550 || Gdx.input.getX() > 1750 || Gdx.input.getY() < 550 || Gdx.input.getY() > 650){   // start
            spriteBatch.draw(imageStartWait,1550, 300, 200, 200);
        }else{
            spriteBatch.draw(imageStartActive,1550, 300, 200, 200);
            if (Gdx.input.isTouched()){ statusGamePlay = true ; }
        }

        if (Gdx.input.getX() < 1550 || Gdx.input.getX() > 1750 || Gdx.input.getY() < 400 || Gdx.input.getY() > 500){  // sound
            spriteBatch.draw(imageMusicWait,1550, 450, 200, 200);
        }else{
            spriteBatch.draw(imageMusicActive,1550, 450, 200, 200);
        }

        if (Gdx.input.getX() < 1550 || Gdx.input.getX() > 1750 || Gdx.input.getY() < 250 || Gdx.input.getY() > 350){  // Stage
            spriteBatch.draw(imageStageWait,1550, 600, 200, 200);
        }else{
            spriteBatch.draw(imageStageActive,1550, 600, 200, 200);
            if (Gdx.input.justTouched() && this.gameStage + 1 <= 3 ){ this.gameStage++ ; }
        }

        myStage.draw(spriteBatch, stage, 1615, 730);
        stage = " " ;

    }

    @Override
    public void dispose() {
        imagePauseActive.dispose();
        imagePauseWait.dispose();

        imageMusicActive.dispose();
        imageMusicWait.dispose();

        imageBloodHouse.dispose();
        imageHeart.dispose();
        imageCoinBonus.dispose();

        imageExitPlayActive.dispose();
        imageExitPlayWait.dispose();

        imageStartActive.dispose();
        imageExitPlayWait.dispose();

        myStage.dispose();
    }
}
