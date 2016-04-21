 

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

class BufferedImageLoader 
{
	private BufferedImage image; //the spritesheet that will be used for a character and/or AI
	
	public BufferedImage loadImage(String path) throws IOException
	{
		image = ImageIO.read(getClass().getResource(path));
		return image; //returns the image located at path
	}
}
	
