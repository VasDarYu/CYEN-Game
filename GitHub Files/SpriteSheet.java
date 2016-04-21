 

import java.awt.image.BufferedImage;

class SpriteSheet 
{
	private BufferedImage image; //image to be loaded
	
	public SpriteSheet(BufferedImage image)
	{
		this.image=image;
	}
	public BufferedImage grabImage(int col, int row, int width, int height)
	{
		BufferedImage img = image.getSubimage((col*117)-117, (row*109)-109, width, height); //this is the current size of sprites. it may change as time goes on.
		return img;
	}
}