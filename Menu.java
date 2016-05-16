import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Menu
{
	public Rectangle button1 = new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 50);
	public Rectangle button2 = new Rectangle(Game.WIDTH / 2 - 50, 300, 100, 50);
	public Rectangle button3 = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 50);
	private BufferedImage logo;
	Game game;
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		String string1 = "", string2 = "", string3 = "", path = "";
		int x = 19;
		
		Font fnt0 = new Font("arial", Font.BOLD, 60);
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		
		if(Game.State == Game.STATE.MENU)
		{
			path = "bg/menubg.png";
			string1 = "Play";
			string2 = "Epic";
			string3 = "Exit";
		}
		else if(Game.State == Game.STATE.LOSE)
		{
			x = 10;
			path = "bg/SpaceExplode.png";
			string1 = "Score";
			string2 = "Again";
			string3 = "Exit";
		}
		
		try 
		{
			logo = ImageIO.read(new File(path));
		} catch (IOException e) {}
		
		g.drawImage(logo, 0, 0, Game.WIDTH, Game.HEIGHT, null);	

		g.setColor(Color.black);
		
		if(Game.State == Game.STATE.LOSE)
		{
			g.setFont(fnt0);
			g.drawString("YOU LOSE", (Game.WIDTH/2 - 148), 152);
			g.drawString("YOU LOSE", (Game.WIDTH/2 - 148), 148);
			g.drawString("YOU LOSE", (Game.WIDTH/2 - 152), 152);
			g.drawString("YOU LOSE", (Game.WIDTH/2 - 152), 148);
			g.setColor(Color.white);
			g.drawString("YOU LOSE", (Game.WIDTH/2 - 150), 150);
			g.setColor(Color.black);
			g2d.fill(button1);
			g2d.fill(button2);
			g2d.fill(button3);
			g.setColor(Color.white);
		}
		g.setFont(fnt1);
		
		
		g.drawString(string1, button1.x + x, button1.y + 35);
		g.drawString(string2, button2.x + x, button2.y + 35);
		g.drawString(string3, button3.x + 19, button3.y + 35);
		
		g2d.draw(button1);
		g2d.draw(button2);
		g2d.draw(button3);
	}
	
}