package com.javasquad.game.Sprites.Items;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by inehemias on 5/2/17.
 */

public class ItemDef {
    public Vector2 position;
    public Class<?> type;

    public ItemDef(Vector2 position, Class<?> type){
        this.position = position;
        this.type = type;
    }
}
