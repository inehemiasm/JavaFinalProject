package com.javasquad.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.javasquad.game.JavaSquadJdbc;
import com.javasquad.game.MarioBros;
import com.javasquad.game.Tools.SQLclass;

public class DesktopLauncher {
	public static void main (String[] arg) {




		System.out.println("Hello world");




		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MarioBros(), config);




	}
}
