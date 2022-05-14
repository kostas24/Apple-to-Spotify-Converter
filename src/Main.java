import javax.swing.JFrame;

/**
   This program displays the growth of an investment with variable interest.
*/
public class Main
{  
	
   public static void main(String[] args)
   {  
  
      JFrame frame = new NameFrame();
      //The setDefaultCloseOperation() method is used to specify one of several options for the close button. 

      //JFrame.EXIT_ON_CLOSE — Exit the application.
      //JFrame.HIDE_ON_CLOSE — Hide the frame, but keep the application running.
      //JFrame.DISPOSE_ON_CLOSE — Dispose of the frame object, but keep the application running.
      //JFrame.DO_NOTHING_ON_CLOSE — Ignore the click.
   
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
     
   }
}

//EXAMPLE INPUT: https://music.apple.com/us/album/ride-it/1472026375?i=1472026722

