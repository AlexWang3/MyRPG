package com.myrpg.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.myrpg.game.MyRPG;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width= MyRPG.WIDTH;
		config.height=MyRPG.HEIGHT;
		config.title=MyRPG.TITLE;
		new LwjglApplication(new MyRPG(), config);
	}
}
