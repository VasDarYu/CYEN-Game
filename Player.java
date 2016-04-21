import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

class Player extends GameObject
{
	Handler handler;
	BufferedImage ship;
	public Player(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 32, 32);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		x = Game.clamp(x, 0, Game.WIDTH - 37);
		y = Game.clamp(y, 0, Game.HEIGHT - 60);
		
		collision();
	}
	
	public void collision()
	{
		for(GameObject obj : handler.object)
		{
			if(obj.getID() == ID.Projectile)
			{
				if(getBounds().intersects(obj.getBounds()))
				{
					HUD.HEALTH -= 2;
				}
			}
		}
	}
	
	public void render(Graphics g)
	{
		try 
		{
			ship = ImageIO.read(new File("ship.png"));
		} catch (IOException e) {System.out.println("This is bullshit:" + e);}
	    //if(id == ID.Player) g.setColor(Color.green);
		//else g.setColor(Color.blue);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(ship, x, y, null);
	}
}