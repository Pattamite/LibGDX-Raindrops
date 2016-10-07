package com.fiveleaf.raindrops.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.fiveleaf.raindrops.RaindropsGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(160, 144);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new RaindropsGame();
        }
}