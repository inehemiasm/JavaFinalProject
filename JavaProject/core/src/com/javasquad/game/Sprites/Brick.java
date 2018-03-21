package com.javasquad.game.Sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Scenes.Hud;
import com.javasquad.game.Screens.PlayScreen;

/**
 * Created by inehemias on 4/9/17.
 */

//brick item.
public class Brick extends InteractiveTileObject{

    public Brick(PlayScreen screen, MapObject object){

        super(screen, object);
        fixture.setUserData(this);
        setCategoryFilter(MarioBros.BRICK_BIT);

    }

    @Override
    public void onHeadHit(Mario mario) { //bricks are only destructible if mario is big.
        if(mario.isBig()){
            //Gdx.app.log("Brick", "Collision");
            setCategoryFilter(MarioBros.DESTROYED_BIT);
            getCell().setTile(null);
            Hud.addScore(200);
            MarioBros.manager.get("audio/sounds/breakblock.wav", Sound.class).play();
        }
        else
            MarioBros.manager.get("audio/sounds/bump.wav", Sound.class).play();


    }

}