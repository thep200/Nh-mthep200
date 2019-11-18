package com.gdx.game.component.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.component.map.tower.Tower;

import java.util.List;

public class Map implements Disposable {

    public static final int SIDE_BIT_MAP = 100;

    private Texture imageRoad = new Texture("BitMap/road.png");
    private Texture imageLand = new Texture("BitMap/landNormal.png");
    private Texture imgSpecialLand = new Texture("BitMap/landSpecial.png");


    public Map(int[][] map,List<Tower> towerList) {
        this.setTowerList(map,towerList);
    }

    public void setTowerList(int[][] bitMapList1,List<Tower> towerList) {
        for(int i = 0; i < bitMapList1.length; i++){
            for(int j = 0; j < bitMapList1[i].length; j++){
                if(bitMapList1[i][j] == 5)
                    towerList.add(new Tower(bitMapList1.length - 1 - i , j));
            }
        }
    }

    public void draw(SpriteBatch spriteBatch, int[][] map) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 0: spriteBatch.draw(imageLand,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP); // đất
                        break;
                    case 1:spriteBatch.draw(imageRoad,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);  // Đường rẽ phải
                        break;
                    case 2:spriteBatch.draw(imageRoad,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);  // Đường Xuống
                        break;
                    case 3:spriteBatch.draw(imageRoad,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);  // Đường rẽ trái
                        break;
                    case 4:spriteBatch.draw(imageRoad,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);  // Đường Lên
                        break;
                    case 5:spriteBatch.draw(imageLand,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);  // Xấy Tháp
                        break;
                    case 6:spriteBatch.draw(imgSpecialLand,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP); // Rừng cây
                        break;
                }
            }
        }

    }


    @Override
    public void dispose() {
        imageLand.dispose();
        imageRoad.dispose();
        imgSpecialLand.dispose();
    }
}
