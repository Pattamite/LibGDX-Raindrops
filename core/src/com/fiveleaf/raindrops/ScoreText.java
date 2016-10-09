package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class ScoreText {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	private BitmapFont font;
	
	
	public ScoreText(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		font = new BitmapFont(Gdx.files.internal("assets/GameBoy_S10.fnt"));
		font.setColor(15, 56, 15, 1);
	}
	
	public void draw(){
		font.draw(gameScreen.batch, "Score : " + gameScreen.score , 2, 7);
	}
}
