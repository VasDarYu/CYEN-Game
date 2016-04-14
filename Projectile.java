import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

class Projectile extends GameObject
{
	private Handler handler;
	
	public Projectile(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		velX = 5;
		velY = 5;
	}
	
	public Rectangle getBounds()
	{
		return new Rectangle(x, y, 16, 16);
	}
	
	public void tick()
	{
		x += velX;
		y += velY;
		
		//x = Game.clamp(x, 0, Game.WIDTH - 37);
		//y = Game.clamp(y, 0, Game.HEIGHT - 60);
		if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
		
		//handler.addObject(new Trail(x, y, ID.Trail, Color.red, 0.01f, handler));
	}
	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect(x, y, 16, 16);
	}
}