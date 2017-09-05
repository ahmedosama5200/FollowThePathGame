package com.blogspot.osamatech442.avoidthespikes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.blogspot.osamatech442.avoidthespikes.screens.PlayScreen;
import com.blogspot.osamatech442.avoidthespikes.utils.Assets;

public class AvoidTheSpikesGame extends Game {

    public SpriteBatch batch;
    public Assets assets;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        this.assets = new Assets();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        assets.dispose();
    }
}
