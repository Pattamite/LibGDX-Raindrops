package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.TimeUtils;

public class HeartText {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	private BitmapFont font;
	private Texture heartImg;
	
	private boolean isBlinkActivate = false;
	private boolean isBlink = true;
	private long blinkingFreq = 70;
	private long blinkTime = 1500;
	private long lastBlink;
	private long lastActivate;
	
	public HeartText(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		font = new BitmapFont(Gdx.files.internal("assets/GameBoy_S10.fnt"));
		heartImg = new Texture(Gdx.files.internal("assets/Heart.png"));
		font.setColor(15, 56, 15, 1);
	}
	
	public void draw(){
		
		font.draw(gameScreen.batch, "x " + gameScreen.heart , 140, 7);
		if(isBlinkActivate)
		{
			Blink();
			if(isBlink) gameScreen.batch.draw(heartImg, 125, 0);
		}
		else gameScreen.batch.draw(heartImg, 125, 0);
	}
	
	
	public void ActivateBlink(){
		isBlinkActivate = true;
		lastActivate = TimeUtils.millis();
		lastBlink = TimeUtils.millis();
	}
	
	private void Blink(){
		if(TimeUtils.millis() - lastBlink > blinkingFreq){
			lastBlink = TimeUtils.millis();
			isBlink = !isBlink;
		}
		if(TimeUtils.millis() - lastActivate > blinkTime){
			isBlink = true;
			isBlinkActivate = false;
		}
	}
}
