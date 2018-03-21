package com.javasquad.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.javasquad.game.Screens.GameOverScreen;
import com.javasquad.game.Screens.PlayScreen;
import com.javasquad.game.Screens.startScreen;
import com.javasquad.game.Tools.SQLclass;


public class MarioBros extends Game {
	public static final int V_WIDTH= 400;
	public static final int V_HEIGHT= 208;
	public static final float PPM = 100;

	//we assign different bits for all the objectgs that will exist in the game. note that bit assignments are to be unique.
	public static final short NOTHING_BIT = 0;
	public static final short GROUND_BIT = 1;
	public static final short MARIO_BIT = 2;
	public static final short BRICK_BIT = 4;
	public static final short COIN_BIT = 8;
	public static final short DESTROYED_BIT = 16;
	public static final short OBJECT_BIT = 32;
	public static final short ENEMY_BIT = 64;
	public static final short ENEMY_HEAD_BIT = 128;
	public static final short ITEM_BIT = 256;
	public static final short MARIO_HEAD_BIT = 512;
	public static final short FIREBALL_BIT = 1024;

	public SpriteBatch batch;

	public static AssetManager manager;



	@Override
	public void create () { //in here we load all of our assets to the game. Such as music, sound effects, and sprites for the objects.

		batch= new SpriteBatch();
		manager = new AssetManager();
		manager.load("audio/music/mario_music.ogg", Music.class);
		manager.load("audio/sounds/coin.wav", Sound.class);
		manager.load("audio/sounds/bump.wav", Sound.class);
		manager.load("audio/sounds/breakblock.wav", Sound.class);
		manager.load("audio/sounds/powerup_spawn.wav", Sound.class);
		manager.load("audio/sounds/powerup.wav", Sound.class);
		manager.load("audio/sounds/powerdown.wav", Sound.class);
		manager.load("audio/sounds/stomp.wav", Sound.class);
		manager.load("audio/sounds/mariodie.wav", Sound.class);

		manager.finishLoading();
		SQLclass db;


		//setScreen(new GameOverScreen(this));
		//setScreen(new PlayScreen(this));
		setScreen(new startScreen(this));

	}

	@Override
	public void dispose() { //dispose closes the current screen being called.
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
	@Override
	public void render () { //render draws up a screen.

		super.render();
	}


}