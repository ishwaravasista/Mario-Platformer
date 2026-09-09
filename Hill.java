import mayflower.*;
public class Hill extends Actor
{

    public Hill(String img)
    {
        setImage(img);
    }
    public void act()
    {
        move(-1);
    }
}
