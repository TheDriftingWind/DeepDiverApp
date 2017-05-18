package com.charlesyzhu.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.charlesyzhu.game.DeepDiverApp;

/**
 * Created by Charles on 1/21/2017.
 */

public class MenuScreen implements Screen {

    private DeepDiverApp game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;

    public MenuScreen(DeepDiverApp game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(DeepDiverApp.V_WIDTH, DeepDiverApp.V_HEIGHT,gameCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();

        game.batch.end();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
