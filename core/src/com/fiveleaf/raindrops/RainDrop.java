package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class RainDrop {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	private Texture rainDropImage;
	public Array<Rectangle> raindropsRactangle;
	
	private float randomPosition;
	private int lastPosition;
	private int streak = 0;
	private int maxStreak = 3;
	
	public RainDrop(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		rainDropImage = new Texture(Gdx.files.internal("Raindrops_Rain.png"));
		raindropsRactangle = new Array<Rectangle>();
	}
	
	public void spawnRaindrop() {
		randomPosition = randomCactiPosition();
		
	    Rectangle raindrop = new Rectangle();
	    raindrop.x = MathUtils.random(-8, 24) + randomPosition;
	    raindrop.y = 144;
	    raindrop.width = 8;
	    raindrop.height = 8;
	    raindropsRactangle.add(raindrop);
	 }
	public void draw(){
		for(Rectangle raindrop: raindropsRactangle) {
            gameScreen.batch.draw(rainDropImage, raindrop.x, raindrop.y);
        }
	}
	
	private float randomCactiPosition(){
		float position = MathUtils.random(0, 3);
		if(position < 1f){
			if(lastPosition == 1) streak++;
			else streak = 1;
			if(streak < maxStreak){
				lastPosition = 1;
				return 12f;
			}
			else{
				if(position < 0.5f){
					streak = 1;
					lastPosition = 2;
					return 64f;
				}
				else{
					streak = 1;
					lastPosition = 3;
					return 116f;
				}
			}
		}
		else if(position < 2f){
			if(lastPosition == 2) streak++;
			else streak = 1;
			if(streak < maxStreak){
				lastPosition = 2;
				return 64f;
			}
			else{
				if(position < 1.5f){
					streak = 1;
					lastPosition = 1;
					return 12f;
				}
				else{
					streak = 1;
					lastPosition = 3;
					return 116f;
				}
			}
		}
		else{
			if(lastPosition == 3) streak++;
			else streak = 1;
			if(streak < maxStreak){
				lastPosition = 3;
				return 116f;
			}
			else{
				if(position < 2.5f){
					streak = 1;
					lastPosition = 1;
					return 12f;
				}
				else{
					streak = 1;
					lastPosition = 2;
					return 64f;
				}
			}
		}
	}
}
