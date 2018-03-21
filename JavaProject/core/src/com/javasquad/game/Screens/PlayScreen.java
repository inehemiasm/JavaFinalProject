
package com.javasquad.game.Screens;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasquad.game.MarioBros;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.javasquad.game.Scenes.Hud;
import com.javasquad.game.Sprites.Enemies.Enemy;
import com.javasquad.game.Sprites.Items.Item;
import com.javasquad.game.Sprites.Items.ItemDef;
import com.javasquad.game.Sprites.Items.Mushroom;
import com.javasquad.game.Sprites.Mario;
import com.javasquad.game.Tools.B2WorldCreator;
import com.javasquad.game.Tools.MyTextInputListener;
import com.javasquad.game.Tools.WorldContactListener;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Created by inehemias on 3/26/17.
 */

public class PlayScreen implements Screen{

    //Reference to our Game, used to set Screens
    private MarioBros game;
    private TextureAtlas atlas;
    long startTime;
    public static int highscore;




    //basic playscreen variables
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private Hud hud;

    //Tiled map variables
    private TmxMapLoader maploader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //Box2d variables
    private World world;
    private Box2DDebugRenderer b2dr;
    private B2WorldCreator creator;
    //Sprites
    private Mario player;

    private Music music;


    private Array<Item> items;
    private LinkedBlockingQueue<ItemDef> itemsToSpawn;
    String name = MyTextInputListener.getUsername();

    //get saved data







    public PlayScreen(MarioBros game){
        atlas = new TextureAtlas("Mario_and_Enemies.pack");

        this.game = game;
        //create cam can be used to follow mario
        gamecam= new OrthographicCamera();
        //create a FitViewport to maintain virtual aspect ratio despite screen size
        gamePort = new FitViewport(MarioBros.V_WIDTH/MarioBros.PPM, MarioBros.V_HEIGHT/MarioBros.PPM, gamecam);



        //create our game HUD for scores/timers/level info
        hud = new Hud(game.batch);


        //Load our map and setup our map renderer
        maploader = new TmxMapLoader();
        map = maploader.load("level1.tmx");
        renderer = new OrthogonalTiledMapRenderer(map, 1  / MarioBros.PPM);


        //initially set our gamcam to be centered correctly at the start of of map
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

        //create our Box2D world, setting no gravity in X, -10 gravity in Y, and allow bodies to sleep
        world = new World(new Vector2(0, -10), true);
        //allows for debug lines of our box2d world.
        b2dr = new Box2DDebugRenderer();;

        creator = new B2WorldCreator(this);
        // create in our mario world
        player = new Mario(this);

        world.setContactListener(new WorldContactListener());

        music = MarioBros.manager.get("audio/music/mario_music.ogg", Music.class);
        music.setLooping(true);
        //music.setVolume(0.3f);
        music.play();


        items = new Array<Item>();
        itemsToSpawn = new LinkedBlockingQueue<ItemDef>();
}


    public void spawnItem(ItemDef idef){
            itemsToSpawn.add(idef);
        }

    public void handleSpawningItems(){
        if(!itemsToSpawn.isEmpty()){
            ItemDef idef = itemsToSpawn.poll();
            if(idef.type == Mushroom.class){
                items.add(new Mushroom(this, idef.position.x, idef.position.y));
            }
        }
    }
    public TextureAtlas getAtlas(){
        return atlas;
    }
    @Override
    public void show() {

    }

    public void update(float dt){
        //Handle user input first
        handleInput(dt);

        handleSpawningItems();

        //takes 1 step in the physics simulation (60 times per second)
        world.step(1/60f, 6, 2);
        player.update(dt);
        for(Enemy enemy : creator.getEnemies()){
            enemy.update(dt);
            if(enemy.getX()<player.getX() + 224 / MarioBros.PPM)
                enemy.b2body.setActive(true);

        }

        for(Item item : items)
            item.update(dt);

        hud.update(dt);

        if(player.currentState != Mario.State.DEAD){
        gamecam.position.x = player.b2body.getPosition().x;
        }

        gamecam.update();
        //Tell our renderer to draw only what our camera can see in our game world.
        renderer.setView(gamecam);

    }
    //
    public void handleInput(float dt) {

        //control our player using impulses
        if (player.currentState != Mario.State.DEAD) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP))
                player.b2body.applyLinearImpulse(new Vector2(0, 4f), player.b2body.getWorldCenter(), true);

            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                if (player.b2body.getLinearVelocity().x <= 2) {
                    player.b2body.applyLinearImpulse(new Vector2(0.1f, 0), player.b2body.getWorldCenter(), true);
                }

            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.b2body.getLinearVelocity().x >= -2)
                player.b2body.applyLinearImpulse(new Vector2(-0.1f, 0), player.b2body.getWorldCenter(), true);
        }
    }


    public void create() {
        // Initialize all your other variables here
        startTime = TimeUtils.millis();
    }

    @Override
    public void render(float delta) {


        //separate our update logic from render
        update(delta);

        //Clear the game screen with Black
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render our game map
        renderer.render();

        //renderer our Box2DDebugLines
        b2dr.render(world, gamecam.combined);

        game.batch.setProjectionMatrix(gamecam.combined);
        game.batch.begin();
        player.draw(game.batch);
        for (Enemy enemy : creator.getEnemies())
            enemy.draw(game.batch);
        for (Item item : items)
            item.draw(game.batch);
        game.batch.end();

        //Set our batch to now draw what the Hud camera sees.
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

        if(gameOver()){

            highscore = Hud.getScore();



            System.out.printf("user%s", MyTextInputListener.getUsername());

            System.out.println(highscore);
            GameOverScreen.setScore();
            game.setScreen(new GameOverScreen(game));
            dispose();
        }




    }
    public static int getscore(){

        return highscore;
    }


    public boolean gameOver(){
        if(player.currentState == Mario.State.DEAD && player.getStateTimer() > 3){
            return true;
        }
        return false;
    }

    public boolean startGame(){

        if(player.currentState != Mario.State.DEAD ){
            return true;
        }
        return false;
    }

    @Override
    public void resize(int width, int height) {

        gamePort.update(width, height);

    }

    public TiledMap getMap(){
        return map;
    }
    public World getWorld(){
        return world;
    }
     @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        b2dr.dispose();
        hud.dispose();

    }
}
