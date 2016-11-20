package act;

import act.View.*;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;  

public class welcome extends JFrame{
	public welcome(String filename, int waitTime){ 
		this.setTitle("ACT Practice");
		this.setSize(600, 400);
		JLabel l = new JLabel(new ImageIcon(filename));
		getContentPane().add(l, BorderLayout.CENTER); 

		final int pause = waitTime; 
		final Runnable closerRunner = new Runnable(){ 
			public void run(){ 
			setVisible(false); 
		    dispose();
		    MainActivity mainActivity = new MainActivity();
			mainActivity.setSize(Toolkit.getDefaultToolkit().getScreenSize());
			mainActivity.addWindowListener(new WindowAdapter(){
					@Override
					public void windowClosing(WindowEvent we){
						System.exit(0);
					}
			});
		   } 
		}; 

		Runnable waitRunner = new Runnable(){ 
			public void run(){ 
		    try{ 
		     Thread.sleep(pause); 
		     //invoke closerRunner and wait for waitRunner run. 
		     SwingUtilities.invokeAndWait(closerRunner); 
		    }catch(Exception e){ 
		     e.printStackTrace(); 
		    } 
		   } 
		  };
		  setVisible(true); 
		  Thread waitThread = new Thread(waitRunner, "welcome"); 
		  waitThread.start(); 
		} 
		
}
