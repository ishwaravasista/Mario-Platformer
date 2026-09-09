import mayflower.*;
public class Koopa extends GravityAnimatedActor
{
    private boolean isHit = false;
    private boolean thrown = false;
    private boolean movingLeft = true;
    public Koopa()
    {
        setImage(new MayflowerImage ("img/shyguy.png"));
    }
    public void act()
    { 
        if(getX()>1000)
        {
            move(-1);
            return;
        }
        if(!isHit)
        {
            if(movingLeft)
            {
                move(-2);
            }
        }else{
            move(-1);
        }
        if(isTouching(Koopa.class)&&thrown)
        {
            Koopa enemy = getOneIntersectingObject(Koopa.class);
            World w = getWorld();
            w.removeObject(enemy);
        }
        super.act();        
    }
    
    public void flip()
    {
        if(movingLeft)
        {
            movingLeft = false;
            move(2);
        }else{
            movingLeft = true;
            move(-2);
        }
    }
    public void freeze()
    {
        setImage("img/frozenKoopa.png");
    }
    
    public boolean getIsHit()
    {
        return isHit;
    }
    
    public void push(boolean isLeft)
    {
        thrown = true;
        if(isLeft)
        {
            setXVelocity(-150);
        }else{
            setXVelocity(150);
        }

    }
    
    public void hit()
    {
        setImage("img/shyguyupsidedown.png");
        isHit = true;
        //getWorld().removeObject(this);
    }
}
