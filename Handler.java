import java.util.ArrayList;
import java.awt.Graphics;

class Handler
{
    ArrayList<GameObject> object = new ArrayList<GameObject>();
    Game game;
    public int count;
    public void tick()
    {
        for (GameObject obj : object) 
        {
            obj.tick();
            count();
        }
    }
    public void render(Graphics g)
    {
        for (GameObject obj : object) obj.render(g);
    }
    public void addObject(GameObject obj)
    {
        this.object.add(obj);
        //object.add(obj);
    }
    public void removeObject(GameObject obj)
    {
        this.object.remove(obj);
        //object.remove(obj);
    }
    private void count()
    {
        int i = 0;
        for(GameObject obj : object)
        {
            if(obj.gone==true)
            {
                removeObject(obj);
            }
            else
            {
                i++;
            }
        }
        //System.out.println(i);
        count = i;
    }
    public void reset()
    {
        for(GameObject obj : object)
        {
            if(obj.id!=ID.Player)obj.y=game.HEIGHT;
        }
    }
}