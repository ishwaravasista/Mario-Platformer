import mayflower.*;
public class GravityAnimatedActor extends AnimatedActor
{
    private static final double GRAVITY = -70;
    private static double FRICTION = -35;
    private static final double frameRate = 60;
    private static final double deltaTime = 0.1;
    private static final double MOVE_SPEED = 50;
    private static final double JUMP_VELOCITY = 125;
    private double velocityX = 0;
    private double velocityY = 0;
    private boolean isJumping = false;
    private int world = 0;
    public GravityAnimatedActor()
    {
    }
    public void act()
    {

        applyPhysics(false);
        if(getY()>800)
        {
            if(this instanceof Mario)
            {
                Mayflower.setWorld(new EndScreen());
            }
            getWorld().removeObject(this);
        }
        super.act();
    }
    public void setWorld(int newWorld)
    {
        world = newWorld;
        FRICTION = newWorld ==2?-10:-35;
    }
    public boolean getIsJumping()
    {
        return isJumping;
    }
    public void jump() {
            velocityY = JUMP_VELOCITY;
            isJumping = true;
            while(isTouching(StaticActor.class))
            {
                setLocation(getX(),getY()-1);
            }
    }
    public void setXVelocity(int newX)
    {
        velocityX = newX;
    }
    public void setYVelocity(int newY)
    {
        velocityY = newY;
    }
    public void moveLeft() {
        velocityX-=5;
        if(velocityX<-50)
        {
            velocityX = -MOVE_SPEED;
        }
    }

    public void moveRight() {
        velocityX+=5;
        if(velocityX>50)
        {
            velocityX = MOVE_SPEED;
        }
    }

    public void applyPhysics(boolean bounce) {
        velocityY += (GRAVITY * deltaTime);
        if(isTouching(StaticActor.class))
        {
            if(velocityX>0)
            {
                velocityX += (FRICTION * deltaTime);
                if(velocityX<0)
                {
                    velocityX = 0;
                }
            }else if(velocityX<0)
            {
                velocityX -= (FRICTION * deltaTime);
                if(velocityX>0)
                {
                    velocityX = 0;
                }
            }
        }
        updateMarioPosition();
    }
    private void putDown(boolean bounce) {
        touchObstacle(bounce);
        touchKoopa();
        climbVine();
        touchCherry();
        nextWorld();
    }
    private void nextWorld()
    {
        if(this instanceof Mario && getY()<0&& world ==0)
        {
            Mayflower.setWorld(new World2());
        }else if(this instanceof Mario && getY()<0&& world ==1)
        {
            Mayflower.setWorld(new World3());
        }else if(this instanceof Mario && getY() < 0 && world == 2)
        {
            Mayflower.setWorld(new Victory());
        }
    }
    private void touchCherry()
    {
        if(isTouching(Cherry.class)&& this instanceof Mario)
        {
            Mayflower.playMusic("audio/cherry.mp3");
            getWorld().removeObject(getOneIntersectingObject(Cherry.class));
            this.increaseScore();
        }
    }
    
    private void climbVine()
    {
        if(isTouching(Vine.class)&& this instanceof Mario)
        {
            if(Mayflower.isKeyDown( Keyboard.KEY_DOWN)&& !isTouching(StaticActor.class))
            {
                setLocation(getX(),getY()+1);
            }else if(Mayflower.isKeyDown( Keyboard.KEY_UP))
            {
                setLocation(getX(),getY()-1);
            }
            if(Mayflower.isKeyDown( Keyboard.KEY_RIGHT))
            {
                move(1);
            }else if(Mayflower.isKeyDown( Keyboard.KEY_LEFT))
            {
                move(-1);
            }
            move(-1);
            velocityX = 0;
            velocityY = 0;
        }
    }
    
    private void touchObstacle(boolean bounce)
    {
        if (isTouching(StaticActor.class)) {
            Actor intersectingObject = getOneIntersectingObject(StaticActor.class);  // get the object we are touching
            int objectTopY = intersectingObject.getY();  // the Y position of the object we are touching
            int objectBottomY = objectTopY + intersectingObject.getHeight();
            int objectLeftX = intersectingObject.getY();// object's bottom Y coordinate
            int objectRightX = objectLeftX +intersectingObject.getWidth();
            int actorBottomY = getY() + getHeight();
            int actorTopY = getY();
            int actorLeftX = getX();
            int actorRightX = getWidth()+getX();// actor's bottom Y coordinate
            // Check if the actor is landing on top of the object
            if ((actorBottomY <= objectTopY + 1)&&velocityY<0) {
                if (bounce) {
                    velocityY = -velocityY;  // Reverse velocity for bouncing
                }else{
                    velocityY = 0; 
                }// Allow a margin for floating point issues
                isJumping = false;  // Not jumping anymore
                //setLocation(getX(), objectTopY - getHeight());  // Snap to the top of the object
            } else if (actorTopY >= objectBottomY - 1 && velocityY > 0) {  // Check if hitting the bottom of the object
                    if(intersectingObject instanceof HillTop && velocityY>0)
                    {
                        return;
                    }
                    velocityY = -velocityY;
            }            
        }
    }
    
    private void touchKoopa()
    {
        if(isTouching(Koopa.class)&& this instanceof Mario)
        {
            Koopa enemy = getOneIntersectingObject(Koopa.class);
            if(enemy.getIsHit())
            {
                enemy.push(enemy.getX()<getX());
            }
            else if(getY()+getHeight()<=enemy.getY()+1)
            {
                enemy.hit();
                jump();
            }else
            {
                this.hit();
            }

        }   
    }
    public void hit()
    {

    }
    public void increaseScore()
    {
        
    }
    private void updateMarioPosition() {
        goUp();
        goRight();

    }

    private void goUp() {
            for(int i = 0;i<Math.abs(velocityY*deltaTime);i++)
            {
                putDown(false);
                if(velocityY>0)
                {
                    setLocation(getX(),getY()-1);    
                }else if(velocityY<0)
                {
                    setLocation(getX(),getY()+1);    
                }
                else{
                    return;
                }
            }
        //Thread t1 = new Thread();

    }
    private void goRight() {

            for(int i = 0;i<Math.abs(velocityX*deltaTime);i++)
            {
                putDown(false);
                if(velocityX>0)
                {
                    setLocation(getX()+1,getY());    
                }else if(velocityX<0)
                {
                    setLocation(getX()-1,getY());
                }else{
                    return;
                }
            }
        //setLocation(getX()+distance,getY());
    }
}
