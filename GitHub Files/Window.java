import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.*;

class Window extends Canvas
{
	public Window(int width, int height, String title, Game game)
	{
		JFrame frame = new JFrame(title);
		
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		JLabel background1 = new JLabel(new ImageIcon("Background 1.png"));
		frame.add(background1);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		
		frame.pack();
		
		frame.setVisible(true);
		game.start();
	}
}