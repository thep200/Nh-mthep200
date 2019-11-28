package com.gdx.game.component.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.gdx.game.component.map.tower.Tower;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Map implements Disposable {

    public static final int SIDE_BIT_MAP = 100;
    private static int[][] mapStage1 = new int[10][15] ;

    private Texture imageRoad = new Texture("BitMap/road.png");                 // 1  2  3  4
    private Texture imageLand = new Texture("BitMap/landNormal.png");           // 0
    private Texture imageSpecialLand = new Texture("BitMap/landSpecial.png");   // 6

    private Texture imageRoadRL = new Texture("Bitmap/road_R_L.png");
    private Texture imageRoadUD = new Texture("Bitmap/road_U_D.png");
    private Texture imageRoadDAndR = new Texture("Bitmap/road_DR.png");
    private Texture imageRoadRAndD = new Texture("Bitmap/road_RD.png");
    private Texture imageRoadRAndU = new Texture("Bitmap/road_RU.png");
    private Texture imageRoadUAndR = new Texture("Bitmap/road_UR.png");

    public static int[][] loadTileMap(){
        String line ;
        try {
            BufferedReader reader = new BufferedReader(new FileReader("bitmap/tileMap.txt"));
            Scanner Sc = new Scanner(reader);
            int j = 0 ;
            while (Sc.hasNextLine()){
                line = Sc.nextLine() ;
                String[] Str = line.split("  ") ;
                for (int i = 0; i < 15; i++){
                    mapStage1[j][i] = Integer.parseInt(Str[i]) ;
                }
                j++ ;
            }
            reader.close();
        }catch (IOException e){e.getMessage();}
        return mapStage1 ;
    }

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

                if (map[i][j] == 0 || map[i][j] == 5 ){
                    spriteBatch.draw(imageLand,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP); //  land
                }
                if (map[i][j] > 0 && map[i][j] <= 4){
                    spriteBatch.draw(imageRoad,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP);   // road
                }
                if (map[i][j] == 6){
                    spriteBatch.draw(imageSpecialLand,j* SIDE_BIT_MAP,(map.length - 1 - i)* SIDE_BIT_MAP, SIDE_BIT_MAP, SIDE_BIT_MAP); // đất
                }
            }
        }
    }

    @Override
    public void dispose() {
        imageLand.dispose();
        imageRoad.dispose();
        imageSpecialLand.dispose();
        imageRoad.dispose();
        imageRoadDAndR.dispose();
        imageRoadRAndD.dispose();
        imageRoadRAndU.dispose();
        imageRoadRL.dispose();
        imageRoadUAndR.dispose();
        imageRoadUD.dispose();
    }
}
