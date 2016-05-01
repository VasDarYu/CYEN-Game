import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Big extends GameObject
{
	private Handler handler;
	private BufferedImage green;
	public Big(int x, int y, ID id, Handler handler, int vertical)
    {
        super(x, y, id);
        this.handler = handler;
        //velX = vertical;
        velY = vertical;
    }
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 64, 64);
	}
	
	public void tick()
	{
		//x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH - 37);
		//y = Game.clamp(y, 0, Game.HEIGHT - 60);
		//if(y <= 0 || y >= Game.HEIGHT - 64) handler.removeObject(this);
		//if(x <= 0 || x >= Game.WIDTH - 64) velX *= -1;
	}
	public void render(Graphics g)
	{
		try 
		{
			green = ImageIO.read(new File("spr/green.png"));
		} catch (IOException e) {System.out.println("This is bullshit:" + e);}
	    //if(id == ID.Player) g.setColor(Color.green);
		//else g.setColor(Color.blue);
		//g.fillRect(x, y, 32, 32);
		g.drawImage(green, x, y, null);
	}
	
}