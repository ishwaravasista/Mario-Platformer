import mayflower.*;

public class Mario extends MovableAnimatedActor {
    private int score;
    private int lives = 3;
    private Animation runRight;
    private Animation runLeft;
    private Animation jumpAnimation;

    public Mario(int world,int score) {
        setImage("img/mario/run (1).gif");
        score = score;
        // Initialize run right and run left animations
        MayflowerImage[] imageFiles = new MayflowerImage[25];
        MayflowerImage[] flippedImageFiles = new MayflowerImage[25];
        
        for (int i = 0; i <= 24; i++) {
            MayflowerImage myImage = new MayflowerImage("img/mario/run (" + (i + 1) + ").gif");
            myImage.scale(.55);
            imageFiles[i] = myImage;

            // Create a flipped copy for runLeft
            MayflowerImage flippedImage = new MayflowerImage("img/mario/run (" + (i + 1) + ").gif");
            flippedImage.scale(.55);
            flippedImage.mirrorHorizontally();
            flippedImageFiles[i] = flippedImage;
        }

        runRight = new Animation(imageFiles, 60);
        setWalkAnimation(runRight);

        runLeft = new Animation(flippedImageFiles, 60);
        setFlipWalkAnimation(runLeft);

        // Initialize jump animation
        MayflowerImage[] flippedJumpImages = new MayflowerImage[7];
        MayflowerImage[] jumpImages = new MayflowerImage[7];
        for (int i = 0; i <= 6; i++) {
            MayflowerImage myImage = new MayflowerImage("img/mario/jump/Jump (" + (i + 1) + ").gif");
            myImage.scale(.55);
            jumpImages[i] = myImage;
            MayflowerImage flippedImage = new MayflowerImage("img/mario/jump/Jump (" + (i + 1) + ").gif");
            flippedImage.scale(.55);
            flippedImage.mirrorHorizontally();
            flippedJumpImages[i] = flippedImage;
        }


        setJumpAnimation(new Animation(jumpImages, 60),new Animation(flippedJumpImages, 60));// Adjust frame rate as needed
        setWorld(world);
    }

    @Override
    public void act() {
        super.act();

        // If jumping, set the jump animation
        if (getIsJumping()) {
            setAnimation(jumpAnimation);
        } else {
            // Set the walk animation based on direction
            setAnimation(isFacingRight() ? runRight : runLeft);
        }
    }

    public void increaseScore() {
        score += 100;
        System.out.println("Score increased");
        getWorld().removeText(10, 30);
        getWorld().showText("Score: " + score + " Lives: " + lives, 10, 30, Color.WHITE);
        scoreTracker.setScore(score);
    }

    public void hit() {
        lives--;
        if (isTouching(GravityAnimatedActor.class)) {
            getWorld().removeObject(getOneIntersectingObject(GravityAnimatedActor.class));
        }
        if (lives == 0) {
            getWorld().removeObject(this);
            Mayflower.setWorld(new EndScreen());
            return;
        }
        getWorld().removeText(10, 30);
        getWorld().showText("Score: " + score + " Lives: " + lives, 10, 30, Color.WHITE);
        System.out.println("Mario hit");
    }

    private boolean isFacingRight() {
        // This method checks if Mario is currently facing right
        return Mayflower.isKeyDown(Keyboard.KEY_RIGHT);
    }
}
