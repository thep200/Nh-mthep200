package com.mygdx.MyScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CreateScreenGame extends Game {
	public SpriteBatch batch ;

	@Override
	public void create () {
		batch = new SpriteBatch();;
		this.setScreen(new MyMainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
