import java.io.*;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class Leaderboard
{
	private ArrayList<String[]> leaderboard;
	private int maxScores = 5;
	public JFrame frame;
	public JFrame frame2;
	private String passedScore;
	JButton button = new JButton("Add Score");
	JTextField textBox = new JTextField(10);
	JPanel scores = new JPanel(new GridLayout(0, 2));
	JButton close = new JButton("Close");
	JLabel error = new JLabel("");
	
	public Leaderboard()
	{
		frame = new JFrame("Congratulations!");
		frame2 = new JFrame("Leaderboard");
		leaderboard = new ArrayList<>();
		populateArrayList();
		setUpFrame2();
	}
	public boolean isHighScore(String testScore)
	{
		if(leaderboard.size() < maxScores) return true;
		for(String[] score : leaderboard)
		{
			if(Integer.parseInt(testScore)>Integer.parseInt(score[1])) return true;
		}
		return false;
	}
	
	public void prompt(String score)
	{
		passedScore = score;
		frame.setSize(350, 220);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(0, 1));
		
		setUpButton();
		
		addContent();
		
		frame.setVisible(true);
	}
	
	public void setUpButton()
	{
		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if(textBox.getText().length() > 15)
					error.setText("Name too long.");
				else if(textBox.getText().contains(" "))
					error.setText("No Spaces allowed.");
				else
				{
					addHighScore(textBox.getText(), passedScore);
					frame.setVisible(false);
				}
			}
		});
	}
	
	public void addContent()
	{
		JLabel line1 = new JLabel("New High Score!");
		line1.setHorizontalAlignment(JLabel.CENTER);
		frame.add(line1);
		
		JLabel line2 = new JLabel("Your Score Is: " + passedScore);
		line2.setHorizontalAlignment(JLabel.CENTER);
		frame.add(line2);
		
		JLabel line3 = new JLabel("Input Name Below:");
		line3.setHorizontalAlignment(JLabel.CENTER);
		frame.add(line3);
		
		error.setHorizontalAlignment(JLabel.CENTER);
		frame.add(error);

		frame.add(textBox);
		frame.add(button);
	}
	
	public void populateArrayList()
	{
		try 
		{
			BufferedReader reader = new BufferedReader(new FileReader("txt/leaderboard.txt"));
			String line = reader.readLine();
			while(line != null) 
			{
				String[] add = line.trim().split(" ");
				leaderboard.add(add);
				line = reader.readLine();
			}
			reader.close();
		}
		catch(FileNotFoundException e) {System.out.println("File not found.");}
		catch(IOException e) {System.out.println("Not sure what went wrong.");}
	}
	
	public void addHighScore(String name, String testScore)
	{
		String[] newScore = {name, testScore};
		boolean added = false;
		if(isHighScore(testScore))
		{
			for(int i = 0; i < leaderboard.size(); i++)
			{
				if(!added && Integer.parseInt(testScore)>Integer.parseInt(leaderboard.get(i)[1]))
				{
					leaderboard.add(i, newScore);
					added = true;
				}
				if(!added && i == leaderboard.size()-1)
				{
					if(leaderboard.size()<maxScores)
					{
						leaderboard.add(newScore);
						added = true;
					}
				}
			}
		} else
		{
			System.out.println("Not a high score.");
		}
		if(!added)
		{
			leaderboard.add(newScore);
		}
		if(leaderboard.size() >= maxScores)
		{
			for(int r = leaderboard.size() - 1; r >= maxScores; r--)
			{
				leaderboard.remove(r);
			}
		}
		writeFile();
	}
	
	public void writeFile()
	{
		try (FileWriter writer = new FileWriter("txt/leaderboard.txt")) {
            for(String[] print : leaderboard) 
			{
				for (int i = 0; i < 2; i++)
					writer.write(print[i] + " ");
                writer.write('\n');
			}
			writer.close();
        }
        catch(IOException e) {System.out.println(e);}
	}
	
	public void setUpFrame2()
	{
		frame2.setSize(350, 220);
		frame2.setResizable(false);
		frame2.setLocationRelativeTo(null);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setLayout(new GridLayout(0, 1));
		
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame2.setVisible(false);
			}
		});
		
		JLabel title = new JLabel("High Scores");
		title.setHorizontalAlignment(JLabel.CENTER);
		frame2.add(title);
		
		frame2.add(scores);
		
		frame2.add(close);
	}
	
	public void showLeaderboard()
	{
		scores.removeAll();
		for(String[] score : leaderboard)
			for(int i = 0; i < 2; i++)
				scores.add(new JLabel(score[i]));		
		frame2.setVisible(true);
	}
	
	public static void main(String [] args)
	{
		Leaderboard board = new Leaderboard();
	}
}