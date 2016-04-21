package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable
{
	//screen details
	public static final int WIDTH = 480;
	public static final int HEIGHT = WIDTH/12 *7;
	public static final int SCALE = 2; //multiplies the width and height by SCALE to make the screen larger
	
	public final String TITLE = "Ultimate Street Smash Rumble Fighter Z 2: Maximum Ninja Storm";
	
	private boolean running = false; //determines if the game is currently running
	private Thread thread;
	
	//graphics
	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB); //background
	private BufferedImage spriteSheet = null; //any sprites on the screen
	
	public Player p;
	
	public void init() //initializes character
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader(); //creates a new image loader
		try
		{
			spriteSheet = loader.loadImage("/Serra Frames.png"); //loads a spritesheet
		}
		catch(IOException e)
		{
			e.printStackTrace(); //gives error if not possible
		}		
		
		addKeyListener(new KeyInput(this));
		
		p = new Player(200, 200, this);
	}
	
	private synchronized void start() //starts game
	{
		if(running) //determines if game is running
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() //stops game
	{
		if(!running)
			return;
		
		running = false;
		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace(); //gives error message if not possible
		}
		System.exit(1);
	}
	
	public void run() //gives the FPS and ticks the game currently runs at
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta>=1)
			{
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			if(System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick() //serves as a delay
	{
		p.tick();
	}
	
	private void render() //this is what draws stuff
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics(); 
		//
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		
		p.render(g);
		//
		g.dispose();
		bs.show();
	}
	public static void main(String args[]) //main method
	{
		Game game = new Game();
		game.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE)); //sets screen size
		game.setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		game.setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		//screen size as of now may not be changed
		JFrame frame = new JFrame(game.TITLE); //adds title to upper part of screen
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true); //makes screen visible
		
		game.start();//starts the game
	}
	
	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{
			p.setVelX(5);
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			p.setVelX(-5);
		}
		else if(key == KeyEvent.VK_UP)
		{
			p.setVelY(-5);
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			p.setVelY(5);
		}else if(key == KeyEvent.VK_Z)
		{
			p.setSecond(true);
		}
		else if(key == KeyEvent.VK_X)
		{
			p.setPunch(true);
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_RIGHT)
		{
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_LEFT)
		{
			p.setVelX(0);
		}
		else if(key == KeyEvent.VK_UP)
		{
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_DOWN)
		{
			p.setVelY(0);
		}
		else if(key == KeyEvent.VK_Z)
		{
			p.setSecond(false);
		}
		else if(key == KeyEvent.VK_X)
		{
			p.setPunch(false);
		}
	}	
}