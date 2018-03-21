package com.javasquad.game.Sprites;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Screens.PlayScreen;

/**
 * Created by inehemias on 4/9/17.
 */

public abstract class InteractiveTileObject { // the basis for all of interactive objects


    protected World world;
    protected TiledMap map;
    protected Rectangle bounds;
    protected Body body;
    protected PlayScreen screen;

    protected Fixture fixture;
    protected MapObject object;




    public InteractiveTileObject(PlayScreen screen, MapObject object){
        this.object = object;
        this.screen = screen;
        this.world = screen.getWorld();
        this.map = screen.getMap();
        this.bounds = ((RectangleMapObject) object).getRectangle();

// defining objects.

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX()+ bounds.getWidth()/2)/ MarioBros.PPM, (bounds.getY()+ bounds.getHeight()/2)/MarioBros.PPM);
        body = world.createBody(bdef);

        shape.setAsBox((bounds.getWidth()/2/MarioBros.PPM), (bounds.getHeight()/2)/MarioBros.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);



    }
    public abstract void onHeadHit(Mario mario);

    public void setCategoryFilter(short filterBit){
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);


    }

    public TiledMapTileLayer.Cell getCell(){ //returns the block the object is in. "in referenece to the map creator.

        TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(1);
        return layer.getCell((int)(body.getPosition().x* MarioBros.PPM/16),(int)(body.getPosition().y* MarioBros.PPM/16));



    }



}