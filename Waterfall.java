import mayflower.*;
public class Waterfall extends Actor
{

    public Waterfall()
    {
        setImage("img/waterfallblock.png");
    }
    public void act()
    {
        move(-1);
    }

}
