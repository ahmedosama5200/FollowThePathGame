package com.blogspot.osamatech442.avoidthespikes.utils;

import com.badlogic.gdx.graphics.Color;

public class Constants {

    //PlayScreen
    public static final Color PLAY_SCREEN_COLOR = Color.SKY;
    public static final float PLAY_SCREEN_SIZE = 100f;
    public static final float GRAVITY = 200f;

    //Plane
    public static final float PLANE_FRAME_DURATION = 0.1f;
    public static final float PLANE_INITIAL_XPOS = 20f;

    public static final float PLANE_WIDTH = 9.6f;
    public static final float PLANE_HEIGHT = PLANE_WIDTH * 73f / 88f;

    public static final float PLANE_XVELOCITY = 42f;
    public static final float PLANE_BACK_VELOCITY = 200f;
    public static final float PLANE_JUMP_VELOCITY = 48f;

    //Ground
    public static final float GROUND_HEIGHT = 20f;
    public static final float SKY_HEIGHT = 20f;

    //Coins
    public static final float COIN_SIZE = 8.7f;
    public static final float COIN_MARGIN = 0.5f;
    public static final float COIN_DISTANCE = COIN_SIZE + COIN_MARGIN;

    //Assets
    public static final String RED_PLANE_NAME = "plane_red";
    public static final String GREEN_PLANE_NAME = "plane_green";
    public static final String BLUE_PLANE_NAME = "plane_blue";
    public static final String PLANE_NAME_EXTENSION = ".png";

    public static final String GROUND_NAME = "ground.png";

    public static final String SPIKE_NAME = "spike.png";
    public static final String COIN_NAME = "coin.png";


    //Spike columns
    public static final int SPIKE_COLUMN_MIN_ITEMS = 2;

    public static final float SPIKE_COLUMNS_MIN_MARGIN = 6;
    public static final float SPIKE_COLUMNS_MAX_MARGIN = 5;

    //Spikes
    public static final float SPIKE_SIZE = 8.5f;
    public static final float SPIKE_ROTATION_PERIODIC_TIME = 0.8f;

    public static final float COIN_YMARGIN = 1f;
    public static final float COIN_YDISTANCE = COIN_SIZE + COIN_YMARGIN;
}
