import mayflower.*;

public class MovableAnimatedActor extends GravityAnimatedActor {
    private boolean facingLeft;
    private Animation walkRightAnimation;
    private Animation walkLeftAnimation;
    private Animation jumpAnimation;
    private Animation flippedJumpAnimation;
    public MovableAnimatedActor() {
        facingLeft = true;
    }

    public void setWalkAnimation(Animation a) {
        walkRightAnimation = a;
    }

    public void setFlipWalkAnimation(Animation a) {
        walkLeftAnimation = a;
    }

    public void setJumpAnimation(Animation a,Animation b) {
        jumpAnimation = a;
        flippedJumpAnimation = b;
    }

    @Override
    public void act() {
        // Determine movement based on keyboard input
        if (Mayflower.isKeyDown(Keyboard.KEY_RIGHT) && (getWidth() + getX() != 800)) {
            facingLeft = false;
            moveRight();
        } else if (Mayflower.isKeyDown(Keyboard.KEY_LEFT) && (getX() != 0)) {
            facingLeft = true;
            moveLeft();
        } if (Mayflower.isKeyDown(Keyboard.KEY_UP) && (getY() != 0) && !getIsJumping()) {
            if (!getIsJumping() && isTouching(StaticActor.class) && getOneIntersectingObject(Actor.class).getY() >= getY()) {
                jump();
            }
        }

        // Apply the appropriate animation
        if (getIsJumping()) {
            setAnimation(facingLeft?jumpAnimation:flippedJumpAnimation); // Use jump animation when in the air
        } else {
            // Use walk animations based on direction
            setAnimation(facingLeft ? walkRightAnimation : walkLeftAnimation);
        }

        super.act();
    }
        
}
