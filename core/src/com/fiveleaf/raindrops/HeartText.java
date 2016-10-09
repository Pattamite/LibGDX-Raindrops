package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HeartText {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	private BitmapFont font;
	private Texture heartImg;
	
	public HeartText(RaindropsGame raindropsGame, GameScreen gameScreen)
	{
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		font = new BitmapFont(Gdx.files.internal("GameBoy_S10.fnt"));
		heartImg = new Texture(Gdx.files.internal("Heart.png"));
	}
	
	public void draw()
	{
		font.setColor(15, 56, 15, 1);
		font.draw(gameScreen.batch, "x " + gameScreen.heart , 140, 7);
		gameScreen.batch.draw(heartImg, 125, 0);
	}
}
