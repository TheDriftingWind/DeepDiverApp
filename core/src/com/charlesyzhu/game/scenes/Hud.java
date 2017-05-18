package com.charlesyzhu.game.scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.charlesyzhu.game.DeepDiverApp;
import com.charlesyzhu.game.sprites.Diver;

/**
 * Created by Charles on 1/7/2017.
 */

public class Hud {
    //VARIABLES
    public Stage stage;
    private Viewport viewport;

    //Variable Counters | Create setters and getters for
    private Integer depth;
    private Integer health;
    private Integer booster;

    //Variable Labels
    private Label depthCounter;
    private Label healthCounter;
    private Label boosterCounter;

    //Static Labels
    private Label depthLabel;
    private Label healthLabel;
    private Label boosterLabel;

    //Button Variables
    private TextButton leftButton, rightButton, boostButton;
    private TextureAtlas atlas;
    private Skin skin;
    private Table buttonTable;

    private Diver player;


    //CONSTRUCTOR
    public Hud(SpriteBatch sb, Diver diver){
        //pass the player character to be moved
        player = diver;
        //create new viewport specifically for the hud(seperate from the playScreen viewport;
        viewport = new FitViewport(DeepDiverApp.V_WIDTH, DeepDiverApp.V_HEIGHT, new OrthographicCamera());

        //stage contains all of the tables and buttons | tables contain the labels
        stage = new Stage(viewport, sb);

        //BUTTONS **********************************************************************************
        //define button variables
        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(atlas);
        buttonTable = new Table(skin);
        buttonTable.setFillParent(true);
        buttonTable.bottom();
        buttonTable.padBottom(20); // TODO: Find more reliable variable to replace 20;

        //defining text button style for the buttons
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 0;
        textButtonStyle.pressedOffsetY = 0;
        textButtonStyle.font = new BitmapFont();

        //Creating the buttons | adding listeners
        leftButton = new TextButton("LEFT", textButtonStyle);
        leftButton.pad(0);
        leftButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.moveLeft();
            }

        });

        rightButton = new TextButton("RIGHT", textButtonStyle);
        rightButton.pad(0);
        rightButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.moveRight();
            }

        });
        boostButton = new TextButton("BOOST!", textButtonStyle);
        boostButton.pad(0);
        boostButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player.boostUp();
                booster = booster-1;
                System.out.println(booster);
            }

        });

        //add buttons to the button table in order(left to right)
        buttonTable.add(leftButton);
        buttonTable.add(boostButton);
        buttonTable.add(rightButton);
        buttonTable.debug(); //enable debug lines around buttons

        //LABELS ***********************************************************************************
        //Define counter varaibles
        depth = 0;
        health = 3;
        booster = 5;

        //Variable Labels | Definitions
        depthCounter = new Label(String.format("%06d", depth), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthCounter = new Label(String.format("%01d", health), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boosterCounter = new Label(String.format("%01d", booster), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //Static Label | Definitions
        depthLabel = new Label("Depth:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        healthLabel = new Label("Lives:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        boosterLabel = new Label("Charges:", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //Create Table to add labels that will appear at the top of the screen;
        Table topTable = new Table();
        topTable.top();
        topTable.setFillParent(true);

        //add the labels that will appear in the top table
        topTable.add(depthLabel).expandX().padTop(5);
        topTable.add(healthLabel).expandX().padTop(5);
        topTable.row();
        topTable.add(depthCounter).expandX().padTop(5);
        topTable.add(healthCounter).expandX().padTop(5);

        //create the labels that will appear in the bottom of the screen
        Table botTable = new Table();
        botTable.bottom();
        botTable.setFillParent(true);

        //add the labels that appear at the bottom of the screen
        botTable.add(boosterLabel).expandX().padBottom(50); // TODO: find a more reliable variable in take place of 50
        botTable.add(boosterCounter).expandX().padBottom(50);


        //Add the tables to the screen
        stage.addActor(topTable);
        stage.addActor(botTable);
        //Add the Buttons to the stage
        stage.addActor(buttonTable);

    }

    public void update(){
        depth = (int)Math.abs((player.getPosition().y/10) - 20);
        depthCounter.setText(String.format("%06d", depth));
        boosterCounter.setText(String.format("%01d", booster));
    }

    public Integer getDepth(){
        return depth;
    }

}
