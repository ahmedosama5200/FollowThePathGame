package com.blogspot.osamatech442.avoidthespikes.entities;

import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class LinePath {

    private float slope;
    private float initX;
    private float initY;
    private int nCoins;
    private int coinIndex;

    public LinePath(float initX, float initY, float slope, int nCoins) {
        this.slope = slope;
        this.initX = initX;
        this.initY = initY;
        this.nCoins = nCoins;
        this.coinIndex = 0;
    }

    public boolean hasNext() {
        return coinIndex < nCoins;
    }

    public float getCurrentXCoord() {
        return getXCoord(coinIndex - 1);
    }

    public float getLastXCoord() {
        return getXCoord(nCoins - 1);
    }

    public float getLastYCoord() {
        return getYCoord(nCoins - 1);
    }

    public float nextXCoord() {
        return getXCoord(coinIndex);
    }

    public float nextYCoord() {
        return getYCoord(coinIndex++);
    }

    private float getXCoord(int index) {
        return initX + index * Constants.COIN_DISTANCE;
    }

    private float getYCoord(int index) {
        return getYCoord(getXCoord(index));
    }

    private float getYCoord(float x) {
        /*
        float lineX1 = initX + Constants.COIN_RADIUS;
        float lineY1 = initY + Constants.COIN_RADIUS;1

        float lineX = x + Constants.COIN_RADIUS;
        float lineY = slope * (lineX - lineX1) + lineY1;

        return lineY - Constants.COIN_RADIUS;
        */
        return slope * (x - initX) + initY;
    }

    public void nextPath(float initX, float initY, float slope, int nCoins) {
        float y = getYCoord(initX);
        this.initX = initX;
        this.initY = y;
        this.slope = slope;
        this.nCoins = nCoins;
        this.coinIndex = 0;
    }


}

/*



   public static int adjustCoinsNumber(int nCoins, float initX, float initY, float slope, Viewport viewport) {
        int n = nCoins;
        float y = getYCoord(getXCoord(n - 1, initX), initX, initY, slope);
        if (slope >= 0)
            while (y + Constants.COIN_SIZE > viewport.getWorldHeight() - Constants.SKY_HEIGHT) {
                y -= Constants.COIN_SIZE;
                n--;
            }
        else
            while (y < Constants.GROUND_HEIGHT) {
                y += Constants.COIN_SIZE;
                n--;
            }

        return n - 1;
    }

 */
