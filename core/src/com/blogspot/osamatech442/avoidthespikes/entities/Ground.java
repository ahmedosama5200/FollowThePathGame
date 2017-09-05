package com.blogspot.osamatech442.avoidthespikes.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class Ground {


    TextureRegion groundRegion;

    public Ground(TextureRegion groundRegion) {
        this.groundRegion = groundRegion;
    }

    public void render(SpriteBatch batch, float groundWidth) {
        batch.draw(
                groundRegion,
                0, 0,
                groundWidth,
                Constants.GROUND_HEIGHT
        );
    }

}
