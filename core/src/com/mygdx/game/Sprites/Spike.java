package com.mygdx.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

/**
 * Created by SeanB on 12/4/2015.
 */
public class Spike {
    public static final int SPIKE_WIDTH = 52;
    private static final int FLUCTUATION = 130;
    private static final int SPIKE_GAP = 100;
    private static final int LOWEST_OPENING = 120;
    private Texture topSpike, bottomSpike;
    private Vector2 posTopSpike, posBotSpike;
    private Rectangle boundsTop, boundsBot;
    private Random rand;

    public Spike(float x){
        topSpike = new Texture("topSpike.png");
        bottomSpike = new Texture("bottomSpike.png");
        rand = new Random();

        posTopSpike = new Vector2(x, rand.nextInt(FLUCTUATION) + SPIKE_GAP + LOWEST_OPENING);
        posBotSpike = new Vector2(x, posTopSpike.y - SPIKE_GAP - bottomSpike.getHeight());

        boundsTop = new Rectangle(posTopSpike.x, posTopSpike.y, topSpike.getWidth(), topSpike.getHeight());
        boundsBot = new Rectangle(posBotSpike.x, posBotSpike.y, bottomSpike.getWidth(), bottomSpike.getHeight());
    }

    public Texture getTopSpike() {
        return topSpike;
    }

    public Texture getBottomSpike() {
        return bottomSpike;
    }

    public Vector2 getPosTopSpike() {
        return posTopSpike;
    }

    public Vector2 getPosBotSpike() {
        return posBotSpike;
    }

    public void reposition(float x) {
        posTopSpike.set(x, rand.nextInt(FLUCTUATION) + SPIKE_GAP + LOWEST_OPENING);
        posBotSpike.set(x, posTopSpike.y - SPIKE_GAP - bottomSpike.getHeight());
        boundsTop.setPosition(posTopSpike.x, posTopSpike.y);
        boundsBot.setPosition(posBotSpike.x, posBotSpike.y);
    }

    public boolean collides(Rectangle player) {
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topSpike.dispose();
        bottomSpike.dispose();
    }
}
