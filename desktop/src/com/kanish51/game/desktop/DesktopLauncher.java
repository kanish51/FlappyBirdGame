package com.kanish51.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kanish51.game.FlappyDemo;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width=FlappyDemo.WIDTH;
		config.height=FlappyDemo.HEIGHT;
		config.title=FlappyDemo.TITLE;
		new LwjglApplication(new FlappyDemo(), config);
	}
}
