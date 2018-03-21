package com.javasquad.game.Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Scenes.Hud;
import com.javasquad.game.Tools.MyTextInputListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by inehemias on 5/3/17.
 */

public class GameOverScreen implements Screen{


    private Viewport viewport;
    private Stage stage;

    private Game game;
    int score;
    String name;
    protected static int highScore = PlayScreen.getscore();
    int score1;
    private PreparedStatement preparedStatement;


    public GameOverScreen(Game game){
        int highscore = highScore;
        String sc= Integer.toString(highScore);
        name = Hud.getName();
        this.game = game;
        this.score=score;



        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((MarioBros) game).batch);

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);
        Label.LabelStyle over = new Label.LabelStyle(new BitmapFont(), Color.RED);
        Label.LabelStyle again = new Label.LabelStyle(new BitmapFont(), Color.GREEN);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", over);
        Label scoreLabel = new Label("Score", font);
        Label scLabel = new Label(sc , font);
        Label nameLabel = new Label("Name", font);
        Label nmLabel = new Label(name , font);
        Label playAgainLabel = new Label("Press Enter to Play Again", again);


        table.add(gameOverLabel).expandX();
        table.row();

        table.add(nameLabel).expandX();
        table.row();
        table.add(nmLabel).expandX();

        table.row();
        table.add(scoreLabel).expandX();
        table.row();
        table.add(scLabel).expandX();

        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);





        stage.addActor(table);
    }
    @Override

    public void show() {

    }



    @Override
    public void render(float delta) {

        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

            System.out.println(highScore);
            initializeDB();
            try {
                insertDb(name, highScore);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                readDB();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            game.setScreen(new PlayScreen( (MarioBros) game ));



            dispose();
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
    }
    public void insertDb(String name, int hscore) throws ClassNotFoundException, SQLException {
        //Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Establish a connection
        Connection connection;
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/java_squad", "final", "me");

        System.out.println("Database connected");
        Statement st = connection.createStatement();
        Statement scorest = connection.createStatement();

        String pname= name;

        int score = hscore;
        int pid = 19;

        String queryAddPlayer =
                "INSERT INTO Player ( player_name, player_score) VALUES (?,?);" ;

        try (PreparedStatement ps = connection.prepareStatement(queryAddPlayer)) {
           // ps.setInt(1,pid);
            ps.setString(1, pname);
            ps.setInt(2, score);


            System.out.println(ps.toString());
            ps.executeUpdate();
            ps.close();

        }
}
    private void initializeDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded");

            // Establish a connection
            Connection connection;
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost/java_squad", "final", "me");

            System.out.println("Database connected");

            preparedStatement = connection.prepareStatement("SELECT * FROM Player");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    public static void setScore() {
        highScore= Hud.getScore();

    }

    public void readDB() throws SQLException, ClassNotFoundException {

        //Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Establish a connection
        Connection connection;
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/java_squad", "final", "me");

        System.out.println("Database connected");
        Statement st = connection.createStatement();
        Statement scorest = connection.createStatement();

        ResultSet rs = st.executeQuery("SELECT *  FROM Player");

        while(rs.next())
        {

            System.out.println(rs.getString(1)+ "  " + rs.getString(2) + "  " + rs.getString(3));

        }




        rs.close();

        connection.close();



    }

}
