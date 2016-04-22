import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

class Game extends Canvas implements Runnable
{
	//public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	public static final int WIDTH = 750, HEIGHT = 525;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	private BufferedImage bg;
	private Menu menu;
	
	public static enum STATE
	{
		MENU,
		GAME
	};
	
	public static STATE State = STATE.MENU;
	
	public Game()
	{
		menu = new Menu();
		initImages();
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(new MouseInput());
		new Window(WIDTH, HEIGHT, "Ultimate Street Smash Rumble Fighter Z 2: Maximum Ninja Storm", this);
		
		hud = new HUD();
		
		
		handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
		handler.addObject(new Projectile(WIDTH / 2 + 64, HEIGHT / 2 - 32, ID.Projectile, handler));
	}
	
	private void initImages()
	{
		try 
		{
			bg = ImageIO.read(new File("bg1.png"));
		} catch (IOException e) {}
	}
	
	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(running) render();
			frames++;
			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	private void tick()
	{
		if(State == STATE.GAME)
		{
		handler.tick();
		hud.tick();
		}	
	}
	
	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		
		
		if(State == STATE.GAME)
		{
			g.drawImage(bg, 0, 0, WIDTH, HEIGHT, null);
		
			handler.render(g);
			hud.render(g);
		} else if(State == STATE.MENU)
		{
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			menu.render(g);
		}		
		
		
		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max)
	{
		if(var >= max) return var = max;
		else if (var <= min) return var = min;
		else return var;
	}
	
	public static void main(String [] args)
	{
		new Game();
	}
}
