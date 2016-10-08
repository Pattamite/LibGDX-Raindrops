package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Cacti {
	private Texture cactusImage;
	public Array<Rectangle> cactiRectangle;
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	public Cacti(RaindropsGame raindropsGame, GameScreen gameScreen)
	{
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		cactusImage = new Texture(Gdx.files.internal("Raindrops_Cactus.png"));
		cactiRectangle = new Array<Rectangle>();
		
		placeCacti();
	}
	
	private void placeCacti() {
	     Rectangle cactus = new Rectangle();
	     cactus.x = (160 / 2) - (52 + 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) - (32 / 2);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) + (52 - 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);       
	 }
	
	public void draw()
	{
		for(Rectangle cactus: cactiRectangle) {
			gameScreen.batch.draw(cactusImage, cactus.x, cactus.y);
        }
	}
}
