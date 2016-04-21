
 

import javax.swing.*;

class Background
{
	private static JFrame frame;
	
	public static void main(String[] args)
	{
	    makeBackground();
	}
	public static void makeBackground()
	{
		frame = new JFrame("Background 1 Test");
								
		JLabel background1 = new JLabel(new ImageIcon("Background 1.png"));
		frame.add(background1);
				
		frame.setSize(750, 525);

		frame.pack();
		frame.setVisible(true);
	}
}
