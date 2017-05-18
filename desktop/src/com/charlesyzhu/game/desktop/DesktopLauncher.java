package com.charlesyzhu.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.charlesyzhu.game.DeepDiverApp;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = DeepDiverApp.V_HEIGHT;
		config.width = DeepDiverApp.V_WIDTH;
		config.title = DeepDiverApp.TITLE;
		new LwjglApplication(new DeepDiverApp(), config);
	}
}
