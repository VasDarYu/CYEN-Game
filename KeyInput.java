import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class KeyInput extends KeyAdapter
{
	private Handler handler;
	
	public KeyInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (GameObject obj : handler.object)
		{
			if (obj.getID() == ID.Player)
			{
				//Key events for Player 1
				if (key == KeyEvent.VK_W) obj.setVelY(-8);
				if (key == KeyEvent.VK_S) obj.setVelY(8);
				if (key == KeyEvent.VK_A) obj.setVelX(-8);
				if (key == KeyEvent.VK_D) obj.setVelX(8);
			}
		}
	}
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		for (GameObject obj : handler.object)
		{
			if (obj.getID() == ID.Player)
			{
				//Key events for Player 1
				if (key == KeyEvent.VK_W) obj.setVelY(0);
				if (key == KeyEvent.VK_S) obj.setVelY(0);
				if (key == KeyEvent.VK_A) obj.setVelX(0);
				if (key == KeyEvent.VK_D) obj.setVelX(0);
			}
		}
	}
}