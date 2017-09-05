package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public abstract class CoinPath {

    protected int nCoins;
    protected int coinIndex;

    public CoinPath(int nCoins) {
        this.nCoins = nCoins;
        this.coinIndex = 0;
    }

    //X
    public float nextXCoord() {
        return getXCoord(coinIndex);
    }

    public float getLastXCoord() {
        return getXCoord(nCoins - 1);
    }

    public float getXCoord(int index) {
        return getXCoord(index);
    }

    //Y
    public float nextYCoord() {
        return getYCoord(getXCoord(coinIndex++));
    }

    public float getLastYCoord() {
        return getYCoord(getLastXCoord());
    }

    public abstract float getYCoord(float x);

}
