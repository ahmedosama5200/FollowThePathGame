package com.blogspot.osamatech442.avoidthespikes.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class Coin {

    private Rectangle bounds;
    private boolean isVisible;

    public Coin(float x, float y) {
        this.bounds = new Rectangle(x, y, Constants.COIN_SIZE, Constants.COIN_SIZE);
        this.isVisible = true;
    }

    public void render(SpriteBatch batch, TextureRegion coinRegion) {
        if (isVisible) {
            batch.draw(
                    coinRegion,
                    bounds.x,
                    bounds.y,
                    bounds.width,
                    bounds.height
            );
        }
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void take() {
        isVisible = false;
    }
}
