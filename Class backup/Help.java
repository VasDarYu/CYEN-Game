import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Help
{
	public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 50);
	public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 300, 100, 50);
	public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 50);
	private BufferedImage logo;
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D)g;
		
		Font fnt0 = new Font("arial", Font.BOLD, 50);
		g.setFont(fnt0);
		g.setColor(Color.black);
		g.drawString("Welcome to the game!" + "\n" 
				+ "To play, use the WASD controls to avoid bullets." + "\n"
				+ "Grab the health orbs to get health back." + "\n"
				+ "Watch out for the Monster Bullet!", 10, 50);
		//g.drawString("Fighter Z 2:", 240, 100);
		//g.drawString("Maximum Ninja Storm", 100, 150);
		
		
		
		g.drawImage(logo, 0, 0, 750/2, 525/2, null);
		
		Font fnt1 = new Font("arial", Font.BOLD, 30);
		g.setFont(fnt1);
		
		g.drawString("Play", playButton.x +19, playButton.y +35);
		//g.drawString("Help", helpButton.x + 19, helpButton.y + 35);
		g.drawString("Exit", exitButton.x + 19, exitButton.y + 35);
		
		g2d.draw(playButton);
		//g2d.draw(helpButton);
		g2d.draw(exitButton);
	}
}