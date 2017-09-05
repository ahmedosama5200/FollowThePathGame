package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.entities.*;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class SpikeColumn {

    public enum ColumnType {
        EQUAL_DISTRIBUTION,
        EQUAL_DISTRIBUTION_WITH_PADDING,
        CENTER_SPIKES,
        SIDE_SPIKES
    }

    private Spike[] columnSpikes;
    public float x;

    public SpikeColumn(float x, int spikeCount, ColumnType columnType, TextureRegion spikeRegion, Viewport viewport) {
        if (columnType == ColumnType.SIDE_SPIKES && spikeCount % 2 != 0) spikeCount--;

        this.x =x;
        this.columnSpikes = new Spike[spikeCount];

        float totalDistance = viewport.getWorldHeight() - Constants.GROUND_HEIGHT - Constants.SKY_HEIGHT;
        float restDistance = totalDistance - spikeCount * Constants.SPIKE_SIZE;

        switch (columnType) {
            case EQUAL_DISTRIBUTION:
                float margin1 = restDistance / (spikeCount - 1);
                for (int i = 0; i < spikeCount; i++)
                    columnSpikes[i] = new Spike(x, Constants.GROUND_HEIGHT + i * Constants.SPIKE_SIZE + i * margin1, spikeRegion);
                break;
            case EQUAL_DISTRIBUTION_WITH_PADDING:
                float margin2 = restDistance / (spikeCount + 1);
                for (int i = 0; i < spikeCount; i++)
                    columnSpikes[i] = new Spike(x, Constants.GROUND_HEIGHT + i * Constants.SPIKE_SIZE + (i + 1) * margin2, spikeRegion);
                break;
            case CENTER_SPIKES:
                float margin3 = restDistance / 2;
                columnSpikes[0] = new Spike(x, Constants.GROUND_HEIGHT + margin3, spikeRegion);
                for (int i = 1; i < spikeCount; i++)
                    columnSpikes[i] = new Spike(x, Constants.GROUND_HEIGHT + i * Constants.SPIKE_SIZE + margin3, spikeRegion);
                break;
            case SIDE_SPIKES:
                for (int i = 0; i < spikeCount / 2; i++)
                    columnSpikes[i] = new Spike(x, Constants.GROUND_HEIGHT + i * Constants.SPIKE_SIZE, spikeRegion);
                for (int i = spikeCount / 2; i < spikeCount; i++)
                    columnSpikes[i] = new Spike(x, Constants.GROUND_HEIGHT + (spikeCount / 2) * Constants.SPIKE_SIZE + restDistance + (i - spikeCount / 2) * Constants.SPIKE_SIZE, spikeRegion);
                break;
        }

    }

    public static int getMaxSpikesCount(Viewport viewport) {
        float totalDistance = viewport.getWorldHeight() - Constants.GROUND_HEIGHT - Constants.SKY_HEIGHT;
        float planeHeight = Constants.PLANE_HEIGHT * 2f;
        return (int) ((totalDistance + planeHeight) / (Constants.SPIKE_SIZE + planeHeight));
    }

    public void update(float elapsedTime, Plane plane) {
        for (Spike spike : columnSpikes) {
            spike.update(elapsedTime, plane);
        }
    }

    public void render(SpriteBatch batch) {
        for (Spike spike : columnSpikes) {
            spike.render(batch);
        }
    }
}
