package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.blogspot.osamatech442.avoidthespikes.AvoidTheSpikesGame;
import com.blogspot.osamatech442.avoidthespikes.entities.Plane;
import com.blogspot.osamatech442.avoidthespikes.screens.PlayScreen;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class Spike {

    private Rectangle bounds;
    private TextureRegion spikeRegion;
    private float rotation;

    public Spike(float x, float y, TextureRegion spikeRegion) {
        this.spikeRegion = spikeRegion;
        this.bounds = new Rectangle(x, y, Constants.SPIKE_SIZE, Constants.SPIKE_SIZE);
        this.rotation = 0;
    }

    public void update(float elapsedTime, Plane plane) {
        rotation = getRotation(elapsedTime);
        if (bounds.overlaps(plane.getBounds())) {
            ((AvoidTheSpikesGame) Gdx.app.getApplicationListener()).setScreen(new PlayScreen(((AvoidTheSpikesGame) Gdx.app.getApplicationListener())));
        }
    }

    private float getRotation(float elapsedTime) {
        return ((elapsedTime / Constants.SPIKE_ROTATION_PERIODIC_TIME) % 1) * 360f;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                spikeRegion,
                bounds.x,
                bounds.y,
                bounds.width / 2,
                bounds.height / 2,
                bounds.width,
                bounds.height,
                1,
                1,
                rotation
        );
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
