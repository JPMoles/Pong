package com.jpmoles.pong;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Pong extends JPanel implements ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 800, HEIGHT = 600;
	private Timer timer;
	private Random r;
	
	private Ball ball;
	private Paddle paddle1, paddle2;
	private int paddleScore1, paddleScore2;
	
	private Font scoreFont;
	
	public Pong() {
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		this.addKeyListener(this);
		this.setFocusable(true);
		this.requestFocus();
	}
	
	private void start() {
		r = new Random();
		scoreFont = new Font("Score Font", Font.BOLD, 16);
		
		ball = new Ball(r);
		paddle1 = new Paddle(r, 10, KeyEvent.VK_W, KeyEvent.VK_S);
		paddle2 = new Paddle(r, WIDTH - (9 + Paddle.WIDTH), KeyEvent.VK_UP, KeyEvent.VK_DOWN);
		paddleScore1 = 0;
		paddleScore2 = 0;
		
		timer = new Timer(20, this);
		timer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawBackground(g);
		ball.render(g);
		paddle1.render(g);
		paddle2.render(g);
		drawScore(g);
		drawWinnerScreen(g);
	}
	
	private void drawWinnerScreen(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(scoreFont);
		if(paddleScore1 == 5) {
			g.drawString("Player 1 Wins!", (WIDTH/2) - 50, (HEIGHT/2));
			g.drawString("Press Space to Play Again!", (WIDTH/2) - 100, (HEIGHT/2) + 15);
		} else if(paddleScore2 == 5) {
			g.drawString("Player 2 Wins!", (WIDTH/2) - 50, (HEIGHT/2));
			g.drawString("Press Space to Play Again!", (WIDTH/2) - 100, (HEIGHT/2) + 15);
			
		}
	}

	private void drawScore(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(scoreFont);
		g.drawString(paddleScore1 + " : " + paddleScore2, (WIDTH / 2) - 20, 20);
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
	}
	
	private void update() {
		ball.update();
		paddle1.update();
		paddle2.update();
		checkCollision();
		score();
	}

	private void score() {
		if(ball.getX() < 0) {
			paddleScore1++;
			ball.reset();
		} else if(ball.getX() > (Pong.WIDTH - Ball.DIAMETER)) {
			paddleScore2++;
			ball.reset();
		}
		
		if(paddleScore1 == 5 || paddleScore2 == 5) {
			ball.setFinished();
		}
	}

	private void checkCollision() {
		if(ball.getBounds().intersects(paddle1.getBounds()) || ball.getBounds().intersects(paddle2.getBounds())) {
			ball.setVelocity(ball.getVelocity() * -1);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		paddle1.keyPressed(e);
		paddle2.keyPressed(e);
		
		if((paddleScore1 == 5 || paddleScore2 == 5) && e.getKeyCode() == 32) {
			paddleScore1 = 0;
			paddleScore2 = 0;
			ball.reset();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		paddle1.keyReleased(e);
		paddle2.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		Pong pong = new Pong();
		JFrame frame = new JFrame("Pong");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(pong);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		pong.start();
	}
	
}
