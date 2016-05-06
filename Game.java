import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import java.util.Random;
/**
 * This is the main game class.
 * This is what starts the game, spawns new enemies, and keeps track of FPS.
 */
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
    private int time = 0;
    public static boolean epic = false;
    private boolean read = false;
    private boolean read2 = false;
    private boolean boss = false;
    private int loop = 0;
    public static enum STATE
    {
        MENU,
        GAME,
        HELP,
        LOSE
    };
    
    public static STATE State = STATE.MENU;
    
    /**
     * This class picks a random number. This is used in picking the speeds and x position of the bullets when they spawn.
     */
    public int randomGenerator(int min, int max)
    {
        int x = random.nextInt(max - min + 1) + min;
        return x;
    }
    
    /**
     * Constructor method
     * 
     * Creates all the nessessary objects and spawns the player.
     */
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
        
        
        handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler, this));
        //handler.addObject(new Star(randomGenerator(64, WIDTH-64), 0, ID.Star, handler, randomGenerator(1,5), this));
    }
    
    /**
     * As the name may imply, this method initializes the background image for the game.
     */
    private void initImages()
    {
        try 
        {
            bg = ImageIO.read(new File("bg/Space1.png"));
        } catch (IOException e) {}
    }
    
    /**
     * This method starts the game.
     */
    public synchronized void start()
    {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    /**
     * This method stops the game.
     */
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
    
    /**
     * When the game starts, this method loops. It prints the FPS and creates the ticks.
     */
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
                if(Game.State==Game.STATE.GAME)
                {
                    time+=1;
                }
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    /**
     * This is the tick method. When it runs, it makes bullets fall, determines if the game is in EPIC mode, and runs the handler and HUD
     * objects.
     */
    private void tick()
    {
        if(State == STATE.GAME)
        {
            if (read == false)
                {
                    if (epic==true)
                    {
                        time = 295;
                    }
                    read = true;
                }
            if(epic==false)
            {
                if (read2==false)
                {
                
                
                    handler.addObject(new Star(randomGenerator(64, WIDTH-64), 0, ID.Star, handler, randomGenerator(1,5), this));
           
                    read2=true;
                
                }
            }
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
    
    /**
     * This creates the graphic object that will be used throughout the program.
     * It also draws all of the backgrounds for all of the game states.
     */
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
    
    /**
     * This determines if an object can move past a certain x or y position.
     */
    public static int clamp(int var, int min, int max)
    {
        if(var >= max) return var = max;
        else if (var <= min) return var = min;
        else return var;
    }
    
    /**
     * Begins the game.
     */
    public static void main(String [] args)
    {
        new Game();
    }
    
    /**
     * Creates a game-over state.
     */
    public void gameOver()
    {
        Game.State = Game.STATE.LOSE;
        HUD.HEALTH = 100;
        handler.reset();
        time=0;
    }
    
    /**
     * Makes it rain bullets.
     * Only 41 bullets will spawn ever, regardless of game mode.
     */
    private void bullet()
    {
        if(State == STATE.GAME)
        {    
            if(time==0)boss=false;
            int a = randomGenerator(1,150);
            if(a%20 == 0 && handler.count <16)
            {
                 int x = randomGenerator(16, WIDTH-16);
                 int yspeed = randomGenerator(3,8);
                 int xspeed = randomGenerator(0,10);
                 handler.addObject(new Enemy(x, 0, ID.Projectile, handler, yspeed, xspeed, this, ID.Small));
            }
            else if(time>60)
            {
                if(a%30 == 0 && handler.count <31)
                {
                    int x = randomGenerator(32, WIDTH-32);
                    int yspeed = randomGenerator(3,8);
                    int xspeed = randomGenerator(0,10);
                    handler.addObject(new Enemy(x, 0, ID.Projectile, handler, yspeed, xspeed, this, ID.Medium));
                }
                else if (time>120)
                {
                    if(a%75 == 0 && handler.count < 39)
                    {
                        int x = randomGenerator(64, WIDTH-64);
                        int yspeed = randomGenerator(3,8);
                        int xspeed = randomGenerator(0,5);
                        handler.addObject(new Enemy(x, 0, ID.Projectile, handler, yspeed, xspeed, this, ID.Big));
                    }
                    else if(time>300)
                    {
                        //int b = randomGenerator(1,1000);
                        if(boss!=true)
                        {
                            int x = randomGenerator(128, WIDTH-128);
                            int yspeed = randomGenerator(3,8);
                            int xspeed = randomGenerator(0,3);
                            handler.addObject(new Enemy(x, -128, ID.Projectile, handler, yspeed, xspeed, this, ID.Boss));
                            boss=true;
                            
                        }
                        else if(time>360)
                        {
                            loop+=1;
                            time=0;
                        }
                    }
                }
            }
        }
    }
}

