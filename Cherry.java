import mayflower.*;

public class Cherry extends Actor
{
    private Animation walk;
    public Cherry() 
    {
        setImage("img/cherry.png");
    }
    public void act()
    {
        move(-1);
    }
}
