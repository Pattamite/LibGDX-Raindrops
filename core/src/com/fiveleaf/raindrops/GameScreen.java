package com.fiveleaf.raindrops;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class GameScreen extends ScreenAdapter{
	private OrthographicCamera camera;
	
	private Sound rainToCactiSound;
	private Sound rainToUmbrellaSound;
	private Music raindropsMusic;
	
	public SpriteBatch batch;
	private RaindropsGame raindropsGame;
	private RainDrop rainDrop;
	private Umbrella umbrella;
	private Fertilizer fertilizer;
	private Cacti cacti;
	private CactiHealthBar cactiHealthBar;
	private ScoreText scoreText;
	private WaterLevel water;
	private long lastDropTime;
	private int cactiCounter;
	private int fertilizerCount = 0;
	public int score;
	public int heart;
	public int rainDropsCount = 0;
	public int lastWaterLevel = 0;
	public static int MAX_WATERLEVEL = 60;
	public static int SCORE_UMBRELLA = 1;
	public static int SCORE_NEEDRAINEACH = 10;
	public static int SCORE_NEEDRAINCOMPLETE = 100;
	public static int SCORE_NEEDFERT = 50;
	public GameScreen(RaindropsGame raindropsGame) {
		this.raindropsGame = raindropsGame;
		this.batch = raindropsGame.batch;
		fertilizer = new Fertilizer(this.raindropsGame, this);
		rainDrop = new RainDrop(this.raindropsGame, this);
		umbrella = new Umbrella(this.raindropsGame, this);
		cacti = new Cacti(this.raindropsGame, this);
		cactiHealthBar = new CactiHealthBar(cacti, this);
		scoreText = new ScoreText(this.raindropsGame, this);
	    water = new WaterLevel(this.raindropsGame, this);
	//Sound
	    //rainToCactiSound = Gdx.audio.newSound(Gdx.files.internal("drop_on_cacti.wav"));
	    //rainToUmbrellaSound = Gdx.audio.newSound(Gdx.files.internal("rain.wav"));
	    //raindropsMusic = Gdx.audio.newMusic(Gdx.files.internal("raindrops.mp3"));
	//Background Music
	    //raindropsMusic.setLooping(true)
	    //raindropsMusic.play();
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 160, 144);
	    rainDrop.spawnRaindrop();
	    
	    score = 0;
	    heart = 5;
	}
	
	@Override
	public void render(float delta)
	{
	    Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		controlUmbrella();
	    spawnRaindrops();
	    checkRainDrops();
	    checkFertilizer();
	    waterControl();
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();
	    umbrella.draw();
	    rainDrop.draw();
	    cacti.draw();
	    water.draw();
	    fertilizer.draw();
	    cactiHealthBar.draw();
	    scoreText.draw();
	    batch.end();  
	}
	private void waterControl() {
	    if(water.getWaterLevel() > Cacti.STARTING_Y + 16 && water.getWaterLevel() != lastWaterLevel && water.getWaterLevel() < MAX_WATERLEVEL){
	        Iterator<Rectangle> iterCacti = cacti.cactiRectangle.iterator();
            cactiCounter = 0;
            while(iterCacti.hasNext()) {
                Rectangle cactus = iterCacti.next();
                cactus.y = cactus.getY() + 1;
                cactiCounter++;
            }
            lastWaterLevel = water.getWaterLevel();
	    }
	}
	private void controlUmbrella()
	{
	    if(Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
	        if(umbrella.umbrellaRactangle.getX() > 0) {
	            umbrella.umbrellaRactangle.x = umbrella.umbrellaRactangle.getX() - 3;
	        }
	    }
	    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT )|| Gdx.input.isKeyPressed(Input.Keys.D)) {
	        if(umbrella.umbrellaRactangle.getX() < (160 - 32)) {
	            umbrella.umbrellaRactangle.x = umbrella.umbrellaRactangle.getX() + 3;
	        }
	    }
	    if(Gdx.input.isKeyPressed(Input.Keys.DOWN )|| Gdx.input.isKeyPressed(Input.Keys.S)) {
	        if(fertilizerCount == 0) {
	            fertilizer.spawnFertilizer(umbrella.umbrellaRactangle.getX());
	            fertilizerCount = fertilizerCount + 1;
	        }
	    }
	}
	private void spawnRaindrops()
	{
	    if(TimeUtils.nanoTime() - lastDropTime > 300000000) {
	        rainDropsCount = rainDropsCount + 1;
	        rainDrop.spawnRaindrop();
	        lastDropTime = TimeUtils.nanoTime();
	        //rainToCactiSound.play();
	    }
	}
	private void checkRainDrops()
	{
	    Iterator<Rectangle> iterRaindrop = rainDrop.raindropsRactangle.iterator(); 
	    while(iterRaindrop.hasNext()) {
	        Rectangle raindrop = iterRaindrop.next();
	        raindrop.y = raindrop.getY() - (100 * Gdx.graphics.getDeltaTime());
	        if(raindrop.y + 16 < 0) {
	            iterRaindrop.remove();
	        }
	        if(raindrop.overlaps(umbrella.umbrellaRactangle)) {
	            //rainToUmbrellaSound.play();
	            iterRaindrop.remove();
	            addScore(SCORE_UMBRELLA);
	        }
	        else {
	            Iterator<Rectangle> iterCacti = cacti.cactiRectangle.iterator();
	            cactiCounter = 0;
	            while(iterCacti.hasNext()) {
	                Rectangle cactus = iterCacti.next();
	                if(raindrop.overlaps(cactus)) {
	                    //rainToCactiSound.play();
	                    iterRaindrop.remove();
	                    cacti.rainHit(cactiCounter);
	                }
	                cactiCounter++;
	            }
	        }
	    }
	}
	private void checkFertilizer()
	{
	    if(fertilizer.fertilizer != null) {
	        Rectangle targetFertilizer = fertilizer.fertilizer;
	        targetFertilizer.y = targetFertilizer.getY() - (50 * Gdx.graphics.getDeltaTime());
	        if(targetFertilizer.y + 16 < 0) {
	            fertilizer.fertilizer = null;
	            fertilizerCount = 0;
	        }
	        else {
	            Iterator<Rectangle> iterCacti = cacti.cactiRectangle.iterator();
	            cactiCounter = 0;
	            while(iterCacti.hasNext()) {
	                Rectangle cactus = iterCacti.next();
	                if(targetFertilizer.overlaps(cactus)) {
	                    //rainToCactiSound.play();
	                    fertilizer.fertilizer = null;
	                    fertilizerCount = 0;
	                    addScore(SCORE_NEEDFERT);
	                    cacti.fertHit(cactiCounter);
	                }
	                cactiCounter++;
	            }
	        }
	    }
	}
	public void addScore(int value)
	{
		score += value;
	}
}
