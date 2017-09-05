package com.blogspot.osamatech442.avoidthespikes.garbage;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.entities.Plane;
import com.blogspot.osamatech442.avoidthespikes.garbage.SpikeColumn;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class SpikeCreator {

    private Viewport viewport;
    private float elapsedTime;
    private TextureRegion spikeRegion;
    private Array<SpikeColumn> spikeColumns;
    private float travelledDistance;
    private SpikeColumn.ColumnType lastColumnType;

    public SpikeCreator(Viewport viewport, TextureRegion spikeRegion) {
        this.viewport = viewport;
        this.spikeColumns = new Array<SpikeColumn>();
        this.elapsedTime = 0;
        this.spikeRegion = spikeRegion;
        this.travelledDistance = 0;
        this.lastColumnType = SpikeColumn.ColumnType.SIDE_SPIKES;
    }

    public void update(float delta, Plane plane) {
        removeInvisibleColumns();
        addSpikeColumns();
        updateSpikeColumns(plane);
        elapsedTime += delta;
        travelledDistance += delta * Constants.PLANE_XVELOCITY;
    }

    private void removeInvisibleColumns() {
        for (SpikeColumn spikeColumn : spikeColumns) {
            if (spikeColumn.x + Constants.SPIKE_SIZE < viewport.getCamera().position.x - viewport.getWorldWidth() / 2) {
                spikeColumns.removeValue(spikeColumn, true);
            }
        }
    }

    private void addSpikeColumns() {
        if (travelledDistance >= Constants.SPIKE_SIZE + Constants.SPIKE_COLUMNS_MIN_MARGIN) {
            SpikeColumn.ColumnType currentColumnType ;
            switch (lastColumnType) {
                case EQUAL_DISTRIBUTION:
                    currentColumnType = SpikeColumn.ColumnType.EQUAL_DISTRIBUTION_WITH_PADDING;
                    break;
                case EQUAL_DISTRIBUTION_WITH_PADDING:
                    currentColumnType = SpikeColumn.ColumnType.CENTER_SPIKES;
                    break;
                case SIDE_SPIKES:
                    currentColumnType = SpikeColumn.ColumnType.EQUAL_DISTRIBUTION;
                    break;
                default: //Center
                    currentColumnType = SpikeColumn.ColumnType.SIDE_SPIKES;
                    break;
            }
            lastColumnType = currentColumnType;
            spikeColumns.add(
                    new SpikeColumn(
                            viewport.getCamera().position.x + viewport.getWorldWidth() / 2, 2,
                            currentColumnType,
                            spikeRegion, viewport
                    )
            );

            travelledDistance = 0;
        }
    }

    private void updateSpikeColumns(Plane plane) {
        for (SpikeColumn spikeColumn : spikeColumns) {
            spikeColumn.update(elapsedTime, plane);
        }
    }

    public void render(SpriteBatch batch) {
        for (SpikeColumn spikeColumn : spikeColumns) {
            spikeColumn.render(batch);
        }
    }
}
