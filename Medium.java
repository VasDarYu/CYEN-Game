import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Medium extends GameObject
{
    private Game game;
    private Handler handler;
    private BufferedImage zap;
    //public  boolean gone = false;
    public Medium(int x, int y, ID id, Handler handler, int vertical, int horizontal, Game game)
    {
        super(x, y, id);
        this.handler = handler;
        this.game = game;
        velX = horizontal;
        velY = vertical;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 32, 32);
    }
    
    public void tick()
    {
        x += velX;
        y += velY;
        
        //x = Game.clamp(x, 0, Game.WIDTH - 37);
        //y = Game.clamp(y, 0, Game.HEIGHT - 60);
        if(y >= Game.HEIGHT) 
		{
		    x= game.randomGenerator(32, game.WIDTH-32);
		    y=0;
		    
            velX = game.randomGenerator(1, 5);
            velY = game.randomGenerator(1, 5);
		  }
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
    }
    public void render(Graphics g)
    {
        try 
        {
            zap = ImageIO.read(new File("spr/zap.png"));
        } catch (IOException e) {System.out.println("This is bullshit:" + e);}
        //if(id == ID.Player) g.setColor(Color.green);
        //else g.setColor(Color.blue);
        //g.fillRect(x, y, 32, 32);
        g.drawImage(zap, x, y, null);
    }
}