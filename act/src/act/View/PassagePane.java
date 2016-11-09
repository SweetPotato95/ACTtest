package act.View;

import javax.swing.*;

import act.Model.*;
import java.awt.Dimension;
import java.awt.Font;
import java.io.*;
import javax.swing.event.*;
import javax.swing.text.html.*;

public class PassagePane extends JPanel{
	/**
	 This is the passagePane with scroll. 
	 */
	private static final long serialVersionUID = 8L;
	JScrollPane scrollPane;
	private JTextPane passagepane = new JTextPane();
	private reading readingBrain;
	private File passageFile;
	public PassagePane()
	{
		scrollPane = new JScrollPane(passagepane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setVisible(true);
	}
	
	public void init(int w,int h){
		//passagepane.setSize(myConstants.mainpanel_width, myConstants.mainpanel_height);
		passagepane.setVisible(true);
		passagepane.setContentType("text/html");
		passagepane.setOpaque(false);
		Font font = new Font("Segoe UI",Font.BOLD,100);
		HTMLEditorKit kit = (HTMLEditorKit)passagepane.getEditorKit();
		String bodyRule = "body { font-family: " + font.getFamily() + "; " +
	            "font-size: " + font.getSize() + "pt; }";
		((HTMLDocument)passagepane.getDocument()).getStyleSheet().addRule(bodyRule);
        kit.setAutoFormSubmission(false);
		passageFile = new File("D://test.html"); 
			//passagepane.setPage(passageFile.toURI().toURL());
		passagepane.setText("<html><body><h1>haha</h1><p>fuckfuck</p></body></html>");
		
		scrollPane.setPreferredSize(new Dimension(w,h));
		this.setPreferredSize(new Dimension(w,h));
        this.add(scrollPane);		
	}
	public void setText(String s){
		passagepane.setText("<html><body>"+s+"</body></html>");
		return;
	}
	public void setReadingBrain(reading r){
		readingBrain = r;
	}
	
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
		passagepane.setText("<html><body>"+readingBrain.getPassage()+"</body></html>");
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() { 
			       scrollPane.getVerticalScrollBar().setValue(0);
			       scrollPane.revalidate();
			   }
			});
		
		
	}
};