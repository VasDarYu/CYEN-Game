import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.BufferedImage;

class Game extends Canvas implements Runnable
{
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    
    private BG bg;
    private BufferedImage spriteSheet = null;
    
    
    public Game()
    {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Ultimate Street Smash Rumble Fighter Z 2: Maximum Ninja Storm", this);
        
        hud = new HUD();
        
        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
        handler.addObject(new Projectile(WIDTH / 2 + 64, HEIGHT / 2 - 32, ID.Projectile, handler));
    }
    
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
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
        BufferedImageLoader loader = new BufferedImageLoader(); //creates a new image loader
		try
		{
			spriteSheet = loader.loadImage("Background 1.png"); //loads a spritesheet
		}
		catch(IOException e)
		{
			e.printStackTrace(); //gives error if not possible
		}		
		
		
		
		bg = new BG(0, 0, this);
		
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
        handler.tick();
        hud.tick();
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
        
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        hud.render(g);
        
        bg.render(g);
        
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
