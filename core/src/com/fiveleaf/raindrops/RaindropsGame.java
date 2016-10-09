package com.fiveleaf.raindrops;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class RaindropsGame extends Game {
   
    public SpriteBatch batch;
    public int highScore = 0;
    public int score = 0;
    public boolean isNewHightScore = true;
    
    @Override
    public void create() {
    	
        batch = new SpriteBatch();
        setScreen(new MainScreen(this));
    }
    
    @Override
    public void render() {
        super.render();
    }
    
    @Override
    public void dispose() {
        batch.dispose();
    }
    
    public void GameOver(int score){
    	this.score = score;
    	if(score > highScore){
    		isNewHightScore = true;
    		highScore = score;
    	}
    	else{
    		isNewHightScore = false;
    	}
    	setScreen(new GameOverScreen(this));
    }
    
    public void PlayGame(){
    	setScreen(new GameScreen(this));
    }
    
    public void HowToPlay(){
    	setScreen(new HowToPlayScreen(this));
    }
    
    public void MainMenu()
    {
    	setScreen(new MainScreen(this));
    }
}