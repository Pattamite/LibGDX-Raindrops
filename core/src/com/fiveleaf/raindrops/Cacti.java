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
	
	public static int STARTING_Y = 16;
	
	private Texture cactusImage;
	public Array<Rectangle> cactiRectangle;
	public int cactusStatus[];
	public int cactusHealth[];
	public int needRain[];
	public int needFert[];
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
		
		
		setUpArray();
		placeCacti();
	}
	
	private void setUpArray()
	{
		cactusHealth = new int[3];
		cactusHealth[0] = cactusMaxHealth;
		cactusHealth[1] = cactusMaxHealth;
		cactusHealth[2] = cactusMaxHealth;
		
		cactusStatus = new int[3];
		cactusStatus[0] = STATUS_NORMAL;
		cactusStatus[1] = STATUS_NORMAL;
		cactusStatus[2] = STATUS_NORMAL;
		
		needRain = new int[3];
		needRain[0] = 0;
		needRain[1] = 0;
		needRain[2] = 0;
		
		needFert = new int[3];
		needFert[0] = 0;
		needFert[1] = 0;
		needFert[2] = 0;
	}
	
	private void placeCacti() {
	    Rectangle cactus = new Rectangle();
	    cactus.x = (160 / 2) - (52 + 16);
	    cactus.y = STARTING_Y;
	    cactus.width = 32;
	    cactus.height = 32;
	    cactiRectangle.add(cactus);
	    cactus = new Rectangle();
	    cactus.x = (160 / 2) - (32 / 2);
	    cactus.y = STARTING_Y;
	    cactus.width = 32;
	    cactus.height = 32;
	    cactiRectangle.add(cactus);
	    cactus = new Rectangle();
	    cactus.x = (160 / 2) + (52 - 16);
	    cactus.y = STARTING_Y;
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
	
	public void rainHit(int target)
	{
		if(cactusStatus[target] == STATUS_NORMAL || cactusStatus[target] == STATUS_NEEDFERT)
		{
			cactusHealth[target]--;
			if(cactusHealth[target] <= 0)
			{
				cactusDead(target);
			}
		}
		else if(cactusStatus[target] == STATUS_NEEDRAIN)
		{
			needRain[target]--;
			if(needRain[target] <= 0)
			{
				setStatus(target, STATUS_NORMAL, 0);
			}
		}
	}
	
	public void fertHit(int target)
	{
		if(false) // DebugCheck
		//if(cactusStatus[target] == STATUS_NORMAL || cactusStatus[target] == STATUS_NEEDRAIN) // RealGameCheck
		{
			cactusHealth[target] = healthCheck(cactusHealth[target] - 1);
			if(cactusHealth[target] <= 0)
			{
				cactusDead(target);
			}
		}
		else // DebugCheck
		//else if(cactusStatus[target] == STATUS_NEEDFERT)
		{
			needFert[target] = 0;
			cactusHealth[target] = healthCheck(cactusHealth[target] + 3);
			setStatus(target, STATUS_NORMAL, 0);
		}
	}
	
	private void cactusDead(int target)
	{
		cactusStatus[target] = STATUS_DEAD;
		cactiRectangle.get(target).width = 0;
		cactiRectangle.get(target).height = 0;
		cactiRectangle.get(target).y = -100;
	}
	
	public void setStatus(int target, int status, int value)
	{
		if(cactusStatus[target] != STATUS_DEAD)
		{
			cactusStatus[target] = status;
			if(status == STATUS_NEEDRAIN) setNeedRain(target, value);
			else if(status == STATUS_NEEDFERT) setNeedFert(target, value);
		}
		
	}
	public int healthCheck(int health){
		if(health > cactusMaxHealth){
			return cactusMaxHealth;
		}
		else if(health <= 0){
			return -10;
		}
		else {
			return health;
		}
	}
	
	public void setNeedRain(int target, int value)
	{
		if(cactusStatus[target] == STATUS_NEEDRAIN)
		{
			needRain[target] = value;
		}
		else
		{
			System.out.println("ERROR : Cannot set cactus's NeedRain -> cactusStatus is not STATUS_NEEDRAIN");
		}
	}
	
	public void setNeedFert(int target, int value)
	{
		if(cactusStatus[target] == STATUS_NEEDFERT)
		{
			needFert[target] = value;
		}
		else
		{
			System.out.println("ERROR : Cannot set cactus's NeedFert -> cactusStatus is not STATUS_NEEDFERT");
		}
	}
}
