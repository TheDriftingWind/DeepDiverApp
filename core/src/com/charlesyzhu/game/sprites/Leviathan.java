package com.charlesyzhu.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Charles on 1/17/2017.
 */

public class Leviathan {
    public TextureRegion Head;
    public TextureRegion Body1, Body2, Body3;
    public TextureRegion Tail;
    public Texture spriteSheet;
    public TextureRegion[] frames;

    public Leviathan(float x, float y, float width, float height, float velocityX, float velocityY) {

        spriteSheet = new Texture("sprites/mob/leviathan.png");
        frames = new TextureRegion[6];
    }


    public TextureRegion getHeadTexture() {
        return null;
    }


    public void move() {

    }


    public void update() {

    }
}
