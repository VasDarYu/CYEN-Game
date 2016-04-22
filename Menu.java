import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics2D;

class Menu
{
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 300, 100, 50);
	public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 50);
	
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString("Ultimate Street Smash Rumble", 10, 50);
		g.drawString("Fighter Z 2:", 240, 100);
		g.drawString("Maximum Ninja Storm", 100, 150);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		g.drawString("Play", playButton.x +19, playButton.y +35);
		g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g.drawString("Exit", exitButton.x + 19, exitButton.y + 35);
		
		g2d.draw(playButton);
		g2d.draw(helpButton);
		g2d.draw(exitButton);
	}
}