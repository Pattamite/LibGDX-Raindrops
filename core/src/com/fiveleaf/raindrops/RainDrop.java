package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class RainDrop {
	private Texture rainDropImage;
	public Array<Rectangle> raindropsRactangle;
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	public RainDrop(RaindropsGame raindropsGame, GameScreen gameScreen)
	{
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		rainDropImage = new Texture(Gdx.files.internal("Raindrops_Rain.png"));
		raindropsRactangle = new Array<Rectangle>();
	}
	
	public void spawnRaindrop() {
	     Rectangle raindrop = new Rectangle();
	     raindrop.x = MathUtils.random(0, 160-16);
	     raindrop.y = 144;
	     raindrop.width = 8;
	     raindrop.height = 8;
	     raindropsRactangle.add(raindrop);
	 }
	public void draw()
	{
		for(Rectangle raindrop: raindropsRactangle) {
            gameScreen.batch.draw(rainDropImage, raindrop.x, raindrop.y);
        }
	}
}
