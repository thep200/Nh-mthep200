package com.gdx.game.Map.Tower;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Entity;

import java.util.ArrayList;
import java.util.List;

import static com.gdx.game.Map.BitMap.sideBitMap;

public class Tower extends Entity {
    protected int widthTower;
    protected int heightTower;
    protected int lvlOfTower;
    protected int type;
    protected List<TowerOption> towerOptionList=new ArrayList<>();
    public Tower(int row, int column){
        speed=0;
        type=0;
        sprite=new Sprite(new Texture("Tower1lvl0.png"));
        widthTower=75;
        heightTower=75;
        lvlOfTower=0;
        x=column*sideBitMap+0.5*(sideBitMap-widthTower);
        y=row*sideBitMap+0.5*(sideBitMap-heightTower);
        Texture texture1=new Texture("Option1lvl0.png");
        towerOptionList.add(new TowerOption(texture1,(int)x/sideBitMap*sideBitMap,(int)y+80));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option
        Texture texture2=new Texture("Option2lvl0.png");
        towerOptionList.add(new TowerOption(texture2,(int)x/sideBitMap*sideBitMap+sideBitMap,(int)y+80));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option
    }
    public void upgradeTower(int lvl1,int type1){
        texture=new Texture(String.format("Tower%dlvl%d.png",type1,lvl1));
        sprite= new Sprite(texture);
        lvlOfTower=lvl1;
        Texture texture1=new Texture("Option1lvl0.png");
        towerOptionList.add(new TowerOption(texture1,(int)x/sideBitMap*sideBitMap,(int)y+80));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option
        Texture texture2=new Texture("Option2lvl0.png");
        towerOptionList.add(new TowerOption(texture2,(int)x/sideBitMap*sideBitMap+sideBitMap,(int)y+80));//se dc dieu chinh them bot = 1 luong nua chieu rong cua option
        System.out.println("asfdsadf" );
    }
    @Override
    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition((int)x,(int)y);
        sprite.draw(spriteBatch);
    }

    public void isTouched(SpriteBatch spriteBatch) {
        if(isTouched((int)x,(int)y,widthTower,heightTower)){
            this.setTouchActive();
        }
        for (int i=0;i<towerOptionList.size();i++)
            if(this.isTouchActive()){                           //new thap bi an chuot trai
                towerOptionList.get(i).draw(spriteBatch);               //ve option
                towerOptionList.get(i).isTouched(spriteBatch);           //kiem tra cac option co bi ve hay khong. neu co thi kich hoat
                if(towerOptionList.get(i).isTouchActive()){                //new option dang kich hoat
                    upgradeTower(++lvlOfTower,i+1);                       //upgrade
                    towerOptionList.get(i).setTouchActive();            //tat kich hoat option
                    this.setTouchActive();                              //tat trang thai kich hoat cua tower
                }
        }

    }
}
