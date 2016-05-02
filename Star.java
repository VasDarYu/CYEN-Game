import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Star extends GameObject
{
    private Game game;
	private Handler handler;
	private BufferedImage star;
	//public  boolean gone = false;
	public Star(int x, int y, ID id, Handler handler, int vertical, Game game)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        //velX = vertical;
        velY = vertical;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH - 37);
		//y = Game.clamp(y, 0, Game.HEIGHT - 60);
		if(y >= Game.HEIGHT) 
		{
		    x = game.randomGenerator(164, game.WIDTH-64);
		    y=0;
		    
            //velX = game.randomGenerator(1, 5);
            velY = game.randomGenerator(1, 5);
		  }
		if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
	}
	public void render(Graphics g)
	{
		try 
		{
			star = ImageIO.read(new File("spr/star.png"));
		} catch (IOException e) {System.out.println("This is bullshit:" + e);}
	    //if(id == ID.Player) g.setColor(Color.green);
		//else g.setColor(Color.blue);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(star, x, y, null);
	}
	public void delete()
	{
	    handler.removeObject(this);
	}
}