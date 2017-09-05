package com.blogspot.osamatech442.avoidthespikes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.AvoidTheSpikesGame;
import com.blogspot.osamatech442.avoidthespikes.entities.Ground;
import com.blogspot.osamatech442.avoidthespikes.entities.Plane;
import com.blogspot.osamatech442.avoidthespikes.utils.Assets;
import com.blogspot.osamatech442.avoidthespikes.utils.CoinCreator;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;

public class PlayScreen implements Screen {

    private AvoidTheSpikesGame avoidTheSpikesGame;
    private Assets assets;
    private SpriteBatch batch;
    private Viewport viewport;
    private Viewport hudViewPort;

    private Plane plane;
    private Ground ground;
    private CoinCreator coinCreator;

    private boolean isFirstResized;
    private boolean isGameover;

    public PlayScreen(AvoidTheSpikesGame avoidTheSpikesGame) {
        this.avoidTheSpikesGame = avoidTheSpikesGame;
        this.batch = avoidTheSpikesGame.batch;
        this.assets = avoidTheSpikesGame.assets;

        this.viewport = new ExtendViewport(Constants.PLAY_SCREEN_SIZE, Constants.PLAY_SCREEN_SIZE);
        this.hudViewPort = new ExtendViewport(Constants.PLAY_SCREEN_SIZE, Constants.PLAY_SCREEN_SIZE);

        this.plane = new Plane(assets.planeAssets.redPlaneAnimation);
        this.ground = new Ground(assets.decorationAssets.ground);
        this.coinCreator = new CoinCreator(assets.componentsAssets.coin, viewport);

        this.isFirstResized = true;
        this.isGameover = false;
    }

    @Override
    public void show() {

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, isFirstResized);
        hudViewPort.update(width, height, true);
        if (isFirstResized) {
            plane.init(viewport);
            trackPlane();
            coinCreator.init();
        }
        isFirstResized = false;
    }

    @Override
    public void render(float delta) {
        clearScreen(Constants.PLAY_SCREEN_COLOR);
        if (isGameover) {
            plane.stepBack(delta);
            trackPlane();
            if (plane.getDeathPos() - plane.getBounds().x >= viewport.getWorldWidth()) {
                isGameover = false;
                coinCreator = new CoinCreator(assets.componentsAssets.coin, viewport);
                coinCreator.init();
            }
        } else {
            updateScreen(delta);
        }

        renderScreen();
    }

    private void clearScreen(Color color) {
        Gdx.gl.glClearColor(color.r, color.g, color.b, color.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    private void updateScreen(float delta) {
        plane.update(delta, viewport);
        trackPlane();
        coinCreator.update(plane);
    }

    private void trackPlane() {
        viewport.getCamera().position.x = plane.getBounds().x + viewport.getWorldWidth() / 2 - Constants.PLANE_INITIAL_XPOS;
        viewport.apply();
    }

    private void renderScreen() {
        batch.setProjectionMatrix(hudViewPort.getCamera().combined);
        batch.begin();
        ground.render(batch, hudViewPort.getWorldWidth());
        batch.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        coinCreator.render(batch);
        plane.render(batch);
        batch.end();
    }

    public void setGameover(boolean gameover) {
        isGameover = gameover;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
