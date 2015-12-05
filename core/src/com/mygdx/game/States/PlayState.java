package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.Sprites.Bat;
import com.mygdx.game.Sprites.Spike;

/**
 * Created by SeanB on 12/3/2015.
 */
public class PlayState extends State{
    private static final int SPIKE_SPACING = 125;
    private static final int SPIKE_COUNT = 4;
    private Bat bat;
    private Texture bg;
    private Array<Spike> spikes;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        bat = new Bat(50, 300);
        cam.setToOrtho(false, MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT/2);
        bg = new Texture("bg.png");
        spikes = new Array<Spike>();

        for(int i = 1; i <= SPIKE_COUNT; i++) {
            spikes.add(new Spike(i * (SPIKE_SPACING + Spike.SPIKE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()) {
            bat.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        bat.update(dt);
        cam.position.x = bat.getPosition().x + 80;

        for(Spike spike : spikes) {
            if(cam.position.x - (cam.viewportWidth/2)>spike.getPosTopSpike().x + spike.getTopSpike().getWidth()) {
                spike.reposition(spike.getPosTopSpike().x  + ((Spike.SPIKE_WIDTH + SPIKE_SPACING) * SPIKE_COUNT));
            }
            if(spike.collides(bat.getBounds()))
                gsm.set(new PlayState(gsm));
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
        sb.draw(bat.getTexture(), bat.getPosition().x, bat.getPosition().y);
        for (Spike spike : spikes) {
            sb.draw(spike.getTopSpike(), spike.getPosTopSpike().x, spike.getPosTopSpike().y);
            sb.draw(spike.getBottomSpike(), spike.getPosBotSpike().x, spike.getPosBotSpike().y);
        }
        sb.end();

    }

    @Override
    public void dispose() {
        bg.dispose();
        bat.dispose();
        for(Spike spike:spikes)
            spike.dispose();
    }
}
