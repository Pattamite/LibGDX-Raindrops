package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HowToPlayScreen extends ScreenAdapter{
	public SpriteBatch batch;
	private RaindropsGame raindropsGame;
	private BitmapFont font;
	private OrthographicCamera camera;
	
	public HowToPlayScreen(RaindropsGame raindropsGame){
		this.batch = raindropsGame.batch;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 160, 144);
		this.raindropsGame = raindropsGame;
		font = new BitmapFont(Gdx.files.internal("assets/GameBoy_S10.fnt"));
		font.setColor(15, 56, 15, 1);
		
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    font.draw(raindropsGame.batch, "~ Press Left arrow", 10, 140);
	    font.draw(raindropsGame.batch, "or 'A' to move left", 22, 130);
	    font.draw(raindropsGame.batch, "~ Press Right arrow", 10, 115);
	    font.draw(raindropsGame.batch, "or 'D' to move left", 22, 105);
	    font.draw(raindropsGame.batch, "~ Press Down arrow", 10, 90);
	    font.draw(raindropsGame.batch, "or 's' to drop", 22, 80);
	    font.draw(raindropsGame.batch, "fertilizer", 22, 70);
	    font.draw(raindropsGame.batch, "~ Move Umbrella to", 10, 55);
	    font.draw(raindropsGame.batch, "Block raindrops", 22, 45);
	    font.draw(raindropsGame.batch, "as much as you can", 22, 35);
	    font.draw(raindropsGame.batch, "Press Spacebar", 26, 20);
		font.draw(raindropsGame.batch, "To Play", 55, 10);
		
	    batch.end();
		
		CheckInput();
	}
	
	private void CheckInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			raindropsGame.PlayGame();
		}
	}
	
}
