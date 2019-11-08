package com.gdx.game.Map;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Map.Tower.Tower;

import java.util.*;

public class Map {

    private final int valueStart=8;
    private final int valueEnd=9;
    private BitMap Start=new BitMap(valueStart,3,0);
    private BitMap End;
    private int row;
    private int column;
    private List<Tower> towerList=new ArrayList<>();;
    private List<List<BitMap>> bitMapList=new ArrayList<>();

    public Map(int[][] map) {
        this.setBitMapList(map);
        this.setSpriteMap();
        this.setTowerList(map);
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(int[][] bitMapList1) {
        for(int i=0;i<bitMapList1.length;i++){
            for(int j=0;j<bitMapList1[i].length;j++){
                if(bitMapList1[i][j]==2)
                this.towerList.add(new Tower(i,j));
            }
        }
    }

    public void setBitMapList(int[][] bitMapList1) {
        for(int i=0;i<bitMapList1.length;i++){
            for(int j=0;j<bitMapList1[i].length;j++){
                if(j==0 ){
                    List<BitMap> list1=new ArrayList<>();
                    list1.add(new BitMap(bitMapList1[i][0],i,0));
                    this.bitMapList.add(list1);
                }
                else
                this.bitMapList.get(i).add(new BitMap(bitMapList1[i][j],i,j));
            }
        }
        setRow(bitMapList1.length);
        setColumn(bitMapList1[0].length);

    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setColumn(int column) {
        this.column = column;
    }
    public void setSpriteMap(){
        for(int i=0;i<bitMapList.size();i++){
            for(int j = 0; j < bitMapList.get(i).size(); j++){
                bitMapList.get(i).get(j).setSprite();
            }

        }
    }
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < bitMapList.size(); i++) {
            for (int j = 0; j < bitMapList.get(i).size(); j++) {
                bitMapList.get(i).get(j).draw(spriteBatch);
            }
        }
        for (int i = 0; i < towerList.size();i++){
            towerList.get(i).draw(spriteBatch);
            towerList.get(i).isTouched(spriteBatch);
        }
    }
}
