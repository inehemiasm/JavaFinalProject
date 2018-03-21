package com.javasquad.game.Tools;
import com.apple.laf.ScreenMenuBarProvider;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.javasquad.game.Scenes.Hud;
import com.sun.corba.se.impl.orb.PrefixParserAction;

import java.awt.TextField;
import java.awt.event.InputMethodEvent;
import java.sql.*;
/**
 * Created by inehemias on 5/8/17.
 */

public class SQLclass implements Screen {

    private PreparedStatement preparedStatement;
    private String name1= Hud.getName();



    //@Override
    public void start(Stage primaryStage)
    {
        initializeDB();

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
    private void showData(){

        try{
            preparedStatement.setString(1, name1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next())
            {

                String pname = resultSet.getString(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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

    }
}
