package com.fiveleaf.raindrops;

import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class RaindropsGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture rainDropImage;
    private Texture umbrellaImage;
    private Texture cactusImage;
    private Sound rainToCactiSound;
    private Sound rainToUmbrellaSound;
    private Music raindropsMusic;
    private Rectangle umbrella;
    private int umbrellaPosition;
    private Array<Rectangle> raindrops;
    private Array<Rectangle> cacti;
    private long lastDropTime;
    
    @Override
    public void create() {
        // Texture
        rainDropImage = new Texture(Gdx.files.internal("Raindrops_Rain.png"));
        umbrellaImage = new Texture(Gdx.files.internal("Raindrops_Umbrella.png"));
        cactusImage = new Texture(Gdx.files.internal("Raindrops_Cactus.png"));
        // Sound
        //rainToCactiSound = Gdx.audio.newSound(Gdx.files.internal("drop_on_cacti.wav"));
        //rainToUmbrellaSound = Gdx.audio.newSound(Gdx.files.internal("rain.wav"));
        //raindropsMusic = Gdx.audio.newMusic(Gdx.files.internal("raindrops.mp3"));
        // Background Music
        //raindropsMusic.setLooping(true);
        //raindropsMusic.play();
        // Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 160, 144);
        // SpriteBatch
        batch = new SpriteBatch();
        // Umbrella
        umbrella = new Rectangle();
        umbrella.x = (160 / 2) - (32 / 2);
        umbrella.y = 48 + 24;
        umbrella.width = 32;
        umbrella.height = 32;
        // GameLogic
        umbrellaPosition = 2;
        raindrops = new Array<Rectangle>();
        spawnRaindrop();
        cacti = new Array<Rectangle>();
        placeCacti();
    }
    
    @Override
    public void render() {
        Gdx.gl.glClearColor(0.6055f, 0.7344f, 0.0586f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        camera.update();
        
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(umbrellaImage, umbrella.x, umbrella.y);
        for(Rectangle raindrop: raindrops) {
            batch.draw(rainDropImage, raindrop.x, raindrop.y);
        }
        for(Rectangle cactus: cacti) {
            batch.draw(cactusImage, cactus.x, cactus.y);
        }
        batch.end();
        
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if(umbrella.getX() > 0) {
                umbrella.x = umbrella.getX() - 3;
            }
        }
        
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        	if(umbrella.getX() < (160 - 32)) {
                umbrella.x = umbrella.getX() + 3;
            }
        }
        if(TimeUtils.nanoTime() - lastDropTime > 100000000) {
            spawnRaindrop();
        }
        
        Iterator<Rectangle> iter = raindrops.iterator();
        
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
    
    @Override
    public void dispose() {
        rainDropImage.dispose();
        umbrellaImage.dispose();
        //rainToCactiSound.dispose();
        //rainToUmbrellaSound.dispose();
        //raindropsMusic.dispose();
        batch.dispose();
    }
    
    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 160-16);
        raindrop.y = 144;
        raindrop.width = 8;
        raindrop.height = 8;
        raindrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
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