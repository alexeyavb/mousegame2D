package com.mygdx.mygdxexample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Input.Keys;

import java.security.Key;

public class MyGdxExample extends ApplicationAdapter {
	private SpriteBatch batch;

	private Texture mouseTexture;
	private float mouseX,mouseY;

	private Texture cheeseTexture;
	private float cheeseX,cheeseY;

	private Texture floorTexture;
	private Texture winMsg;

	private boolean win;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mouseTexture = new Texture(("mouse.png"));
		mouseX = 20; mouseY = 20;

		cheeseTexture = new Texture(("cheese.png"));
		cheeseX = 400; cheeseY = 400;

		floorTexture = new Texture(("floor.png"));
		winMsg = new Texture(("youwin.png"));

		win = false;
	}

	private void CheckUserInputProc(){
		if(Gdx.input.isKeyPressed(Keys.LEFT))
			mouseX--;
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
			mouseX++;
		if(Gdx.input.isKeyPressed(Keys.UP))
			mouseY++;
		if(Gdx.input.isKeyPressed(Keys.DOWN))
			mouseY--;

		win = ((mouseX < cheeseX)
				&& (mouseX + mouseTexture.getWidth() == cheeseX + cheeseTexture.getWidth())
				&& (mouseY < cheeseY)
				&& (mouseY + mouseTexture.getHeight() > cheeseY + cheeseTexture.getHeight()) ) ? true:false;
	}
	@Override
	public void render () {
		CheckUserInputProc();
		Gdx.gl.glClearColor(1f, 1f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(floorTexture, 0, 0);
		batch.draw(cheeseTexture,cheeseX,cheeseY);
		batch.draw(mouseTexture,mouseX,mouseY);
		if(win) {
			batch.draw(winMsg, 170, 60);
		}
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		winMsg.dispose();
		cheeseTexture.dispose();
		mouseTexture.dispose();
		floorTexture.dispose();
	}
}
