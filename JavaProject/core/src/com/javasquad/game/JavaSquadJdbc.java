package com.javasquad.game;
import com.javasquad.game.Scenes.Hud;

import java.security.SecureRandom;
import java.sql.*;
import java.util.Random;


public class JavaSquadJdbc {

    Random ranGen = new SecureRandom();
     byte[] aesKey = new byte[16]; // 16 bytes = 128 bits

    private String name1= Hud.getName();
    private int hscore= Hud.getScore();

    public void readDB() throws SQLException {
        // Establish a connection
        Connection connection;
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/java_squad", "final", "me");
        ResultSet rs;
        try (Statement st = connection.createStatement()) {


            rs = st.executeQuery("SELECT *  FROM Player");
        }

        while(rs.next())
        {

            System.out.println(rs.getString(1)+ "  " + rs.getString(2) + "  " + rs.getString(3));

        }

        rs.close();




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

        String pname= "Johny";

        int score = hscore;
        int pid= 7437%7+9;

        String queryAddPlayer =
                "INSERT INTO Player (player_ID, player_name, player_score) VALUES (?,?,?);" ;

        try (PreparedStatement ps = connection.prepareStatement(queryAddPlayer)) {
            ps.setInt(1,pid);
            ps.setString(2, pname);
            ps.setInt(3, score);


            System.out.println(ps.toString());

            ps.executeUpdate();
            ps.close();

        }



    }

    public void loadDb() throws ClassNotFoundException, SQLException {
        //Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        // Establish a connection
        Connection connection;
        connection = DriverManager.getConnection
                ("jdbc:mysql://localhost/java_squad", "final", "me");

        System.out.println("Database connected");



    }



    public static void main(String[] args) throws ClassNotFoundException, SQLException {



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









}}




