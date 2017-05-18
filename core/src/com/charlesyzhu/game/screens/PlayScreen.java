package com.charlesyzhu.game.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.charlesyzhu.game.DeepDiverApp;
import com.charlesyzhu.game.map.Background;
import com.charlesyzhu.game.scenes.Hud;
import com.charlesyzhu.game.sprites.Barracuda;
import com.charlesyzhu.game.sprites.Diver;
import com.charlesyzhu.game.sprites.Mob;
import com.charlesyzhu.game.sprites.Shark;
import com.charlesyzhu.game.sprites.TinyFish;

import java.util.ArrayList;

/**
 * Created by Charles on 1/7/2017.
 */

public class PlayScreen implements Screen {

    //Setup
    private DeepDiverApp game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Hud hud;

    //Background
    private int depthZone; //used to keep depthCheck from adding unwanted textures to the bgQueue
    private Background bg1;
    private Background bg2;
    private Background bg3;

    /*
     Have the background check for any textures in this queue.
     If the queue isn't empty, get the texture from the queue when
     the next background gets relocated
      */
    private Queue<Texture> bgQueue;
    //Game Objects
    private Diver playerDiver;

    //Test Mobs**
    private ArrayList<Mob> mobs;
    private TinyFish tinyFish1, tinyFish2;

    public PlayScreen(DeepDiverApp game){
        //Setup
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(DeepDiverApp.V_WIDTH, DeepDiverApp.V_HEIGHT,gameCam);
        //Background
        depthZone = 0;
        bgQueue = new Queue<Texture>();
        bg1 = new Background(0,0, new Texture("background/Bg0.png"), gameCam, bgQueue);
        bg2 = new Background(0,-DeepDiverApp.V_HEIGHT, new Texture("background/Bg1.png"), gameCam, bgQueue);
        bg3 = new Background(0,-DeepDiverApp.V_HEIGHT*2, bg1.getBackground(0), gameCam, bgQueue);
        bgQueue.addFirst(bg1.getBackground(0));
        bgQueue.addLast(bg1.getBackground(0));
        //player
        playerDiver = new Diver(DeepDiverApp.V_WIDTH/2, DeepDiverApp.V_HEIGHT/2);
        //hud/GUI
        hud = new Hud(game.batch, playerDiver);

        //Test mobs**
//        tinyFish1 = new TinyFish(playerDiver.getPosition().x - 30 , playerDiver.getPosition().y + 10, 0.2f, 0);
//        tinyFish2 = new TinyFish(playerDiver.getPosition().x + 30 , playerDiver.getPosition().y + 10, -0.2f, 0);
        mobs = new ArrayList<Mob>();
//        mobs.add(new TinyFish(playerDiver.getPosition().x - 40, playerDiver.getPosition().y+ 20, 0.3f, 0));
//        mobs.add(new Shark(playerDiver.getPosition().x - 60, playerDiver.getPosition().y -30, 0.5f, 0));
//        mobs.add(new Barracuda(playerDiver.getPosition().x + 60, playerDiver.getPosition().y -30, -0.8f, 0));

    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(hud.stage);

    }

    public void update(float dt){
        playerDiver.update();
        //*TEST MOBS***
//        tinyFish1.update();
//        tinyFish2.update();
        for (int i = 0; i < mobs.size(); i++){
            mobs.get(i).update();
        }

        bg1.relocate();
        bg2.relocate();
        bg3.relocate();
        depthCheck();
        if(mobs.size() <= 5) {
            generateMob();
        }
        checkMobDeletion();
        checkCollision();
    }

    @Override
    public void render(float delta) {
        gameCam.update();
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        update(delta);
        //Draw game objects
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.begin();
        game.batch.draw(bg1.getCurrentTexture(), bg1.getPosition().x, bg1.getPosition().y);
        game.batch.draw(bg2.getCurrentTexture(), bg2.getPosition().x, bg2.getPosition().y);
        game.batch.draw(bg3.getCurrentTexture(), bg3.getPosition().x, bg3.getPosition().y);
        game.batch.draw(playerDiver.getTexture(), playerDiver.getPosition().x, playerDiver.getPosition().y);
        //Test Mobs**
//        game.batch.draw(tinyFish1.getCurrentTexture(), tinyFish1.getPosition().x, tinyFish1.getPosition().y);
//        game.batch.draw(tinyFish2.getCurrentTexture(), tinyFish2.getPosition().x, tinyFish2.getPosition().y);
        for (int i = 0; i < mobs.size(); i++){
            game.batch.draw(mobs.get(i).getCurrentTexture(), mobs.get(i).getPosition().x, mobs.get(i).getPosition().y);
        }

        game.batch.end();
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        //Draw HUD
        hud.stage.act(delta);
        hud.stage.draw();
        hud.update();

        gameCam.position.set(DeepDiverApp.V_WIDTH/2, playerDiver.getPosition().y,0);

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

    //New Methods
    public void depthCheck(){
        if(Math.abs(hud.getDepth()) > 200 && depthZone == 0){
            bgQueue.addLast(bg1.getBackground(1));
            bgQueue.addLast(bg1.getBackground(2));
            for(int i = 1; i <= 3; i++) {
                bgQueue.addLast(bg1.getBackground(3));
            }
            depthZone += 1;
        }
        if (Math.abs(hud.getDepth()) > 1000 && depthZone == 1){
            bgQueue.addLast(bg1.getBackground(4));
            bgQueue.addLast(bg1.getBackground(5));
            for(int i = 1; i <=3; i ++) {
                bgQueue.addLast(bg1.getBackground(6));
            }
            depthZone += 1;
        }
    }

    public void generateMob(){
        int randomNum = (int)(Math.random()*20);
        if (randomNum >= 17 && randomNum <= 20){
            mobs.add(new Shark(playerDiver, DeepDiverApp.V_WIDTH, (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2)) + (float)(Math.random()*350), -0.5f, 0));
        }
        if (randomNum >= 15 && randomNum < 17){
            mobs.add(new Shark(playerDiver, -64, (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2)) + (float)(Math.random()*350), 0.5f, 0));
        }
        if (randomNum >= 10 && randomNum < 15){
            mobs.add(new Barracuda(playerDiver, -32, (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2)) + (float)(Math.random()*300), 1.2f, 0.5f));
        }
        if (randomNum >= 5 && randomNum < 10){
            mobs.add(new Barracuda(playerDiver, DeepDiverApp.V_WIDTH, (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2)) + (float)(Math.random()*300), -1.2f, 0.5f));
        }
        if (randomNum >=3 && randomNum < 5){
            mobs.add(new TinyFish(playerDiver, DeepDiverApp.V_WIDTH - (float)(Math.random()*70), (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2) - 30) + (float)(Math.random()*20), -0.1f, 0));
        }
        if (randomNum >= 0 && randomNum < 3){
            mobs.add(new TinyFish(playerDiver, -15 + (float)(Math.random()*70), (playerDiver.getPosition().y - (DeepDiverApp.V_HEIGHT/2) - 30) + (float)(Math.random()*20), 0.1f, 0));
        }
    }

    public void checkMobDeletion(){
        for (int i = 0; i < mobs.size(); i++){
            mobs.get(i).checkDeletion();
            if(mobs.get(i).getDeleteState() == true){
                mobs.remove(i);
            }
        }
    }

    public void checkCollision(){
        for (int i = 0; i < mobs.size(); i++){
            if (mobs.get(i).getHitBox().overlaps(playerDiver.getHitBox())){
                System.out.println("Player Overlapped");
            }
        }
    }
}
