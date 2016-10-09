package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CactiHealthBar {
	private Texture healthBar;
	private Texture remainHealth;
	
	private Cacti cacti;
	private GameScreen gameScreen;
	
	private int counter;
	private float yPositionOffset;
	private int tempHealth;
	
	public CactiHealthBar( Cacti cacti, GameScreen gameScreen)
	{
		this.cacti = cacti;
		this.gameScreen = gameScreen;
		
		healthBar = new Texture(Gdx.files.internal("HealthBar_0.png"));
		remainHealth = new Texture(Gdx.files.internal("RemainHealth.png"));
		
		yPositionOffset = -19f;
	}
	
	public void draw()
	{
		for(counter = 0; counter < 3 ; counter++)
		{
			tempHealth = cacti.cactusHealth[counter];
			if(tempHealth < 0) tempHealth = 0;
			else if(tempHealth > cacti.cactusMaxHealth) tempHealth = cacti.cactusMaxHealth;
			
			gameScreen.batch.draw(healthBar, cacti.cactiRectangle.get(counter).x, 
					cacti.cactiRectangle.get(counter).y + yPositionOffset);
			gameScreen.batch.draw(remainHealth, cacti.cactiRectangle.get(counter).x + 6, 
					cacti.cactiRectangle.get(counter).y + yPositionOffset + 15,
					 20 * tempHealth / cacti.cactusMaxHealth , 2);
		}
	}
}
