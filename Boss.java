import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Boss extends GameObject
{
	private Handler handler;
	private BufferedImage boss;
	public Boss(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 208, 208);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH - 37);
		//y = Game.clamp(y, 0, Game.HEIGHT - 60);
		if(y <= 0 || y >= Game.HEIGHT - (208+16)) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 208) velX *= -1;
	}
	public void render(Graphics g)
	{
		try 
		{
			boss = ImageIO.read(new File("spr/boss.png"));
		} catch (IOException e) {System.out.println("This is bullshit:" + e);}
	    //if(id == ID.Player) g.setColor(Color.green);
		//else g.setColor(Color.blue);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(boss, x, y, null);
	}
}