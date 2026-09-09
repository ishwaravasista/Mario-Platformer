import mayflower.*;

 public class Intro extends World {
   
    public Intro() 
    {
        setBackground("img/intro.jpg");
        showText("Press space to continue!",300, 450 , Color.BLACK);
    }
  public void act()
  {
    if(Mayflower.isKeyDown( Keyboard.KEY_SPACE ))
    {
            Mayflower.setWorld(new MyWorld());
    }
  }
}