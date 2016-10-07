package com.fiveleaf.raindrops.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.fiveleaf.raindrops.RaindropsGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Raindrops Keep Fallin' on My Cacti!";
        config.width = 160;
		config.height = 144;
        new LwjglApplication(new RaindropsGame(), config);
	}
}
