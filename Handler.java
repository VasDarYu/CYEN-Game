import java.util.LinkedList;
import java.awt.Graphics;

class Handler
{
    LinkedList<GameObject> object = new LinkedList<GameObject>();
    Game game;
    public void tick()
    {
        for (GameObject obj : object) obj.tick();
    }
    public void render(Graphics g)
    {
        for (GameObject obj : object) obj.render(g);
    }
    public void addObject(GameObject object)
    {
        this.object.add(object);
    }
    public void removeObject(GameObject object)
    {
        this.object.remove(object);
    }
    
}