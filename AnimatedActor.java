import mayflower.*;
public class AnimatedActor extends Actor
{
    private Animation currentAnimation;
    public AnimatedActor()
    {
    }
    public void setAnimation(Animation a)
    {
     currentAnimation =  a;
    }
    public void act()
    {
        if(currentAnimation!=null)
        {
            setImage(currentAnimation.getNextFrame());
        }
    }
}
