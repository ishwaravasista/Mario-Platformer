import mayflower.*;

public class Animation

{
    private int currentFrame;
    private MayflowerImage[] frames;
    private Timer animationTimer;
    private boolean isFlipped = false;
    public Animation(MayflowerImage[] actorFrames,int fps)
    {
        currentFrame = 0;
        frames = actorFrames;
        animationTimer = new Timer(1000000*fps);
    }
    
    public void mirrorHorizontally()
    {
        for(int i = 0; i < frames.length; i++)
            frames[i].mirrorHorizontally();
    }
    
    private void incrementFrame()
    {
        currentFrame++;
        if(currentFrame==frames.length)
        {
            currentFrame = 0;
        }
    }
    public void flip()
    {
        isFlipped = false;
    }
    public MayflowerImage getNextFrame()
    {
        if( animationTimer.isDone() )
        {
            animationTimer.reset();
            incrementFrame();
            if(isFlipped)
            {
                frames[currentFrame].mirrorHorizontally();
            }
        }
        return frames[currentFrame];
    }
}
