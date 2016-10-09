package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameOverScreen extends ScreenAdapter{
	
	public SpriteBatch batch;
	private Music gameOverMusic;
	private RaindropsGame raindropsGame;
	private BitmapFont font;
	private OrthographicCamera camera;
	private long blinkFreq = 300;
	private boolean isBlinking = true;
	private long lastBlink;
	
	public GameOverScreen(RaindropsGame raindropsGame)
	{
		this.batch = raindropsGame.batch;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 160, 144);
		this.raindropsGame = raindropsGame;
		font = new BitmapFont(Gdx.files.internal("GameBoy_S10.fnt"));
		lastBlink = TimeUtils.millis();
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
		font.draw(raindropsGame.batch, "Game Over", 50, 110);
		font.draw(raindropsGame.batch, "Score :" + raindropsGame.highScore ,17 , 90);
		font.draw(raindropsGame.batch, "High Score :" + raindropsGame.highScore ,17 , 75);
		if(raindropsGame.isNewHightScore)
		{
			Blinking();
			if(isBlinking)font.draw(raindropsGame.batch, "!! New High Score !!"  ,10 , 55);
		}
		batch.end();  
	}
	
	private void Blinking()
	{
		if(TimeUtils.millis() - lastBlink > blinkFreq)
		{
			lastBlink = TimeUtils.millis();
			isBlinking = !isBlinking;
		}
	}
}
