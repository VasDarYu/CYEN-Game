import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

class Score
{
	protected static int score = 0, scoreMult = 1;

	public void render(Graphics g)
	{
		Font fnt0 = new Font("arial", Font.BOLD, 25);
		g.setFont(fnt0);
		g.setColor(Color.white);
		g.drawString(String.valueOf(score), Game.WIDTH - 250, 50);
		g.setColor(Color.blue);
		g.drawString("x"+scoreMult, Game.WIDTH - 100, 50);
	}
	public void tick()
	{
		score += 1 * scoreMult;
	}
	public String toString()
	{
		return String.valueOf(score);
	}
}