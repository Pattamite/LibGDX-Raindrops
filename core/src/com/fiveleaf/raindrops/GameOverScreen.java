package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameOverScreen extends ScreenAdapter{
	
	public SpriteBatch batch;
	private RaindropsGame raindropsGame;
	private BitmapFont font;
	private OrthographicCamera camera;
	
	private Music gameOverMusic;
	
	private long blinkFreq = 200;
	private boolean isBlinking = true;
	private long lastBlink;
	
	
	public GameOverScreen(RaindropsGame raindropsGame){
		this.batch = raindropsGame.batch;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 160, 144);
		this.raindropsGame = raindropsGame;
		font = new BitmapFont(Gdx.files.internal("assets/GameBoy_S10.fnt"));
		font.setColor(15, 56, 15, 1);
		lastBlink = TimeUtils.millis();
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
		font.draw(raindropsGame.batch, "Game Over", 50, 130);
		font.draw(raindropsGame.batch, "Time  :" + (int)(raindropsGame.time) ,17 , 115);
		font.draw(raindropsGame.batch, "Score :" + raindropsGame.score ,17 , 100);
		font.draw(raindropsGame.batch, "High Score :" + raindropsGame.highScore ,17 , 85);
		
		if(raindropsGame.isNewHightScore)
		{
			Blinking();
			if(isBlinking)font.draw(raindropsGame.batch, "!! New High Score !!"  ,10 , 70);
		}
		font.draw(raindropsGame.batch, "Press Spacebar",26 ,55);
		font.draw(raindropsGame.batch, "To replay",47 ,45);
		font.draw(raindropsGame.batch, "Press Up or W", 33, 30);
		font.draw(raindropsGame.batch, "To go to Main Menu", 16, 20);
		batch.end();
		
		CheckInput();
	}
	
	private void Blinking(){
		if(TimeUtils.millis() - lastBlink > blinkFreq){
			lastBlink = TimeUtils.millis();
			isBlinking = !isBlinking;
		}
	}
	
	private void CheckInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			raindropsGame.PlayGame();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)){
			raindropsGame.MainMenu();
		}
	}
}
