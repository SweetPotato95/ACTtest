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
	private static TestBasicInfo basicInfo;
	public PassagePane()
	{
		basicInfo = new TestBasicInfo();
		scrollPane = new JScrollPane(passagepane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setVisible(true);
	}
	
	public void init(int w,int h){
		passagepane.setVisible(true);
		passagepane.setContentType("text/html");
		passagepane.setOpaque(false);
		Font font = new Font("Segoe UI",Font.BOLD,19);
		HTMLEditorKit kit = (HTMLEditorKit)passagepane.getEditorKit();
		String bodyRule = "body { font-family: " + font.getFamily() + "; " +
	            "font-size: " + font.getSize() + "pt; }";
		((HTMLDocument)passagepane.getDocument()).getStyleSheet().addRule(bodyRule);
        kit.setAutoFormSubmission(false);
		passageFile = new File("D://test.html"); 
		passagepane.setText("<html><body><h1>haha</h1><p>fuckfuck</p></body></html>");
		
		scrollPane.setPreferredSize(new Dimension(w,h));
		this.setPreferredSize(new Dimension(w,h));
        this.add(scrollPane);		
	}
	
	public void setReadingBrain(reading r){
		readingBrain = r;
	}
	
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
//		System.out.println("DEBUG INFO: passagePane.requestUpdate "+ questionIndex);
		int questionId = basicInfo.questionIndexinSplit(questionIndex);
		String passagetext = readingBrain.getPassage();
		questionId += 1;
		String substring = "<font id="+questionId+">";
		passagetext = passagetext.replace(substring, "<font style=\"background-color:yellow;\">");
//		System.out.println("DEBUG INFO: passagePane.requestUpdate "+ passagetext);
		passagepane.setText("<html><body>"+passagetext+"</body></html>");
		int height = scrollPane.getVerticalScrollBar().getValue();
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			   public void run() {
			       scrollPane.getVerticalScrollBar().setValue(height);
			       scrollPane.revalidate();
			   }
			});
		
		
	}
};