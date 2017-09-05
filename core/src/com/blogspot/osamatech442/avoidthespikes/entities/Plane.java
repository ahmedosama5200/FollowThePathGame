package com.blogspot.osamatech442.avoidthespikes.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.blogspot.osamatech442.avoidthespikes.utils.Constants;
import com.sun.org.apache.bcel.internal.generic.FLOAD;

public class Plane {

    private Rectangle bounds;
    private Vector2 velocity;
    private Animation<TextureRegion> planeAnimation;
    private float elapsedTime;
    private float deathPos;

    public Plane(Animation<TextureRegion> planeAnimation) {
        this.bounds = new Rectangle();
        this.velocity = new Vector2(Constants.PLANE_XVELOCITY, 0);

        this.planeAnimation = planeAnimation;

        this.elapsedTime = 0;
        this.deathPos = 0;

    }

    public void init(Viewport viewport) {
        this.bounds.set(
                Constants.PLANE_INITIAL_XPOS,
                viewport.getWorldHeight() / 2 - Constants.PLANE_HEIGHT / 2,
                Constants.PLANE_WIDTH,
                Constants.PLANE_HEIGHT
        );
    }

    private void jump() {
        velocity.y = Constants.PLANE_JUMP_VELOCITY;
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            jump();
        }
    }

    private void applyGravity(float delta) {
        velocity.y += -Constants.GRAVITY * delta;
    }

    private void move(float delta) {
        bounds.x += velocity.x * delta;
        bounds.y += velocity.y * delta;

    }

    private void handleGround(Viewport viewport) {
        if (bounds.y < Constants.GROUND_HEIGHT) {
            bounds.y = Constants.GROUND_HEIGHT;
        }
        else if (bounds.y > viewport.getWorldHeight() - bounds.height) {
            bounds.y = viewport.getWorldHeight() - bounds.height;
        }
    }

    public void stepBack(float delta) {
        bounds.x -= Constants.PLANE_BACK_VELOCITY * delta;
    }

    public float getDeathPos() {
        return deathPos;
    }

    public void setDeathPos(float deathPos) {
        this.deathPos = deathPos;
    }

    public void update(float delta, Viewport viewport) {
        handleInput();
        applyGravity(delta);
        move(delta);
        handleGround(viewport);
        elapsedTime += delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                planeAnimation.getKeyFrame(elapsedTime, true),
                bounds.x,
                bounds.y,
                bounds.width,
                bounds.height
        );
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
