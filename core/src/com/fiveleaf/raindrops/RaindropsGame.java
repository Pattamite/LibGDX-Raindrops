package com.fiveleaf.raindrops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RaindropsGame extends Game {
   
    public SpriteBatch batch;
    public int highScore = 0;
    public int score = 0;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new GameScreen(this));
    }
    
    @Override
    public void render() {
        super.render();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
    
}