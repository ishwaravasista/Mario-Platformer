import mayflower.*;

 public class Victory extends World {
   
    public Victory() 
    {
        setBackground("img/victory.png");
        showText("YAY! you got "+ scoreTracker.getScore() +" points",100, 100 , Color.WHITE);
        showText("Press ENTER to play again", 50, 675, Color.BLACK);
        Mayflower.stopMusic("audio/world1.mp3");
        Mayflower.stopMusic("audio/world2.mp3");
        Mayflower.stopMusic("audio/world3.mp3");
        Mayflower.playMusic("audio/victory.mp3");
    }
  public void act()
  {
      if(Mayflower.isKeyDown( Keyboard.KEY_ENTER ))
        {
            Mayflower.setWorld(new EndScreenTwo());
        }
  }
}