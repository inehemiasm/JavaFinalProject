package com.javasquad.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Tools.MyTextInputListener;

/**
 * Created by inehemias on 5/4/17.
 */

public class startScreen implements Screen{



    private Viewport viewport;
    private Stage stage;

    private Game game;
    private Skin login_text_skin= new Skin();

    public String username =null;



    public startScreen(Game game){

        this.game = game;
        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MarioBros) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label.LabelStyle font2 = new Label.LabelStyle(new BitmapFont(), Color.RED);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label startLabel = new Label("Super Mario Bros", font2);
        Label playLabel = new Label("Please press U to Add Username", font);



        table.add(startLabel).expandX();
        table.row();
        table.add(playLabel).expandX().padTop(10f);

        stage.addActor(table);
    }
    @Override

    public void show() {

    }

    @Override
    public void render(float delta) {



        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {

                game.setScreen(new PlayScreen((MarioBros) game));
                dispose();
            }
        else if (Gdx.input.isKeyJustPressed(Input.Keys.U)){

            String name;

            MyTextInputListener listener = new MyTextInputListener();
            Gdx.input.getTextInput(listener, " ", "Username   ", " ");


            System.out.printf("user%s", MyTextInputListener.getUsername());


        }

            Gdx.gl.glClearColor(0,0,0,0);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            stage.draw();






    }

    @Override
    public void resize(int width, int height) {

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
        stage.dispose();

    }
}
