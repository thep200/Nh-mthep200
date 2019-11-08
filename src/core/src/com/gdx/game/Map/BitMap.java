package com.gdx.game.Map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.gdx.game.Map.Tower.Tower;

public class BitMap{
    public static final int sideBitMap=100;
    private int value;
    private int rowIndex;
    private int columnIndex;
    private Sprite sprite;
    private Texture img0=new Texture("Gollum-100.jpg");
    private Texture img1=new Texture("Gollum-101.jpg");
    private Texture img2=new Texture("img2.png");
    public BitMap(int value, int rowIndex, int columnIndex) {
        this.value = value;
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
    }

    public int getValue() {
        return value;
    }

    public void setSprite() {
        switch (value) {
            case 0:sprite=new Sprite(img0);
                break;
            case 1:sprite=new Sprite(img1);
                break;
            case 2:sprite=new Sprite(img2);
                break;

        }
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.setPosition(columnIndex*sideBitMap,rowIndex*sideBitMap);
        sprite.draw(spriteBatch);

    }
}