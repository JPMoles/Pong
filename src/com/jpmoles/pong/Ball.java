package com.jpmoles.pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	
	private Random r;
	public static final int DIAMETER = 40;
	private int x, y;
	private int xVel = 6, yVel = 8;
	private boolean gameFinished;
	
	public Ball(Random r) {
		this.r = r;
		reset();
	}

	public void reset() {
		resetPosition();
		resetVelocity();
		gameFinished = false;
	}
	
	private void resetPosition() {
		x = 400;
		y = 300;
	}
	
	private void resetVelocity() {
		xVel = r.nextBoolean() ? xVel : -xVel;
		yVel = r.nextBoolean() ? yVel : -yVel;
	}

	public void update() {
		if(!gameFinished) {
			if(y < 0 || y > (Pong.HEIGHT - DIAMETER)) {
				yVel = -yVel;
			}
			x += xVel;
			y += yVel;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.CYAN);
		if(!gameFinished) {
			g.fillOval(x, y, DIAMETER, DIAMETER);
		}
	}
	
	public void setFinished() {
		gameFinished = true;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}
	
	public void setVelocity(int xVel) {
		this.xVel = xVel;
	}
	
	public int getVelocity() {
		return xVel;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
