package com.blogspot.osamatech442.avoidthespikes.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.sun.corba.se.impl.orbutil.closure.Constant;

public class Assets implements Disposable {

    public AssetManager assetManager;

    public PlaneAssets planeAssets;
    public DecorationAssets decorationAssets;
    public ComponentsAssets componentsAssets;

    public Assets() {
        this.assetManager = new AssetManager();
        initAssets();

        this.planeAssets = new PlaneAssets();
        this.decorationAssets = new DecorationAssets();
        this.componentsAssets = new ComponentsAssets();

    }

    public void initAssets() {
        //Planes
        for (int i = 1; i <= 3; i++) {
            assetManager.load(Constants.RED_PLANE_NAME + i + Constants.PLANE_NAME_EXTENSION, Texture.class);
        }

        //Decorations
        assetManager.load(Constants.GROUND_NAME, Texture.class);

        //Components
        assetManager.load(Constants.SPIKE_NAME, Texture.class);
        assetManager.load(Constants.COIN_NAME, Texture.class);

        assetManager.finishLoading();
    }

    public class PlaneAssets {
        public Animation<TextureRegion> redPlaneAnimation;

        public PlaneAssets() {
            Array<TextureRegion> planeRegions = new Array<TextureRegion>();

            //Red Plane
            for (int i = 1; i <= 3; i++) {
                planeRegions.add(new TextureRegion(assetManager.get(Constants.RED_PLANE_NAME + i + Constants.PLANE_NAME_EXTENSION, Texture.class)));
            }
            this.redPlaneAnimation = new Animation<TextureRegion>(Constants.PLANE_FRAME_DURATION, planeRegions);

        }
    }

    public class ComponentsAssets {
        public TextureRegion spike;
        public TextureRegion coin;

        public ComponentsAssets() {
            this.spike = new TextureRegion(assetManager.get(Constants.SPIKE_NAME, Texture.class));
            this.coin = new TextureRegion(assetManager.get(Constants.COIN_NAME, Texture.class));
        }
    }

    public class DecorationAssets {
        public TextureRegion ground;

        public DecorationAssets() {
            ground = new TextureRegion(assetManager.get(Constants.GROUND_NAME, Texture.class));

        }


    }

    @Override
    public void dispose() {
        assetManager.dispose();
    }
}
