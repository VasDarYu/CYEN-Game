import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Star extends GameObject
{
	private Handler handler;
	private BufferedImage star;
	public Star(int x, int y, ID id, Handler handler, int vertical)
    {
        super(x, y, id);
        this.handler = handler;
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
		//if(y <= 0 || y >= Game.HEIGHT - 48) handler.removeObject(this);
		//if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
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