import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

class Player extends GameObject
{
    Game game;
    Handler handler;
    BufferedImage ship;
    public Player(int x, int y, ID id, Handler handler, Game game)
    {
        super(x, y, id);
        this.handler = handler;
        
    }
    
    public Rectangle getBounds()
    {
        return new Rectangle(x, y, 64, 64);
    }
    
    public void tick()
    {
        x += velX;
        y += velY;
        
        x = Game.clamp(x, 0, Game.WIDTH - 64);
        y = Game.clamp(y, 0, Game.HEIGHT - (64+32));
        
        collision();
    }
    
    public void collision()
    {
        //if(Game.State == Game.STATE.GAME)
        //{
            for(GameObject obj : handler.object)
            {
                if(obj.getID() == ID.Projectile)
                {
                    if(getBounds().intersects(obj.getBounds()))
                    {
                        HUD.HEALTH -= 15;
                        
                        obj.y=game.HEIGHT;
                    }
                }
                else if(obj.getID() == ID.Star)
                {
                    if(getBounds().intersects(obj.getBounds()))
                    {
                        HUD.HEALTH += 10;
                        obj.y=game.HEIGHT;
                    }
                }
            }
        //}
    }
    
    public void render(Graphics g)
    {
        try 
        {
            ship = ImageIO.read(new File("spr/ship.png"));
        } catch (IOException e) {}
        //if(id == ID.Player) g.setColor(Color.green);
        //else g.setColor(Color.blue);
        //g.fillRect(x, y, 32, 32);
        g.drawImage(ship, x, y, null);
    }
    
    
}