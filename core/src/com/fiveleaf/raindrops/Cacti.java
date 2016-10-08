package com.fiveleaf.raindrops;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class Cacti {
	
	public static int STATUS_NORMAL = 0;
	public static int STATUS_NEEDRAIN = 1;
	public static int STATUS_NEEDFERT = 2;
	public static int STATUS_INVINCIBLE = -1;
	public static int STATUS_DEAD = -2;
	
	private Texture cactusImage;
	public Array<Rectangle> cactiRectangle;
	public int cactusStatus[];
	public int cactusHealth[];
	private RaindropsGame raindropsGame;
	private GameScreen gameScreen;
	public int cactusMaxHealth = 5;
	private int counter;
	
	public Cacti(RaindropsGame raindropsGame, GameScreen gameScreen)
	{
		this.raindropsGame = raindropsGame;
		this.gameScreen = gameScreen;
		cactusImage = new Texture(Gdx.files.internal("Raindrops_Cactus.png"));
		cactiRectangle = new Array<Rectangle>();
		cactusHealth = new int[3];
		cactusHealth[0] = cactusMaxHealth;
		cactusHealth[1] = cactusMaxHealth;
		cactusHealth[2] = cactusMaxHealth;
		
		cactusStatus = new int[3];
		cactusStatus[0] = STATUS_NORMAL;
		cactusStatus[1] = STATUS_NORMAL;
		cactusStatus[2] = STATUS_NORMAL;
		
		placeCacti();
	}
	
	private void placeCacti() {
	     Rectangle cactus = new Rectangle();
	     cactus.x = (160 / 2) - (52 + 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) - (32 / 2);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) + (52 - 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cactiRectangle.add(cactus);       
	 }
	
	public void draw()
	{
		counter = 0;
		for(Rectangle cactus: cactiRectangle) {
			if(cactusStatus[counter] != STATUS_DEAD)
			{
				gameScreen.batch.draw(cactusImage, cactus.x, cactus.y);
			}
			counter++;
        }
	}
	
	public void hit(int target)
	{
		if(cactusStatus[target] == STATUS_NORMAL || cactusStatus[target] == STATUS_NEEDFERT)
		{
			cactusHealth[target]--;
			if(cactusHealth[target] <= 0)
			{
				cactusDead(target);
			}
		}
	}
	
	private void cactusDead(int target)
	{
		cactusStatus[target] = STATUS_DEAD;
		cactiRectangle.get(target).width = 0;
		cactiRectangle.get(target).height = 0;
		cactiRectangle.get(target).x = -1;
		cactiRectangle.get(target).y = -1;
	}
}
