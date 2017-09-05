package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class ArcPath extends CoinPath {

    private boolean isTopArc;

    public ArcPath(int nCoins, float initX, float initY, boolean isTopArc) {
        super(nCoins);
        this.isTopArc = isTopArc;
    }

    @Override
    public float getYCoord(float x) {
        return 0;
    }

    public static float getYCoord(int index, float initY, int nCoins, boolean isTopArc) {
        float y;
        if (nCoins % 2 == 0) {
            //Even number
            if (index < nCoins / 2)
                y = initY + index * Constants.COIN_YDISTANCE;
            else if (index == nCoins / 2)
                y = initY + (index - 1) * Constants.COIN_YDISTANCE;
            else
                y = initY + (nCoins - 1 - index) * Constants.COIN_YDISTANCE;

        } else {
            //Odd Number
            if (index <= (nCoins - 1) / 2)
                y = initY + index * Constants.COIN_YDISTANCE;
            else
                y = initY + (nCoins - 1 - index) * Constants.COIN_YDISTANCE;
        }

        return y;

    }
    /*
     n = 7   -->
    indexes = 0, 1, 2, 3, 4, 5, 6


    n = 6   -->
    indexes = 0, 1, 2, 3, 4, 5

     */

    /*

     double radius = (nCoins * Constants.COIN_SIZE - Constants.COIN_SIZE) / 2;
        double centerX = initX + Constants.COIN_RADIUS + radius;
        double centerY = initY + Constants.COIN_RADIUS + radius;

        float arcX = x + Constants.COIN_RADIUS;
        double underRoot = Math.sqrt(radius * radius - (centerX - arcX) * (centerX - arcX));


        float y1 = (float) (centerY + underRoot - Constants.COIN_RADIUS);
        float y2 = (float) (centerY - underRoot - Constants.COIN_RADIUS);

        if (isTopArc)
            return Math.max(y1, y2);
        else
            return Math.min(y1, y2);
     */


}
