package com.charlesyzhu.game.map;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.charlesyzhu.game.DeepDiverApp;

/**
 * Created by Charles on 1/9/2017.
 */

public class Background {
    private Texture currentTexture;
    private Vector2 position;
    public Texture[] backgrounds;
    private OrthographicCamera camera;
    private Queue<Texture> bgQueue;

    public Background(float x, float y, Texture initial, OrthographicCamera cam, Queue<Texture> queue){
        camera = cam;
        bgQueue = queue;
        backgrounds = new Texture[7];
        backgrounds[0] = new Texture("background/Bg2.png");
        backgrounds[1] = new Texture("background/Bg3.png");
        backgrounds[2] = new Texture("background/Bg4.png");
        backgrounds[3] = new Texture("background/Bg5.png");
        backgrounds[4] = new Texture("background/Bg6.png");
        backgrounds[5] = new Texture("background/Bg7.png");
        backgrounds[6] = new Texture("background/Bg8.png");

        currentTexture = initial;
        position = new Vector2(x,y);
    }

    public void relocate(){
        if (position.y - currentTexture.getHeight()*2 - DeepDiverApp.V_HEIGHT/2 > camera.position.y-DeepDiverApp.V_HEIGHT){
            position.y = position.y - currentTexture.getHeight()*3;
            checkQueue();
        }

    }

    public void checkQueue(){
        if (bgQueue.size != 0){
            setBackground(bgQueue.removeFirst());
        }
    }

    public Texture getCurrentTexture(){
        return currentTexture;
    }

    public void setBackground(Texture bg){
        currentTexture = bg;
    }

    public Vector2 getPosition(){
        return position;
    }

    public Texture getBackground(int i){
        return backgrounds[i];
    }

}
