package act;

import act.View.*;
import java.awt.*; 
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;  

public class welcome extends JFrame implements MouseListener
{
	private JLabel l;
	private ImageIcon image;
	private String pdfpath;
	public welcome(String filename, int waitTime){ 
		this.setTitle("ACT Practice");
		this.setSize(800, 600);
		image = new ImageIcon(filename);
		image.setImage(image.getImage().getScaledInstance(800,600,Image.SCALE_DEFAULT));
		l = new JLabel(image);
		l.addMouseListener(this);
		String path = new File(".").getAbsolutePath();
		path = path.substring(0,path.length()-1);
		path = path.replace('\\', '/');
		path = path.replace(" ", "%20");
		pdfpath = "file:///"+path+"resources/lib/xch.pdf";
		JLabel hint = new JLabel("点击下方图片有惊喜");
		hint.setFont(new Font("Microsoft Yahei",Font.PLAIN,15));
		this.add(hint,BorderLayout.PAGE_START);
		this.add(l, BorderLayout.CENTER); 

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

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			URI uri = new URI(pdfpath);
			Desktop.getDesktop().browse(uri);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	} 
		
}
