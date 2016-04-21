<<<<<<< HEAD
package com.videogame.main;

import javax.swing.*;

class Window
{
	private static JFrame frame;
	
	public static void main(String [] args)
	{
		makeBackground1();
	}
	
	public static void makeBackground1()
	{
		frame = new JFrame("Background 1 Test");
								
		JLabel background1 = new JLabel(new ImageIcon("C:\\Users\\JClark9701\\Pictures\\Background 1.png"));
		frame.add(background1);
				
		frame.setSize(750, 525);

		frame.pack();
		frame.setVisible(true);
	}
}
=======
package com.videogame.main;

import javax.swing.*;

class Window
{
	private static JFrame frame;
	
	public static void main(String [] args)
	{
		makeBackground1();
	}
	
	public static void makeBackground1()
	{
		frame = new JFrame("Background 1 Test");
								
		JLabel background1 = new JLabel(new ImageIcon("C:\\Users\\JClark9701\\Pictures\\Background 1.png"));
		frame.add(background1);
				
		frame.setSize(750, 525);

		frame.pack();
		frame.setVisible(true);
	}
}
>>>>>>> origin/master
