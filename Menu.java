import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


public class Menu implements ActionListener {
    JFrame f;
   private BufferedImage logo;
   Graphics g;
  Menu() 
  {
    //Game game = new Game();
    //game.getGraphic();
    f = new JFrame("Ultimate Street Smash Rumble Fighter Z 2: Maximum Ninja Storm");
    f.setSize(750, 525);
    try 
        {
            logo = ImageIO.read(new File("Game Images.png"));
        } catch (IOException e) {System.out.println("This is bullshit:" + e);}
    
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JMenuBar jmb = new JMenuBar();

    JMenu jmFile = new JMenu("File");
    JMenuItem jmiOpen = new JMenuItem("Open");
    JMenuItem jmiClose = new JMenuItem("Close");
    //JMenuItem jmiSave = new JMenuItem("Save");
    JMenuItem jmiExit = new JMenuItem("Exit");
    jmFile.add(jmiOpen);
    jmFile.add(jmiClose);
    //jmFile.add(jmiSave);
    jmFile.addSeparator();
    jmFile.add(jmiExit);
    jmb.add(jmFile);

    //JMenu jmOptions = new JMenu("Options");
    //JMenu a = new JMenu("A");
    //JMenuItem b = new JMenuItem("B");
    //JMenuItem c = new JMenuItem("C");
    //JMenuItem d = new JMenuItem("D");
    //a.add(b);
    //a.add(c);
    //a.add(d);
    //jmOptions.add(a);

    //JMenu e = new JMenu("E");
    //e.add(new JMenuItem("F"));
    //e.add(new JMenuItem("G"));
    //jmOptions.add(e);

    //jmb.add(jmOptions);

    //JMenu jmHelp = new JMenu("Help");
    //JMenuItem jmiAbout = new JMenuItem("About");
    //jmHelp.add(jmiAbout);
    //jmb.add(jmHelp);

    jmiOpen.addActionListener(this);
    jmiClose.addActionListener(this);
    //jmiSave.addActionListener(this);
    jmiExit.addActionListener(this);
    //b.addActionListener(this);
    //c.addActionListener(this);
    //d.addActionListener(this);
    //jmiAbout.addActionListener(this);

    f.setJMenuBar(jmb);
    f.setVisible(true);
  }
  public void actionPerformed(ActionEvent ae) {
    String comStr = ae.getActionCommand();
    if(comStr.equals("Open"))
    {
        Game game = new Game();
        game.start();
        f.setVisible(false);
    }
    else if(comStr.equals("Close"))
    {
        f.setVisible(false);
    }
    //System.out.println(comStr + " Selected");
  }
  
  public static void main(String args[]) {
    new Menu();
  }
}