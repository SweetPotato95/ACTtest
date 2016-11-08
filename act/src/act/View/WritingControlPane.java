package act.View;

import javax.swing.*;
import javax.swing.border.LineBorder;

import act.Controller.MainController;
import javafx.scene.layout.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WritingControlPane extends JPanel{
	/**
	 This is the input pane contains writing quiz and input pane.
	 */
	//private static final long serialVersionUID = 11L;
	//private JEditorPane editorPane = new JEditorPane();
	//private JScrollPane scrollPane = new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JButton copy = new JButton();
	private JButton paste = new JButton();
	private JLabel count = new JLabel();
	private WritingInputPane writingInputPane;
	private String currentCopyText = "";
	public WritingControlPane()
	{
		count.setSize(100, 50);
		
		count.setBorder(new LineBorder(Color.black));
		count.setPreferredSize(new Dimension(100, 50));
		count.setHorizontalAlignment(JLabel.CENTER);
		count.setOpaque(false);
		
		copy.setText("Copy");
		copy.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				handleCopy();
			}
		});
		
		paste.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				handlePaste();
			}
		});
		paste.setText("Paste");
	}
	public void setWritingInputPane(WritingInputPane w){
		writingInputPane = w;
	}
	public void init(){
		this.setSize(ViewConstants.WRITING_INPUTPANE_WIDTH , ViewConstants.WRITING_CONTROLPANE_HEIGHT);
		//this.setLayout(new BorderLayout());
		//this.setOpaque(false);
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setPreferredSize(new Dimension(ViewConstants.WRITING_INPUTPANE_WIDTH , ViewConstants.WRITING_CONTROLPANE_HEIGHT));
		this.setOpaque(false);
		BoxLayout thislayout = new BoxLayout(this,BoxLayout.X_AXIS);
		this.setLayout(thislayout);
		this.setVisible(true);
		this.add(copy);
		//this.add(Box.createHorizontalGlue());
		this.add(paste);
		this.add(Box.createHorizontalStrut(10));
		this.add(Box.createHorizontalGlue());
		this.add(count);
		count.setText("Word Count: 0");
		//this.add(Box.createVerticalGlue());
		
	}
	
	public void handleCopy(){
		currentCopyText = writingInputPane.getSelectedText();
//		System.out.println(currentCopyText);
	}
	public void handlePaste(){
		writingInputPane.appendCopyText(currentCopyText);
	}
	public void setCountLable(String text){
		count.setText(text);
	}
};