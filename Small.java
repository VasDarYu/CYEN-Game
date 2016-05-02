import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
class Small extends GameObject
{
    private Game game;
    private Handler handler;
    private BufferedImage fireball;
    //public  boolean gone = false;
    public Small(int x, int y, ID id, Handler handler, int vertical, int horizontal, Game game)
    {
        super(x, y, id);
        this.handler = handler;
        this.game=game;
        velX = horizontal;
        velY = vertical;
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 16, 16);
    }
    
    public void tick()
    {
        x += velX;
        y += velY;
        
        //x = Game.clamp(x, 0, Game.WIDTH - 37);
        //y = Game.clamp(y, 0, Game.HEIGHT  - 60);
        if(y >= Game.HEIGHT) 
        {
            x= game.randomGenerator(16, game.WIDTH-16);
            y=0;
            
            velX = game.randomGenerator(1, 5);
            velY = game.randomGenerator(1, 5);
          }
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;
    }
    public void render(Graphics g)
    {
        try 
        {
            fireball = ImageIO.read(new File("spr/fireball.png"));
        } catch (IOException e) {System.out.println("This is bullshit:" + e);}
        //if(id == ID.Player) g.setColor(Color.green);
        //else g.setColor(Color.blue);
        //g.fillRect(x, y, 32, 32);
        g.drawImage(fireball, x, y, null);
    }
    
}