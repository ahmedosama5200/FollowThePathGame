package com.blogspot.osamatech442.avoidthespikes.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.AvoidTheSpikesGame;
import com.blogspot.osamatech442.avoidthespikes.entities.Coin;
import com.blogspot.osamatech442.avoidthespikes.entities.Plane;
import com.blogspot.osamatech442.avoidthespikes.screens.PlayScreen;

public class CoinCreator {

    private TextureRegion coinRegion;
    private Viewport viewport;
    private Array<Coin> coins;
    private PathManager pathManager;

    public CoinCreator(TextureRegion coinRegion, Viewport viewport) {
        this.coinRegion = coinRegion;
        this.viewport = viewport;
        this.coins = new Array<Coin>();

    }

    public void init() {
        this.pathManager = new PathManager(viewport);
        addCoin();
    }

    public void update(Plane plane) {
        //Adding new coins
        if (pathManager.isToAddCoin()) {
            addCoin();
        }

        //Updating the current coins
        if (plane.getBounds().x + Constants.PLANE_WIDTH > pathManager.pathInitialX + Constants.COIN_SIZE / 2) {
            if (isGameover(plane)) {
                kill(plane);
            }
        }

    }

    //Returns true if the game is over
    private boolean isGameover(Plane plane) {
        boolean isGameover = true;
        for (Coin coin : coins) {
            //Remove coins
            if (coin.getBounds().x + coin.getBounds().width < viewport.getCamera().position.x - viewport.getWorldWidth() / 2) {
                coins.removeValue(coin, false);
                continue;
            }

            //update coins
            if (coin.getBounds().overlaps(plane.getBounds())) {
                coin.take();
                isGameover = false;
            }
        }
        return isGameover;
    }

    private void addCoin() {
        coins.add(new Coin(
                pathManager.nextXCoord(),
                pathManager.nextYCoord()
        ));
    }

    private void kill(Plane plane) {
        PlayScreen playScreen = (PlayScreen) (((AvoidTheSpikesGame) Gdx.app.getApplicationListener()).getScreen());
        playScreen.setGameover(true);
        plane.setDeathPos(plane.getBounds().x);
    }

    public void render(SpriteBatch batch) {
        for (Coin coin : coins) {
            coin.render(batch, coinRegion);
        }
    }


}

/*

     if (virtualCoin != null && !plane.getBounds().overlaps(virtualCoin.getBounds()))
            virtualCoin = null;

        if (virtualCoin == null) {
            Coin collidedCoin = getCollidedCoin(plane);
            if (collidedCoin != null) {
                virtualCoin = collidedCoin;
                coins.removeValue(collidedCoin, false);
            } else {
                kill();
            }
        }


    private Coin getCollidedCoin(Plane plane) {
        for (Coin coin : coins) {
            if (coin.getBounds().overlaps(plane.getBounds())) {
                return coin;
            }
        }
        return null;
    }

 */