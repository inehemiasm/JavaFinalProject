package com.javasquad.game.Tools;

import com.badlogic.gdx.Input;

/**
 * Created by inehemias on 5/5/17.
 */

public class MyTextInputListener implements Input.TextInputListener {
    public static String username;
    @Override
    public void input (String text) {

        username = text;


    }

    @Override
    public void canceled () {

    }
    public static String getUsername() {
        return username;
    }
}