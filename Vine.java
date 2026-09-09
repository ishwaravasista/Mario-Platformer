import mayflower.*;
public class Vine extends Actor
{
    
    public Vine()
    {
        setImage("img/vineblock.png");
    }
    public void act()
    {
        move(-1);
    }
}