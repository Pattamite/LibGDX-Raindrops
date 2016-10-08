package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class CactiHealthBar {
	private Texture healthBar[];
	
	private Cacti cacti;
	private GameScreen gameScreen;
	
	private int counter;
	private float yPositionOffset;
	private int tempHealth;
	
	public CactiHealthBar( Cacti cacti, GameScreen gameScreen)
	{
		this.cacti = cacti;
		this.gameScreen = gameScreen;
		
		healthBar = new Texture[6];
		healthBar[0] = new Texture(Gdx.files.internal("HealthBar_0.png"));
		healthBar[1] = new Texture(Gdx.files.internal("HealthBar_1.png"));
		healthBar[2] = new Texture(Gdx.files.internal("HealthBar_2.png"));
		healthBar[3] = new Texture(Gdx.files.internal("HealthBar_3.png"));
		healthBar[4] = new Texture(Gdx.files.internal("HealthBar_4.png"));
		healthBar[5] = new Texture(Gdx.files.internal("HealthBar_5.png"));
		
		yPositionOffset = -19f;
	}
	
	public void draw()
	{
		for(counter = 0; counter < 3 ; counter++)
		{
			tempHealth = cacti.cactusHealth[counter];
			if(tempHealth < 0) tempHealth = 0;
			else if(tempHealth > cacti.cactusMaxHealth) tempHealth = cacti.cactusMaxHealth;
			gameScreen.batch.draw(healthBar[tempHealth], cacti.cactiRectangle.get(counter).x, cacti.cactiRectangle.get(counter).y + yPositionOffset);
		}
	}
}
