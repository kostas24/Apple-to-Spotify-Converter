import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.regex.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NameFrame extends JFrame{

	   private static final int FRAME_WIDTH = 450;
	   private static final int FRAME_HEIGHT = 100;
	   
	   private JLabel tLabel;
	   private JTextField tField;
	   private JButton button;
	   private String url = "";
	   
	   public NameFrame()
	   {  
	      createTextField();
	      createButton();
	      createPanel();
	      setSize(FRAME_WIDTH, FRAME_HEIGHT);
	   }

	   class AddInterestListener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent event)
	      {
	    	 
	    		  url = tField.getText();
	    		  try {	   	
	    		  String stringUrl = url.toString();
	    		  Document doc = Jsoup.connect(stringUrl).get(); //might need to use stringUrl instead of URL
	    		  boolean isFound = stringUrl.contains("music.apple.com");
	    		  if(isFound) // apple - > Spotify
	    		  {
	    			  Desktop.getDesktop().browse(URI.create(url));    
	    			
	    			  ArrayList<String> songName = new ArrayList<String>();
	    			  ArrayList<String> artistNames = new ArrayList<String>();
	    			
	    			 // Elements song = doc.select("div.product-page-header__metadata.album-header-metadata");
	    			 //Elements title = doc.select("h1.product-name.typography-large-title-semibold.clamp-4");
	    			 // System.out.println(doc.body().text());
	    			  stringUrl = URLDecoder.decode(new String(stringUrl.getBytes("ISO-8859-1"), "UTF-8"), "UTF-8"); //fix for songs with accent marks
	    			  String regexUrl = Pattern.quote("https://music.apple.com/us/album/") + "(.*?)" + Pattern.quote("/");
	    			  Pattern pattern = Pattern.compile(regexUrl);
	    			  Matcher matcher = pattern.matcher(stringUrl);
	    			  
	    			  if (matcher.find()) {
	    			  	songName.add(matcher.group(1).trim().toString().replaceAll("-", " "));
	    			  	System.out.println(songName.get(0));
	    			  }	  
	    			  Elements artistList = doc.select("div.product-creator.typography-large-title");
	    			 // String test = doc.body().text();
	    			 // System.out.println(test);
	    		
	    			  Pattern pattern2;
	    			  Matcher matcher2;
	    			  //use iterator?
	    			  
	    			  String stringArtistList = URLDecoder.decode(new String(artistList.toString().getBytes("ISO-8859-1"), "UTF-8"), "UTF-8"); //fix for artists with accent marks
	    			  
	    				 String reg = Pattern.quote("artist/") + "(.*?)" + Pattern.quote("/");
		    			  pattern2 = Pattern.compile(reg);
		    			  matcher2 = pattern2.matcher(stringArtistList);	
		    			//  System.out.println(artistList);
		    			  //regex (?<=\[)(.*?)(?=\]) - get between 2 brackets
		    			  // "(?<=\>)(.*?)(?=\<)"
	    			
		    			  int count =0;		   
		    			  while (matcher2.find()) {
		    			  	artistNames.add(matcher2.group(1).trim().toString().replaceAll("-", " "));
		    			  	System.out.println(artistNames.get(count));
		    			  	count++;
		    			  
		    			  }
		    			  TimeUnit.SECONDS.sleep(1);
		    			String [] keys = {"VK_CONTROL", "VK_W"};
		    	        sendKeysCombo(keys);

		    			  Desktop.getDesktop().browse(URI.create("https://open.spotify.com/search"));  
		    		/*	  
		    			  int x = 0;
		    			  int y = 0;
		    			  
		    			    Robot bot = new Robot();
		    			    bot.mouseMove(x, y);    
		    			    bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		    			    bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		    			  
		    		  */
	    		  }
	    		  
	    		  else //Spotify - > apple
	    		  {
	    			  Desktop.getDesktop().browse(URI.create(url));
	    		  }

	    	    } catch (IOException e) {
	    	        System.out.println(e.getMessage());
	    	    } catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	      }
	   }
	    		  /*
	    		  catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	      }            
	   } 
	   
	   private static void sendMouseClicks(String click[]) throws AWTException {
		   Robot mouseBot = new Robot();
	   }
	    		  */
	   private static void sendKeysCombo(String keys[]) {
	        try {

	            Robot robot = new Robot();

	            Class<?> cl = KeyEvent.class;

	            int [] intKeys = new int [keys.length];

	            for (int i = 0; i < keys.length; i++) {
	                Field field = cl.getDeclaredField(keys[i]);
	                intKeys[i] = field.getInt(field);
	                robot.keyPress(intKeys[i]);
	            }

	            for (int i = keys.length - 1; i >= 0; i--)
	                robot.keyRelease(intKeys[i]);
	        }
	        catch (Throwable e) {
	            System.err.println(e);
	        }
	    }
	   
	   private void createTextField()
	   {		  
	      tLabel = new JLabel("Enter Apple URL(include https://): ");
	      final int FIELD_WIDTH = 10;
	      tField = new JTextField(FIELD_WIDTH);
	      tField.setText(url);	      
	   }
	   
	   // create a button
	   private void createButton()
	   {
	      button = new JButton("Enter");
	      ActionListener listener = new AddInterestListener();
	      button.addActionListener(listener);
	   }

	   //create a panel
	   private void createPanel()
	   {
	      JPanel panel = new JPanel();
	      panel.add(tLabel);
	      panel.add(tField);
	      panel.add(button);
	      // adds to current object
	      add(panel);
	   } 
	   
}
