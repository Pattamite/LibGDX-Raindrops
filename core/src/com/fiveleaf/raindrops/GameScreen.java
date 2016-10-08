package com.fiveleaf.raindrops;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends ScreenAdapter{
	 private OrthographicCamera camera;
	 private Texture umbrellaImage;
	 private Texture cactusImage;
	 private Sound rainToCactiSound;
	 private Sound rainToUmbrellaSound;
	 private Music raindropsMusic;
	 private Rectangle umbrella;
	 private int umbrellaPosition;
	 private Array<Rectangle> cacti;
	 public SpriteBatch batch;
	 private RaindropsGame raindropsGame;
	 private RainDrop rainDrop;
	 private long lastDropTime;
	    
	 public GameScreen(RaindropsGame raindropsGame) {
		 this.raindropsGame = raindropsGame;
		 this.batch = raindropsGame.batch;
		 rainDrop = new RainDrop(this.raindropsGame, this);
		 // Texture
	     
	     umbrellaImage = new Texture(Gdx.files.internal("Raindrops_Umbrella.png"));
	     cactusImage = new Texture(Gdx.files.internal("Raindrops_Cactus.png"));
	     // Sound
	     //rainToCactiSound = Gdx.audio.newSound(Gdx.files.internal("drop_on_cacti.wav"));
	     //rainToUmbrellaSound = Gdx.audio.newSound(Gdx.files.internal("rain.wav"));
	     //raindropsMusic = Gdx.audio.newMusic(Gdx.files.internal("raindrops.mp3"));
	     // Background Music
	     //raindropsMusic.setLooping(true)
	     //raindropsMusic.play();
	     // Camera
	     camera = new OrthographicCamera();
	     camera.setToOrtho(false, 160, 144);
	     // SpriteBatch
	     // Umbrella
	     umbrella = new Rectangle();
	     umbrella.x = (160 / 2) - (32 / 2);
	     umbrella.y = 48 + 24;
	     umbrella.width = 32;
	     umbrella.height = 32;
	     // GameLogic
	     umbrellaPosition = 2;
	     rainDrop.spawnRaindrop();
	     cacti = new Array<Rectangle>();
	     placeCacti();
	 }
	 
	 @Override
	 public void render(float delta)
	 {
		 Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	     Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		 camera.update();
	        
	        batch.setProjectionMatrix(camera.combined);
	        batch.begin();
	        batch.draw(umbrellaImage, umbrella.x, umbrella.y);
	        rainDrop.drawAll();
	        for(Rectangle cactus: cacti) {
	            batch.draw(cactusImage, cactus.x, cactus.y);
	        }
	        batch.end();
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
	            if(umbrella.getX() > 0) {
	                umbrella.x = umbrella.getX() - 3;
	            }
	        }
	        
	        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT )|| Gdx.input.isKeyPressed(Input.Keys.D)) {
	        	if(umbrella.getX() < (160 - 32)) {
	                umbrella.x = umbrella.getX() + 3;
	            }
	        }
	        
	        spawnRaindrops();
	        checkRainDrops();
	 }
	 
	 private void spawnRaindrops()
	 {
		 if(TimeUtils.nanoTime() - lastDropTime > 300000000) {
	            rainDrop.spawnRaindrop();
	            lastDropTime = TimeUtils.nanoTime();
	        }
	 }
	 
	 private void checkRainDrops()
	 {
		 Iterator<Rectangle> iter = rainDrop.raindrops.iterator(); 
	     while(iter.hasNext()) {
	         Rectangle raindrop = iter.next();
	         raindrop.y = raindrop.getY() - (100 * Gdx.graphics.getDeltaTime());
	         if(raindrop.y + 16 < 0) {
	             iter.remove();
	         }
	         if(raindrop.overlaps(umbrella)) {
	             //rainToUmbrellaSound.play();
	             iter.remove();
	         }
	         else {
	             Iterator<Rectangle> iter2 = cacti.iterator();
	             while(iter2.hasNext()) {
	                 Rectangle cactus = iter2.next();
	                 if(raindrop.overlaps(cactus)) {
	                     //rainToCactiSound.play();
	                     iter.remove();
	                 }
	             }
	         }
	     }
	 }
	    
	 private void placeCacti() {
	     Rectangle cactus = new Rectangle();
	     cactus.x = (160 / 2) - (52 + 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cacti.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) - (32 / 2);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cacti.add(cactus);
	     cactus = new Rectangle();
	     cactus.x = (160 / 2) + (52 - 16);
	     cactus.y = 48 - (32 / 2);
	     cactus.width = 32;
	     cactus.height = 32;
	     cacti.add(cactus);       
	 }
}
