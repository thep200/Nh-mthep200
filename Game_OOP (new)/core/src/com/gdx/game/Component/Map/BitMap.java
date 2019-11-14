package com.gdx.game.Component.Map;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BitMap{
        private int value;
        private int rowIndex;
        private int columnIndex;

        private Sprite sprite;

        public BitMap(int value, int rowIndex, int columnIndex) {
            this.value = value;
            this.rowIndex = rowIndex;
            this.columnIndex = columnIndex;
        }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(sprite,columnIndex* Map.SIDE_BIT_MAP,rowIndex* Map.SIDE_BIT_MAP,Map.SIDE_BIT_MAP,Map.SIDE_BIT_MAP);
    }
}