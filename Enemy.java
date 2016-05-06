import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


class Enemy extends GameObject
{
    private Game game;
    private Handler handler;
    private BufferedImage image;
    private int a, dimensionA, dimensionB, dimensionC;
	public ID size;
	private String path;
	
    //public  boolean gone = false;
    public Enemy(int x, int y, ID id, Handler handler, int vertical, int horizontal, Game game, ID size)
    {
        super(x, y, id);
        this.handler = handler;
        this.game=game;
        velX = horizontal;
        velY = vertical;
		this.size = size;
		init();
    }
    
	private void init()
	{
		switch(size)
		{
			case Small: dimensionA = 16;
						dimensionB = 16;
						dimensionC = 16;
						path = "spr/small.png";
						break;
			case Medium: dimensionA = 32;
						 dimensionB = 32;
						 dimensionC = 32;
						 path = "spr/medium.png";
						 break;
			case Big: dimensionA = 64;
					  dimensionB = 64;
					  dimensionC = 64;
					  path = "spr/big.png";
					  break;
			case Boss: dimensionA = 208; 
					   dimensionB = 208;
					   dimensionC = 208;
					   path = "spr/boss.png";
					   break;
		}
		try 
        {
            image = ImageIO.read(new File(path));
        } catch (IOException e) {}
	}
	
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, dimensionC, dimensionC);
    }
    
    public void tick()
    {
        move();

        if(y >= Game.HEIGHT) 
        {
            x= game.randomGenerator(dimensionA, game.WIDTH-dimensionB);
			//if(size == ID.Boss) y = -208;
			//else 
			y = 0;
            a = game.randomGenerator(0,1);
            velX = game.randomGenerator(1, 5);
            velY = game.randomGenerator(1, 5);
          }
        if(x <= 0 || x >= Game.WIDTH - dimensionC) velX *= -1;
    }
	
    public void render(Graphics g)
    {
        g.drawImage(image, x, y, null);
    }
	
    private void move()
    {
        
        if(a==1)
        {
            x+=velX;
        }
        else 
        {
            x-=velX;
        }
        y+=velY;
    }
}