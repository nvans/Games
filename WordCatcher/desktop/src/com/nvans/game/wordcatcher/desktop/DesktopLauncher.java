package com.nvans.game.wordcatcher.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.nvans.game.wordcatcher.WordCatcher;

/**
 *
 * Desktop game launcher
 *
 */

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.title = "WordCatcher";
        config.height = 852;
        config.width = 480;

		new LwjglApplication(new WordCatcher(), config);
	}
}
