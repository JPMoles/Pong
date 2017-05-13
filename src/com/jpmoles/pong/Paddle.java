package com.jpmoles.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Paddle {

	public final static int WIDTH = 10;
	public final int HEIGHT = 150;
	private int x, y;
	private int upKey, downKey;
	private int moveSpeed;
	
	public Paddle(Random r, int x, int upKey, int downKey) {
		this.x = x;
		y = r.nextInt(100) + 10;
		this.upKey = upKey;
		this.downKey = downKey;
		moveSpeed = 0;
	}
	
	public void update() {
		if(y < 0) {
			y = 0;
		} else if(y > (Pong.HEIGHT - this.HEIGHT)) {
			y = (Pong.HEIGHT - this.HEIGHT);
		}
		y += moveSpeed;
	}

	public void moveUp() {
		y += -moveSpeed;
	}
	
	public void moveDown() {
		y += moveSpeed;
	}
	
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == upKey) {
			moveSpeed = -4;
		} else if(e.getKeyCode() == downKey) {
			moveSpeed = 4;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == upKey || e.getKeyCode() == downKey) {
			moveSpeed = 0;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, WIDTH, HEIGHT);
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, WIDTH, HEIGHT);
	}
	
	public int getUpKey() {
		return upKey;
	}
	
	public int getDownKey() {
		return downKey;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
}
