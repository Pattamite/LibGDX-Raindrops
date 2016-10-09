package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

public class Fertilizer {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	private Texture fertilizerImage;
	public Rectangle fertilizer;
	
	public Fertilizer(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		fertilizerImage = new Texture(Gdx.files.internal("Raindrops_Fertilizer.png"));
	}
	
	public void spawnFertilizer(float playerXPosition){
	    fertilizer = new Rectangle();
	    fertilizer.x = playerXPosition + 16;
	    fertilizer.y = gameScreen.umbrella.umbrellaRactangle.getY() + 16;
	    fertilizer.width = 16;
	    fertilizer.height = 16;
	}
	
	public void draw()
	{
	    if(fertilizer != null){
            gameScreen.batch.draw(fertilizerImage, (int)fertilizer.x, (int)fertilizer.y);
	    }
	}
}
