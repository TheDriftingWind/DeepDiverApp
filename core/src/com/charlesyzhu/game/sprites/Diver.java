package com.charlesyzhu.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.charlesyzhu.game.DeepDiverApp;

/**
 * Created by Charles on 1/8/2017.
 */

public class Diver {

    private static final float GRAVITY = -0.01f;
    private static final float FRICTION = 0.01f;
    private static final float MAX_FALLING = -1;
    private Vector2 position;
    private Vector2 velocity;
    private Texture currentTexture, frontTexture, leftTexture, rightTexture;
    private Rectangle hitBox;


    public Diver(float x, float y){
        position = new Vector2(x,y);
        velocity = new Vector2(0,0);
        frontTexture = new Texture("sprites/DiverFront.png");
        leftTexture = new Texture("sprites/DiverLeft.png");
        rightTexture = new Texture("sprites/DiverRight.png");
        currentTexture = frontTexture;
        hitBox = new Rectangle(x, y, currentTexture.getWidth(), currentTexture.getHeight());

    }

    public void update(){
        //keep player within the play screen;
        if (position.x < 0){
            position.x = 0;
        }
        if (position.x > DeepDiverApp.V_WIDTH - currentTexture.getWidth()){
            position.x = DeepDiverApp.V_WIDTH - currentTexture.getWidth();
        }
        //increase falling velocity | cap falling velocity so player doesn't fall too fast
       if (velocity.y > MAX_FALLING) {
           velocity.add(0, GRAVITY);
       }
//        if (position.y > MAX_FALLING) {
            position.add(velocity.x, velocity.y);
//        }
        //keep the player from going off the top and bottum of the screen
        if (position.y > DeepDiverApp.V_HEIGHT/2 + 1 ){
            position.y = DeepDiverApp.V_HEIGHT/2 + 1 ;
        }
//        if (position.y < currentTexture.getHeight()*2){
//            position.y = currentTexture.getHeight()*2;
//        }

        //slow the player down so that they don't keep going forever
        if (velocity.x != 0){
            if (velocity.x > 0){
                velocity.add(-FRICTION, 0);
            }
            if (velocity.x < 0){
                velocity.add(FRICTION, 0);
            }
        }

        updateTextures();

        hitBox.setPosition(position.x, position.y);

    }

    public void updateTextures(){
        if (velocity.x < 0.3 && velocity.x > -0.3){
            currentTexture = frontTexture;
        }
        else if(velocity.x > 0){
            currentTexture = rightTexture;
        }
        else if(velocity.x < 0){
            currentTexture = leftTexture;
        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public Texture getTexture(){
        return currentTexture;
    }

    public void boostUp(){
        velocity.y = 1;
    }

    public void moveLeft(){
        velocity.x = -1;
    }

    public void moveRight(){
        velocity.x = 1;
    }

    public Rectangle getHitBox(){
        return hitBox;
    }


}
