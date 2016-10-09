package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class WaterLevel {
    
    private RaindropsGame raindropsGame;
    private GameScreen gameScreen;
    private Texture waterTexture;
    private Rectangle waterRectangle;
    public int wave = 0;
    
    public WaterLevel (RaindropsGame raindropsGame, GameScreen gameScreen) {
        this.raindropsGame = raindropsGame;
        this.gameScreen = gameScreen;
        waterTexture = new Texture(Gdx.files.internal("Raindrops_WaterTexture.png"));
        waterRectangle = new Rectangle();
    }
    
    public int getWaterLevel () {
        int level = gameScreen.rainDropsCount / 2;
        if(level > gameScreen.MAX_WATERLEVEL){
            return gameScreen.MAX_WATERLEVEL;
        }
        return level;
    }
    public void draw() {
        if(MathUtils.randomBoolean((float)(this.getWaterLevel() * 0.005))){
            calcWave();
        }
        waterRectangle.x = waterRectangle.getX() + (wave * Gdx.graphics.getDeltaTime());
        waterRectangle.y = (this.getWaterLevel() - 160) * Gdx.graphics.getDeltaTime();
        gameScreen.batch.draw(waterTexture, (int)(-40+(wave/1.5)), (int)(this.getWaterLevel()) - 160);
    }
    public void calcWave() {
        wave = MathUtils.random(-(this.getWaterLevel() / 2), (this.getWaterLevel() / 2));
    }
    public int moveCactus() {
        return MathUtils.random(-5, 15) * wave;
    }
}
