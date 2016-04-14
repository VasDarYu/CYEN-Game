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
	
		makeMenuBar();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}
	
	private void makeMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem openItem = new JMenuItem("Open");
		fileMenu.add(openItem);
		
		JMenuItem quitItem = new JMenuItem("Quit");
		fileMenu.add(quitItem);
	}
}