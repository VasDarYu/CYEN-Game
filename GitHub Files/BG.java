import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class BG
{
   private int x;
   private int y;
   Game game;
   private BufferedImage bg;
   private SpriteSheet ss;
   private BufferedImageLoader loader;
   
   
   public BG(int x, int y, Game game)
    {
        this.x=x;
        this.y=y;
        this.game=game;
        
        ss = new SpriteSheet(game.getSpriteSheet());
        bg = ss.grabImage(1, 1, 750, 525);
        
        
        
   }
   
   public void render(Graphics g)
   {
       g.drawImage(bg, x, y, null);
       //g.drawImage(animation.getSprite(), (int)x, (int)y, null);
       //animation.start();
       //g.drawImage(animation2.getSprite(), (int)x+117, (int)y, null);
       //animation2.start();
   }
}
