package com.fiveleaf.raindrops;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class RainDropLastFrame {
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	
	private Texture rainDropEffectImage;
	public Array<RainDropLastFrameInfo> infoArray;
	
	private long showTime = 300;
	
	public RainDropLastFrame(RaindropsGame raindropsGame, GameScreen gameScreen){
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		rainDropEffectImage = new Texture(Gdx.files.internal("assets/NewSprite/rainlastframe.png"));
		infoArray = new Array<RainDropLastFrameInfo>();
	}
	
	public void addNewRDLF(float x,float y)
	{
		infoArray.add(new RainDropLastFrameInfo(TimeUtils.millis(), x, y));
	}
	
	public void draw()
	{
		Iterator<RainDropLastFrameInfo> iterInfo = infoArray.iterator();

		while(iterInfo.hasNext())
		{
			RainDropLastFrameInfo object = iterInfo.next();
			if(TimeUtils.millis() - object.spawnTime > showTime) iterInfo.remove();
			else gameScreen.batch.draw(rainDropEffectImage, object.xPosition, object.yPosition);
		}
		
	}
}
