package com.charlesyzhu.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Charles on 1/17/2017.
 */

public class Barracuda extends Mob {
    public TextureRegion currentTexture;
    public Texture spriteSheet;
    public TextureRegion[] frames;

    public Barracuda(Diver player, float x, float y, float velocityX, float velocityY) {
        super(player, x, y, 32, 14, velocityX, velocityY);
        spriteSheet = new Texture("sprites/mob/barracuda.png");
        frames = new TextureRegion[2];


        TextureRegion[][] tmpFrames = TextureRegion.split(spriteSheet, 32, 14);

        int index = 0;
        for (int i = 0; i <= 1; i++){
            for (int j = 0; j <= 0; j++){
                frames[index] = tmpFrames[j][i];
                index++;
            }
        }
        currentTexture = new TextureRegion(frames[0]);
    }

    @Override
    public TextureRegion getCurrentTexture() {
        return currentTexture;
    }

    @Override
    public void move() {
        super.position.add(super.velocity);
        if (velocity.x >= 0){
            currentTexture = frames[1];
        }
        if (velocity.x < 0){
            currentTexture = frames[0];
        }

    }

    @Override
    public void update() {
        move();
        hitBox.setPosition(position.x, position.y);
    }
}
