import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Random;

class Game extends Canvas implements Runnable
{
    //public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    public static final int WIDTH = (1920*3)/4, HEIGHT = (1080*3)/4;
    private Thread thread;
    private boolean running = false;
    private Handler handler;
    private HUD hud;
    private BufferedImage bg;
    private Menu menu;
    private Random random;
    private Lose lose;
    public static enum STATE
    {
        MENU,
        GAME,
        HELP,
        LOSE
    };
    
    public static STATE State = STATE.MENU;
    
    public int randomGenerator(int min, int max)
    {
        int x = random.nextInt(max - min + 1) + min;
        return x;
    }
    
    public Game()
    {
        menu = new Menu();
        lose = new Lose();
        initImages();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(new MouseInput());
        new Window(WIDTH, HEIGHT, "Ultimate Space Smash Rumble Fighter Z 2: Maximum Bullet Storm", this);
        
        random = new Random();
        
        hud = new HUD();
        
        
        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
    }
    
    private void initImages()
    {
        try 
        {
            bg = ImageIO.read(new File("bg/bg1.png"));
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
            if(HUD.HEALTH == 0)
            {
                gameOver();
            }
            bullet();
        }
        //else
        //{
        //    handler.tick();
        //}
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
            
                g.setColor(Color.white);
                g.fillRect(0, 0, WIDTH, HEIGHT);
            
                menu.render(g);
           
        } 
        else if(State == STATE.LOSE)
        {
            g.setColor(Color.red);
            g.fillRect(0, 0, WIDTH, HEIGHT);
            
            lose.render(g);
            
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
    
    public void gameOver()
    {
        Game.State = Game.STATE.LOSE;
        HUD.HEALTH = 100;
        //handler.delete();
    }
    
    private void bullet()
    {
        if(State == STATE.GAME)
        {    
            int a = randomGenerator(1,100);
            if(a%20 == 0)
            {
                int x = randomGenerator(16, WIDTH-16);
                int yspeed = randomGenerator(1,5);
                //int xspeed = randomGenerator(1,5);
                handler.addObject(new Small(x, 0, ID.Projectile, handler, yspeed));
            }
            else if(a%25 == 0)
            {
                int x = randomGenerator(32, WIDTH-32);
                int yspeed = randomGenerator(1,5);
                //int xspeed = randomGenerator(1,5);
                handler.addObject(new Medium(x, 0, ID.Projectile, handler, yspeed));
            }
            else if(a%30 == 0)
            {
                int x = randomGenerator(64, WIDTH-64);
                int yspeed = randomGenerator(1,5);
                //int xspeed = randomGenerator(1,5);
                handler.addObject(new Big(x, 0, ID.Projectile, handler, yspeed));
            }
            else if(a==1)
            {
                int x = randomGenerator(32, WIDTH-32);
                int yspeed = randomGenerator(1,5);
                //int xspeed = randomGenerator(1,5);
                handler.addObject(new Star(x, 0, ID.Star, handler, yspeed));
            }
        }
    }
}
