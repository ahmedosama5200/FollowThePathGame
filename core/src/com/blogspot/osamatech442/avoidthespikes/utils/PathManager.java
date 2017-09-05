package com.blogspot.osamatech442.avoidthespikes.utils;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.entities.LinePath;

public class PathManager {

    private static final float MIN_SLOPE_ANGLE = 25;
    private static final float MAX_SLOPE_ANGLE = 40;
    private static final int MAX_PATH_COINS = 6;
    private static final int MIN_PATH_COINS = 3;
    private static final int INITIAL_PATH_COINS = 4;

    //References
    private Viewport viewport;

    //Properties
    public float pathInitialX;
    public float pathInitialY;
    private LinePath coinPath;
    private float lastSlope;
    private int lastNumCoins;
    private int linePos;

    public PathManager(Viewport viewport) {
        this.viewport = viewport;

        this.pathInitialX = getPathInitialX();
        this.pathInitialY = getPathInitialY();
        this.coinPath = new LinePath(pathInitialX, pathInitialY, 0, INITIAL_PATH_COINS);
        this.lastSlope = 0;
        this.lastNumCoins = INITIAL_PATH_COINS;
        this.linePos = 1;
    }

    private float getPathInitialX() {
        return viewport.getCamera().position.x + viewport.getWorldWidth() / 2;
    }

    private float getPathInitialY() {
        return (viewport.getWorldHeight() - Constants.COIN_SIZE) / 2;
    }

    public float nextXCoord() {
        if (!coinPath.hasNext()) {
            float initX = coinPath.getLastXCoord() + Constants.COIN_DISTANCE;
            float initY = coinPath.getLastYCoord();
            float slope;
            int nCoins;
            switch (linePos) {
                case 1:
                    //Up Slope
                    nCoins = generateNumCoins();
                    slope = generateSlope();
                    linePos = 2;
                    break;
                case 2:
                    //Down Slope
                    nCoins = lastNumCoins;
                    slope = -lastSlope;
                    linePos = 3;
                    break;
                case 3:
                    //Zero Slope
                    nCoins = generateNumCoins();
                    slope = 0;
                    linePos = 4;
                    break;
                case 4:
                    //Down Slope
                    nCoins = generateNumCoins();
                    slope = -generateSlope();
                    linePos = 5;
                    break;
                case 5:
                    //Up Slope
                    nCoins = lastNumCoins;
                    slope = -lastSlope;
                    linePos = 6;
                    break;
                default:
                    //Zero Slope
                    nCoins = generateNumCoins();
                    slope = 0;
                    linePos = 1;
                    break;
            }

            lastSlope = slope;
            lastNumCoins = nCoins;

            coinPath.nextPath(initX, initY, slope, nCoins);


        }
        return coinPath.nextXCoord();
    }

    public float nextYCoord() {
        return coinPath.nextYCoord();
    }

    public boolean isToAddCoin() {
        return coinPath.getCurrentXCoord() + Constants.COIN_SIZE <= viewport.getCamera().position.x + viewport.getWorldWidth() / 2;
    }

    private float generateSlope() {
        float angle = MathUtils.random(MIN_SLOPE_ANGLE, MAX_SLOPE_ANGLE);
        /*
        if (initY >= pathInitialY + Constants.COIN_SIZE / 2) {
            angle = 180 - angle;
        }
        */
        return (float) Math.tan(Math.toRadians(angle));
    }

    private int generateNumCoins() {
        return MathUtils.random(MIN_PATH_COINS, MAX_PATH_COINS);
    }

    private int getMaxCoins(float slope, float initY) {
        if (slope >= 0)
            return (int) ((viewport.getWorldHeight() - Constants.SKY_HEIGHT - initY - Constants.COIN_DISTANCE) / Constants.COIN_SIZE);
        else
            return (int) ((initY - Constants.GROUND_HEIGHT) / Constants.COIN_SIZE);
    }
/*
    private CoinPath createCoinPath() {

        int pathType = MathUtils.random(1, COIN_PATH_TYPES_COUNT);
        pathType = 2;
        switch (pathType) {
            case 1:
                return createRandomLinePath();
            case 2:
                return createRandomArcPath(true);
            default: *//*3*//*
                return createRandomArcPath(false);
        }

    }

    private CoinPath createRandomLinePath() {
        float slope = generateSlope();
        float initX = coinPath.getCurrentXCoord() + Constants.COIN_DISTANCE;
        float initY = coinPath.getLastYCoord();
        int nCoins = LinePath.adjustCoinsNumber(MathUtils.random(MIN_PATH_COINS, MAX_PATH_COINS), initX, initY, slope, viewport);
        return new LinePath(nCoins, initX, initY, slope);
    }

    private CoinPath createRandomArcPath(boolean isTopArc) {
        return new ArcPath(7, coinPath.getCurrentXCoord() + Constants.COIN_DISTANCE, coinPath.getLastYCoord(), isTopArc);
    }

    private float generateSlope() {
        double angle = MathUtils.random(0, SLOPE_ANGLE);

        if (lastSlopeSign == 1) {
            lastSlopeSign = -1;
            angle = 180 - angle;
        } else lastSlopeSign = 1;

        return (float) Math.tan(Math.toRadians(angle));
    }*/

}
