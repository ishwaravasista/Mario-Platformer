import mayflower.*;

 public class EndScreen extends World {
   
    public EndScreen() 
    {
        setBackground("img/EndScreen.png");
        Mayflower.stopMusic("audio/world1.mp3");
        Mayflower.stopMusic("audio/world2.mp3");
        Mayflower.stopMusic("audio/world3.mp3");
        Mayflower.playMusic("audio/end1.mp3");
        showText("BOO HOO!",300, 325 , Color.WHITE);
        showText("Press ENTER to play again", 290, 50, Color.WHITE);
        
    }
  public void act()
  {
      if(Mayflower.isKeyDown( Keyboard.KEY_ENTER ))
        {
            Mayflower.setWorld(new EndScreenTwo());
        }
  }
}