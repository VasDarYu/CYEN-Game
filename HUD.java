import java.awt.Color;
import java.awt.Graphics;

class HUD
{
	public static int HEALTH = 100;
	Player player;
	
	public HUD(Player player)
	{
		this.player = player;
	}
	
	public void tick()
	{
		HEALTH = Game.clamp(HEALTH, 0, 100);
	}
	public void render(Graphics g)
	{
		g.setColor(Color.gray);
		g.fillRect(15, 15, 200, 32);
		
		if(player.shield)
			g.setColor(Color.blue);
		else if(HEALTH < 31)
			g.setColor(Color.red);
		else
			g.setColor(Color.green);
		
		g.fillRect(15, 15, HEALTH * 2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	}
}