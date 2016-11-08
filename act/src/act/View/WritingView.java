package act.View;

import javax.swing.*;

import act.Model.ModelConstants;

import java.awt.*;


public class WritingView extends JPanel{
	/**
	 This is the writingView, one layer of mainContent.
	 */
	private static final long serialVersionUID = 12L;
	private WritingInputPane writingInputPane = new WritingInputPane();	//use for minimal the choice panel size
	private PassagePane passagePane = new PassagePane();	//passage panel
	public WritingView()
	{
		this.setSize(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.MAINCONTENT_HEIGHT );
		this.setLayout(new BorderLayout());
		this.setOpaque(false);
	}
	
	public void init(){
		passagePane.init(ViewConstants.WRITING_PASSAGEPANE_WIDTH, ViewConstants.WRITING_PASSAGEPANE_HEIGHT);
		writingInputPane.init();
		this.setSize(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.PASSAGEPANE_HEIGHT+20 );
		//this.setPreferredSize(new Dimension(MyConstants.MAINCONTENT_WIDTH , MyConstants.MAINCONTENT_HEIGHT));
		this.setPreferredSize(new Dimension(ViewConstants.MAINCONTENT_WIDTH,ViewConstants.WRITING_INPUTPANE_HEIGHT));
		//this.setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(passagePane);
		this.add(writingInputPane);
		this.setVisible(true);
		
	}
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
   		passagePane.requestUpdate(questionIndex, splitIndex, partIndex);
   	}
	public String getText(){
		return writingInputPane.getText();
	}
	
//
//   	public void init(){
//   		
//   		mainchoicepane.init(MyConstants.DISPALY_CHOICE_PANE_PART);
//   		passagepane.init();
//   		
//   		this.setPreferredSize(new Dimension(MyConstants.PASSAGEVIEW_WIDTH, MyConstants.PASSAGEVIEW_HEIGHT));
//   		this.add(mainchoicepane, BorderLayout.WEST);
//		this.add(passagepane, BorderLayout.CENTER);
//   	}
};