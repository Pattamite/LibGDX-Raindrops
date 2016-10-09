package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

public class FertBar {
	private Umbrella umbrella;
	private GameScreen gameScreen;
	
	private Texture fertBar;
	private Texture remainFert;
	private Texture remainFertBelowCost;
	
	private float maxValue = 100;
	private float currentValue;
	private float costValue = 50;
	private float regenValue = 4;
	
	private float yPositionOffset = -19f;
	
	public FertBar(Umbrella umbrella, GameScreen gameScreen)
	{
		this.umbrella = umbrella;
		this.gameScreen = gameScreen;
		
		fertBar = new Texture(Gdx.files.internal("assets/HealthBar_0.png"));
		remainFert = new Texture(Gdx.files.internal("assets/RemainHealth.png"));
		remainFertBelowCost = new Texture(Gdx.files.internal("assets/RemainFertBelowCost.png"));
		currentValue = maxValue;
	}
	
	public void draw(float delta){
		currentValue += (regenValue * delta);
		if(currentValue > maxValue) currentValue = maxValue;
		
		gameScreen.batch.draw(fertBar, umbrella.umbrellaRactangle.x, 
			umbrella.umbrellaRactangle.y + yPositionOffset);
		
		if(currentValue >= costValue)
		{
			gameScreen.batch.draw(remainFert, umbrella.umbrellaRactangle.x + 6, 
				umbrella.umbrellaRactangle.y + yPositionOffset + 15,
				20 * currentValue / maxValue , 2);
		}
		else
		{
			gameScreen.batch.draw(remainFertBelowCost, umbrella.umbrellaRactangle.x + 6, 
					umbrella.umbrellaRactangle.y + yPositionOffset + 15,
					20 * currentValue / maxValue , 2);
		}
	}
	
	public boolean iscanDrop()
	{
		return currentValue >= costValue;
	}
	
	public void dropFert()
	{
		currentValue -= costValue;
	}
}
