package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class AdvancedLinePath {
/*
    private final int MIN_NORMAL_LINE_COINS;
    private final int MAX_NORMAL_LINE_COINS;

    private final float MAX_SLOPE_LINE_ANGLE;
    private final int MIN_SLOPE_LINE_COINS;
    private final int MAX_SLOPE_LINE_COINS;

    private final float OPPOSITE_LINES_ANGLE;
    private final int OPPOSITE_LINES_COINS;

    private int nCoins;
    private int coinIndex;
    private int type;
    private LinePath[] paths;

    private boolean isSlopeLineAgain;
    private float lastSlopeAngle;

    public AdvancedLinePath(float initX, float initY, Viewport viewport) {
        this.MIN_NORMAL_LINE_COINS = 4;
        this.MAX_NORMAL_LINE_COINS = (int) (viewport.getWorldWidth() / Constants.COIN_DISTANCE);

        this.MAX_SLOPE_LINE_ANGLE = 35f;
        this.MIN_SLOPE_LINE_COINS = 4;
        this.MAX_SLOPE_LINE_COINS = (int) (viewport.getWorldHeight() / 2 / Constants.COIN_DISTANCE);

        this.OPPOSITE_LINES_ANGLE = 30f;
        this.OPPOSITE_LINES_COINS = MAX_SLOPE_LINE_COINS;

        this.nCoins = MIN_NORMAL_LINE_COINS;
        this.coinIndex = 0;
        this.type = 0;
        this.paths = new LinePath[]{new LinePath(0, initX, initY)};

        this.isSlopeLineAgain = false;
        this.lastSlopeAngle = MAX_SLOPE_LINE_ANGLE;

    }

    public boolean hasNext() {
        return coinIndex < nCoins;
    }

    public int getCurrentIndex() {
        return coinIndex - 1;
    }

    public Vector2[] nextCoords() {
        Vector2[] coords = new Vector2[paths.length];
        int i = 0;
        for (LinePath linePath : paths) {
            float x = linePath.getXCoord(coinIndex);
            float y = linePath.getYCoord(x);
            coords[i++] = new Vector2(x, y);
        }
        coinIndex++;
        return coords;
    }

    public boolean isReady(Viewport viewport) {
        return paths[0].getXCoord(getCurrentIndex()) + Constants.COIN_SIZE <= viewport.getCamera().position.x + viewport.getWorldWidth() / 2;
    }

    public void nextPath() {
        int nextType = nextPathType();
        this.paths = createPath(nextType);
        this.type = nextType;
        this.coinIndex = 0;
    }

    private LinePath[] createPath(int pathType) {
        LinePath[] linePaths;
        int newNCoins;
        float initX = paths[0].getXCoord(nCoins - 1) + Constants.COIN_DISTANCE;
        switch (pathType) {
            case 0:
                //Normal Line
                newNCoins = MathUtils.random(MIN_NORMAL_LINE_COINS, MAX_NORMAL_LINE_COINS);
                linePaths = new LinePath[]{
                        new LinePath(0, initX)
                };
                break;
            case 1:
                //Slope Line
                if (isSlopeLineAgain) {
                    newNCoins = nCoins;
                    linePaths = new LinePath[]{
                            new LinePath((float) Math.tan(Math.toRadians(180 - lastSlopeAngle)), initX)
                    };
                    isSlopeLineAgain = false;
                } else {
                    newNCoins = MathUtils.random(MIN_SLOPE_LINE_COINS, MAX_SLOPE_LINE_COINS);
                    lastSlopeAngle = MathUtils.random(0, MAX_SLOPE_LINE_ANGLE);
                    linePaths = new LinePath[]{
                            new LinePath((float) Math.tan(Math.toRadians(lastSlopeAngle)), initX)
                    };
                    isSlopeLineAgain = true;
                }
                break;
            case 2:
                //Doubled Lines
                newNCoins = MathUtils.random(MIN_NORMAL_LINE_COINS, MAX_NORMAL_LINE_COINS);
                linePaths = new LinePath[]{
                        new LinePath(0, initX),
                        new LinePath(0, initX)
                };
                break;
            case 3:
                //Positive Opposite Lines
                newNCoins = OPPOSITE_LINES_COINS;
                linePaths = new LinePath[]{
                        new LinePath((float) Math.tan(Math.toRadians(180 - OPPOSITE_LINES_ANGLE)), initX),
                        new LinePath((float) Math.tan(Math.toRadians(OPPOSITE_LINES_ANGLE)), initX)
                };
                break;
            case 4:
                //Negative Opposite Lines
                newNCoins = OPPOSITE_LINES_COINS;
                linePaths = new LinePath[]{
                        new LinePath((float) Math.tan(Math.toRadians(OPPOSITE_LINES_ANGLE)), initX),
                        new LinePath((float) Math.tan(Math.toRadians(180 - OPPOSITE_LINES_ANGLE)), initX)
                };
                break;
            case 5:
                //Positive Opposite Lines
                newNCoins = OPPOSITE_LINES_COINS;
                linePaths = new LinePath[]{
                        new LinePath(0, initX),
                        new LinePath((float) Math.tan(Math.toRadians(180 - OPPOSITE_LINES_ANGLE)), initX),
                        new LinePath((float) Math.tan(Math.toRadians(OPPOSITE_LINES_ANGLE)), initX)
                };
                break;
            default://6
                //Negative Opposite Lines
                newNCoins = OPPOSITE_LINES_COINS;
                linePaths = new LinePath[]{
                        new LinePath(0, initX),
                        new LinePath((float) Math.tan(Math.toRadians(OPPOSITE_LINES_ANGLE)), initX),
                        new LinePath((float) Math.tan(Math.toRadians(180 - OPPOSITE_LINES_ANGLE)), initX),
                };
                break;
        }
        connectPaths(linePaths);
        nCoins = newNCoins;
        return linePaths;
    }

    private void connectPaths(LinePath[] nextPaths) {
        if (paths.length == nextPaths.length) {
            for (int i = 0; i < paths.length; i++) {
                nextPaths[i].setInitY(paths[i].getYCoord(nCoins - 1));
            }
        } else if (nextPaths.length == 1) {
            if (paths.length == 2) {
                float bottomY = paths[0].getYCoord(nCoins - 1);
                float topY = paths[1].getYCoord(nCoins - 1);
                nextPaths[0].setInitY(bottomY + (topY - bottomY) / 2);
            } else if (paths.length == 3) {
                nextPaths[0].setInitY(paths[0].getYCoord(nCoins - 1));
            }
        } else if (nextPaths.length == 2) {
            float y = paths[0].getYCoord(nCoins - 1);
            nextPaths[0].setInitY(y - Constants.COIN_SIZE / 2 - Constants.COIN_YMARGIN / 2);
            nextPaths[1].setInitY(y + Constants.COIN_SIZE - Constants.COIN_SIZE / 2 + Constants.COIN_YMARGIN);
        } else if (nextPaths.length == 3) {
            float y;
            if (paths.length == 1) {
                y = paths[0].getYCoord(nCoins - 1);
            } else {
                float bottomY = paths[0].getYCoord(nCoins - 1);
                float topY = paths[1].getYCoord(nCoins - 1);
                y = bottomY + (topY - bottomY) / 2;
            }
            nextPaths[0].setInitY(y);
            nextPaths[1].setInitY(y + Constants.COIN_SIZE + Constants.COIN_YMARGIN);
            nextPaths[2].setInitY(y - Constants.COIN_SIZE - Constants.COIN_YMARGIN);
        }

    }

    private int nextPathType() {
        if (isSlopeLineAgain) return 1;

        switch (type) {
            case 0:
            case 1:
            case 6:
                int[] availableTypes1 = new int[]{
                        0, 1, 2, 3, 5
                };
                return availableTypes1[MathUtils.random(0, availableTypes1.length - 1)];
            case 2:
            case 3:
            case 4:
                int[] availableTypes2 = new int[]{
                        2, 4
                };
                return availableTypes2[MathUtils.random(0, availableTypes2.length - 1)];
            case 5:
                return 6;
            default:
                return 2;
        }
    }
    */


}
        /*
              * 0,1,     , ,4,   ,6 <-- 0 --> 0,1,    2,3, ,  5,
              * 0,1,     , ,4,   ,6 <-- 1 --> 0,1,    2,3, ,  5,
              *
              * 0,1,    2,3,4,   ,6 <-- 2 -->  , ,    2, ,4,   ,
              * 0,1,     , ,4,   ,6 <-- 3 -->  , ,    2, ,4,   ,
              *  , ,    2,3, ,   ,  <-- 4 --> 0,1,    2,3, ,   ,
              *
              * 0,1,     , , ,   ,6 <-- 5 -->  , ,     , , ,   ,6
              *  , ,     , , ,  5,  <-- 6 --> 0,1,    2,3, ,  5,
        */

        /*
              ***** ONE LINE ****
              * 0 --> normal line(slope = 0)
              * 1 --> line with slope
        */

        /*
              **** TWO LINES ****
              * 2 --> doubled lines
              * 3 --> positive opposite lines
              * 4 --> negative opposite lines
        */

        /*
              **** THREE LINES ****
              * 5 --> positive opposite lines
              * 6 --> negative opposite lines
        */


