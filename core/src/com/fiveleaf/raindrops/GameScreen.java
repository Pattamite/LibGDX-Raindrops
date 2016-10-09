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
	private Sound fertToCactiSound;
	private Sound cactusDeadSound;
	private Music raindropsMusic;
	
	public SpriteBatch batch;
	private RaindropsGame raindropsGame;
	private RainDrop rainDrop;
	public Umbrella umbrella;
	private Fertilizer fertilizer;
	private Cacti cacti;
	private CactiHealthBar cactiHealthBar;
	private ScoreText scoreText;
	private WaterLevel water;
	private HeartText heartText;
	
	private long lastDropTime;
	private long lastMoveTime;
	private int cactiCounter;
	private int fertilizerCount = 0;
	public int score = 0;
	public int heart = 9;
	public int rainDropsCount = 0;
	public int lastWaterLevel = 0;
	public int[] moveTarget = {0, 0, 0};
	
	public static int MAX_WATERLEVEL = 75;
	public static int SCORE_UMBRELLA = 1;
	public static int SCORE_NEEDRAINEACH = 10;
	public static int SCORE_NEEDRAINCOMPLETE = 100;
	public static int SCORE_NEEDFERT = 0;
	
	private float rainToCactiSoundVolume = 0.5f;
	private float fertToCactiSoundVolume = 0.8f;
	private float cactusDeadSoundVolume = 0.6f;
	private float raindropsMusicVolume = 1.0f;
	
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
	    heartText = new HeartText(this.raindropsGame, this);
	    
	    rainToCactiSound = Gdx.audio.newSound(Gdx.files.internal("assets/CactusRainHit.wav"));
	    fertToCactiSound = Gdx.audio.newSound(Gdx.files.internal("assets/CactusFertHit.wav"));
	    cactusDeadSound = Gdx.audio.newSound(Gdx.files.internal("assets/CactusDead.wav"));
	    
	    raindropsMusic = Gdx.audio.newMusic(Gdx.files.internal("assets/Raindrops_BGM.wav"));
	    
	    raindropsMusic.setLooping(true);
	    raindropsMusic.setVolume(raindropsMusicVolume);
	    raindropsMusic.play();
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 160, 144);
	    rainDrop.spawnRaindrop();
	}
	
	@Override
	public void render(float delta){
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
	    heartText.draw();
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
            umbrella.umbrellaRactangle.y = umbrella.umbrellaRactangle.getY() + 1;
            lastWaterLevel = water.getWaterLevel();
	    }
	    if(water.getWaterLevel() > Cacti.STARTING_Y + 16){
	        cactiFloat();
	    }
	}
	private void controlUmbrella(){
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
	private void spawnRaindrops(){
	    if((TimeUtils.nanoTime() - lastDropTime)/10 > 40000000 - (100 * rainDropsCount) || (TimeUtils.nanoTime() - lastDropTime)/10 > 40000000 - (10000 * (rainDropsCount % 5000))) {
	        rainDropsCount = rainDropsCount + 1;
	        rainDrop.spawnRaindrop();
	        lastDropTime = TimeUtils.nanoTime();
	        //rainToCactiSound.play();
	    }
	}
	private void cactiFloat(){
        if((TimeUtils.nanoTime() - lastMoveTime) / 10 > (50000000)) {
            lastMoveTime = TimeUtils.nanoTime();
            Iterator<Rectangle> iterCacti = cacti.cactiRectangle.iterator();
            cactiCounter = 0;
            while(iterCacti.hasNext()) {
                Rectangle cactus = iterCacti.next();
                moveTarget[cactiCounter] = water.moveCactus();
                cactiCounter++;
            }
        }
    }
	private void checkRainDrops(){
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
	                	rainToCactiSound.play(rainToCactiSoundVolume);
	                    iterRaindrop.remove();
	                    cacti.rainHit(cactiCounter);
	                    break;
	                }
	                cactiCounter++;
	            }
	        }
	    }
	}
	private void checkFertilizer(){
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
	                	fertToCactiSound.play(fertToCactiSoundVolume);
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
	public void addScore(int value){
		score += value;
	}
	
	public void cactusDead(){
		heart--;
		heartText.ActivateBlink();
		cactusDeadSound.play(cactusDeadSoundVolume);
		if(heart <= 0) gameOver();
	}
	
	private void gameOver(){
		raindropsMusic.stop();
		raindropsGame.GameOver(this.score);
	}
}
