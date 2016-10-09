package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends ScreenAdapter{
	public SpriteBatch batch;
	private RaindropsGame raindropsGame;
	private BitmapFont font;
	private BitmapFont font2;
	private OrthographicCamera camera;
	
	public MainScreen(RaindropsGame raindropsGame){
		this.batch = raindropsGame.batch;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 160, 144);
		this.raindropsGame = raindropsGame;
		font = new BitmapFont(Gdx.files.internal("assets/GameBoy_S10.fnt"));
		font2 = new BitmapFont(Gdx.files.internal("assets/GameBoy.fnt"));
		font.setColor(15, 56, 15, 1);
		
	}
	
	@Override
	public void render(float delta){
		Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	    camera.update();
	    
	    
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    font.draw(raindropsGame.batch, "Raindrops Keep ", 27, 130);
	    font.draw(raindropsGame.batch, "Fallin' on", 45, 115);
	    font.draw(raindropsGame.batch, "My Plants!", 45, 100);
	    font.draw(raindropsGame.batch, "Press Spacebar", 26, 70);
		font.draw(raindropsGame.batch, "To Play", 55, 60);
		font.draw(raindropsGame.batch, "Press Down or S", 26, 35);
		font.draw(raindropsGame.batch, "To See How to Play", 15, 25);
		font2.draw(raindropsGame.batch, "5-leaf-clover studio", 22, 10);
	    batch.end();
		
		CheckInput();
	}
	
	private void CheckInput(){
		if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
			raindropsGame.PlayGame();
		}
		else if(Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S))
		{
			raindropsGame.HowToPlay();
		}
	}
}
