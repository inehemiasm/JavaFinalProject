package com.javasquad.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Screens.startScreen;
import com.javasquad.game.Tools.MyTextInputListener;
import com.sun.tools.javac.util.Name;

import static com.javasquad.game.Screens.startScreen.*;

/**
 * Created by inehemias on 3/26/17.
 */


public class Hud implements Disposable{

    //Scene2D.ui Stage and its own Viewport for HUD
    public Stage stage;
    private Viewport viewport;

    //Mario score/time Tracking Variables
    private Integer worldTimer;
    private boolean timeUp; // true when the world timer reaches 0
    private float timeCount;
    private static Integer score;
    private static String playerName ;


    //Scene2D widgets
    private Label countdownLabel;
    private static Label scoreLabel;
    private Label timeLabel;
    private Label levelLabel;
    private Label worldLabel;
    private Label marioLabel;
    private Label nameLabel;



    public Hud(SpriteBatch sb){
        //define our tracking variables
        worldTimer = 150;
        timeCount = 0;
        score = 0;
        playerName = MyTextInputListener.getUsername();


        //setup the HUD viewport using a new camera seperate from our gamecam
        //define our stage using that viewport and our games spritebatch
        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        //define a table used to organize our hud's labels
        Table table = new Table();
        //Top-Align table
        table.top();
        //make the table fill the entire stage
        table.setFillParent(true);

        //define our labels using the String, and a Label style consisting of a font and color
        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.RED));
        scoreLabel =new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
        timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        levelLabel = new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldLabel = new Label("WORLD", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        marioLabel = new Label("MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        nameLabel = new Label(String.format(playerName), new Label.LabelStyle(new BitmapFont(), Color.YELLOW));

        //add our labels to our table, padding the top, and giving them all equal width with expandX
        table.add(marioLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        //add a second row to our table
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();
        table.add(nameLabel).expandX();

        //add our table to the stage
        stage.addActor(table);

    }

    public void update(float dt){ //this updates the game with new timers in comparion to the in game world timer.
        timeCount += dt;
        if(timeCount >= 1){
            if (worldTimer > 0) {
                worldTimer--;
            } else {
                timeUp = true;
            }
            countdownLabel.setText(String.format("%03d", worldTimer));
            timeCount = 0;
        }
    }

    public static void addScore(int value){ //updates score
        score += value; //in here the value of bricks and items gained is added to score.
        scoreLabel.setText(String.format("%06d", score)); // the score label updates accorddingly.
    }
    public void setName(String name){ //sets name

        nameLabel.setText(String.format("%06d", playerName));

    }

    @Override
    public void dispose() { stage.dispose(); }

    public boolean isTimeUp() { return timeUp; } //check if timer is out.

    public static String getName(){ // getters for name and score.

        return playerName;
    }
    public static int getScore(){

        return score;
    }
}