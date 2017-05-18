/*
Game by Charles Y Zhu
using LibGDX
 */
package com.charlesyzhu.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.charlesyzhu.game.screens.PlayScreen;

public class DeepDiverApp extends Game {

    public static final int V_HEIGHT = 400;
    public static final int V_WIDTH = 240;
	public static final String TITLE = "Deep Diver";

	public SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
