package com.charlesyzhu.game.sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.charlesyzhu.game.DeepDiverApp;

/**
 * Created by Charles on 1/10/2017.
 */

public abstract class Mob {

//    public Animation animation;
    public Vector2 position;
    public Vector2 velocity;
    public Rectangle hitBox;
    public Diver playerDiver;
    public Boolean toDelete;
//    public float elapsedTime;

    public Mob(Diver player, float x, float y, float width, float height, float velocityX, float velocityY){
        position = new Vector2(x, y);
        velocity = new Vector2(velocityX,velocityY);
        hitBox = new Rectangle(x,y, width, height);
        playerDiver = player;
        toDelete = false;
    }

    public Vector2 getPosition(){
        return position;
    }

//    public Vector2 getVelocity(){
//        return velocity;
//    }
//
    public void setVelocity(float x, float y){
        velocity.x = x;
        velocity.y = y;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }

    public void checkDeletion(){
        if (position.y > playerDiver.getPosition().y + DeepDiverApp.V_HEIGHT/2 +64){
            toDelete = true;
        }
        if (position.x > DeepDiverApp.V_WIDTH + 70){
            toDelete = true;
        }
        if (position.x < - 70){
            toDelete = true;
        }
//        if ( Math.sqrt( Math.pow((position.x - playerDiver.getPosition().x),2) + ( Math.pow((position.y - playerDiver.getPosition().y),2)) ) >)
    }

    public Boolean getDeleteState(){
        return toDelete;
    }

    public abstract TextureRegion getCurrentTexture();
    //For possible additions in the future | having animated sprites
//    public void setAnimation(Animation anim){ animation = anim; }
//    public Animation getAnimation(){
//        return animation;
//    }

    public abstract void move();

    public abstract void update();
}
