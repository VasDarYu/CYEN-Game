import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;


class MouseInput implements MouseListener
{
	public void mouseClicked(MouseEvent arg0)
	{
		
	}
	public void mouseEntered(MouseEvent arg0)
	{
		
	}
	public void mouseExited(MouseEvent arg0)
	{
		
	}
	public void mousePressed(MouseEvent e)
	{
		//public Rectangle playButton = new Rectangle(Game.WIDTH / 2 - 50, 200, 100, 50);
		//public Rectangle helpButton = new Rectangle(Game.WIDTH / 2 - 50, 300, 100, 50);
		//public Rectangle exitButton = new Rectangle(Game.WIDTH / 2 - 50, 400, 100, 50);
		int mx = e.getX();
		int my = e.getY();
		
		if(mx >=Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50)
		{
			if(my >= 200 && my <= 250)
			{
				Game.State = Game.STATE.GAME;
			}
		}
		if(mx >=Game.WIDTH / 2 - 50 && mx <= Game.WIDTH / 2 + 50)
		{
			if(my >= 400 && my <= 450)
			{
				System.exit(1);
			}
		}
		
	}
	public void mouseReleased(MouseEvent arg0)
	{
		
	}
}