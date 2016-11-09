package act.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.LineBorder;

public class SLVWpane extends JPanel{
	/**
	 This is the input pane contains writing quiz and input pane.
	 */
	private static final long serialVersionUID = 11L;
	private JEditorPane editorPane = new JEditorPane();
	private JScrollPane scrollPane = new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private JLabel scoreLabel = new JLabel();
	int w = (int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH) ;
	 int h = (int)Math.floor(0.85*ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT);
	 
	public SLVWpane()
	{
		this.setSize(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.MAINCONTENT_HEIGHT );
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		
	}
	
	public void init(String s){

		editorPane.setPreferredSize(new Dimension(w,h));
		editorPane.setText(s);
		scrollPane.setPreferredSize(new Dimension(w,h));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//this.setPreferredSize(new Dimension (MyConstants.WRITING_INPUTPANE_WIDTH,MyConstants.WRITING_INPUTPANE_HEIGHT));
		this.setPreferredSize(new Dimension(w,h));
		this.setOpaque(false);
		BoxLayout thislayout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(thislayout);
		this.setVisible(true);
		//this.add(Box.createVerticalGlue());
		scoreLabel.setText("Writing Score:0.you stupid!");
		 scoreLabel.setPreferredSize(new Dimension((int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH),
				 (int)Math.floor(0.1*ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT)));
		this.add(scoreLabel);
		this.add(scrollPane);
		//this.add(Box.createVerticalGlue());
		
	}
	
};