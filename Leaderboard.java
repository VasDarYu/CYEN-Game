//import java.io.*;
import java.util.ArrayList;

class Leaderboard
{
	private ArrayList<String[]> leaderboard;
	private int maxScores = 5;
	
	public Leaderboard()
	{
		/*try
		{
		BufferedWriter fw = new BufferedWriter(new FileWriter("txt/leaderboard.txt"));
		} catch(IOEception e) {}*/
		leaderboard = new ArrayList<>();
		
		
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
	
	
	
	public static void main(String [] args)
	{
		
	}
}