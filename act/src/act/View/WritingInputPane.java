package act.View;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

import act.Controller.MainController;
import act.Model.ModelConstants;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.StringTokenizer;


public class WritingInputPane extends JPanel{
	/**
	 This is the input pane contains writing quiz and input pane.
	 */
	private static final long serialVersionUID = 11L;
	private JEditorPane editorPane = new JEditorPane();
	private JScrollPane scrollPane = new JScrollPane(editorPane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	private WritingControlPane writingControlPane = new WritingControlPane();
	public WritingInputPane()
	{
		this.setSize(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.MAINCONTENT_HEIGHT );
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
		writingControlPane.setWritingInputPane(this);
	}
	
	public void init(){
	
		editorPane.getDocument().addDocumentListener(new DocumentListener(){
			
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				getCount();
				MainController.setText(getText());
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				getCount();
				MainController.setText(getText());
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				getCount();
				MainController.setText(getText());
			}
			
		});
		editorPane.addKeyListener(new KeyAdapter(){
			@Override
			public void keyPressed(KeyEvent e){
				if(e.getKeyCode() == KeyEvent.VK_C && (e.getModifiers() & KeyEvent.CTRL_MASK)==KeyEvent.CTRL_MASK){
					writingControlPane.handleCopy();
					e.consume();
				}
				if(e.getKeyCode() == KeyEvent.VK_V && (e.getModifiers() & KeyEvent.CTRL_MASK)==KeyEvent.CTRL_MASK){
					writingControlPane.handlePaste();
					e.consume();
				}
			}
		});
		editorPane.setPreferredSize(new Dimension (ViewConstants.WRITING_INPUTPANE_WIDTH,ViewConstants.WRITING_EDITORPANE_HEIGHT));
				scrollPane.setPreferredSize(new Dimension (ViewConstants.WRITING_INPUTPANE_WIDTH,ViewConstants.WRITING_EDITORPANE_HEIGHT));
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
		writingControlPane.init();
		
		
		//this.setPreferredSize(new Dimension (MyConstants.WRITING_INPUTPANE_WIDTH,MyConstants.WRITING_INPUTPANE_HEIGHT));
		this.setPreferredSize(new Dimension(500,500));
		this.setOpaque(false);
		BoxLayout thislayout = new BoxLayout(this,BoxLayout.Y_AXIS);
		this.setLayout(thislayout);
		this.setVisible(true);
		//this.add(Box.createVerticalGlue());
		this.add(writingControlPane);
		this.add(scrollPane);
		//this.add(Box.createVerticalGlue());
		
	}
	public String getSelectedText(){
		editorPane.grabFocus();
		return editorPane.getSelectedText();
	}
	public void appendCopyText(String s){
		try {
			editorPane.getDocument().insertString(editorPane.getCaretPosition(), s, null);
			editorPane.grabFocus();
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getCount(){
		String text = editorPane.getText();
		String[] sp = text.split("\\r\\n| ");
		int count = 0;
		for(int i=0;i<sp.length;i++){
			
			if(!sp[i].contentEquals("") && !sp[i].contentEquals(" ")){// empty string
//				System.out.println("--"+sp[i]+"--");
				count++;
			}
		}
		
		
//		System.out.println(count);
	    writingControlPane.setCountLable("Word Count: "+Integer.toString(count));
	}
	public String getText(){
		return editorPane.getText();
	}
};