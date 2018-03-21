package com.javasquad.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Scenes.Hud;
import com.javasquad.game.Screens.PlayScreen;
import com.javasquad.game.Sprites.Items.ItemDef;
import com.javasquad.game.Sprites.Items.Mushroom;

/**
 * Created by inehemias on 4/9/17.
 */
//coin item class

public class Coin extends InteractiveTileObject{ //coin is interactive.
    private static TiledMapTileSet tileSet;
    private final int BLANK_COIN = 28;
    public Coin(PlayScreen screen, MapObject object ){
//creating the coin in the playscreen.
        super(screen, object);
        tileSet = map.getTileSets().getTileSet("tileset_gutter");
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.COIN_BIT);
    }

    @Override
    public void onHeadHit(Mario mario) { //the case for when a coin.bit touches a mario bit. This function is called in the contact listenerclass.

        if(getCell().getTile().getId() == BLANK_COIN) //if a coin is hit.
            MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();
        else {
            if(object.getProperties().containsKey("mushroom")) { //else if the brick hit contains a mushroom, spawn mushroom.
                screen.spawnItem(new ItemDef(new Vector2(body.getPosition().x, body.getPosition().y + 16 / MarioBros.PPM),
                        Mushroom.class));
                MarioBros.manager.get("audio/sounds/powerup_spawn.wav", Sound.class).play();
            }
            else
                MarioBros.manager.get("audio/sounds/coin.wav", Sound.class).play(); //p[lays sounds.
            getCell().setTile(tileSet.getTile(BLANK_COIN));
            Hud.addScore(100); //update score
        }
    }

}