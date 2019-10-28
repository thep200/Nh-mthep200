package com.mygdx.MyScreen.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.MyScreen.CreateScreenGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.foregroundFPS = 60 ;
		config.width = 1420 ;
		config.height = 990 ;
		new LwjglApplication(new CreateScreenGame(), config);
	}
}
