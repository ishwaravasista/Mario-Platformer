import mayflower.*;

 public class EndScreenTwo extends World {
   
    public EndScreenTwo() 
    {
        setBackground("img/end2.jpg");
        showText("Press ENTER to play again", 290, 50, Color.WHITE);
        Mayflower.stopMusic("audio/end1.mp3");
        Mayflower.stopMusic("audio/victory.mp3");
        Mayflower.playMusic("audio/end2.mp3");
        
        
        
    }
  public void act()
  {
      if(Mayflower.isKeyDown( Keyboard.KEY_ENTER ))
        {
            Mayflower.setWorld(new MyWorld());
        }
      
  }
}