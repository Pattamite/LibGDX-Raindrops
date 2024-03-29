package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Umbrella {
	private Texture umbrellaImage;
	public Rectangle umbrellaRactangle;
	private int umbrellaPosition;
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	public static int UMBRELLA_HEIGHT = 56;
	
	public Umbrella(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		umbrellaImage = new Texture(Gdx.files.internal("assets/NewSprite/umbrella.png"));
		
		umbrellaRactangle = new Rectangle();
	    umbrellaRactangle.x = (160 / 2) - (32 / 2);
	    umbrellaRactangle.y = UMBRELLA_HEIGHT;
	    umbrellaRactangle.width = 32;
	    umbrellaRactangle.height = 32;
	     
	    umbrellaPosition = 2;
	}
	
	public void draw(){
		gameScreen.batch.draw(umbrellaImage, (int)umbrellaRactangle.x, (int)umbrellaRactangle.y);
	}
}
