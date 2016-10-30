package act.View;

import javax.swing.*;
import java.awt.*;

import act.Model.*;

public class ChoiceView extends JPanel{
	/**
	 This is the ChoiceView contains mainChoicePane. 
	 This is for the mainView showing, a layer of mainContent. 
	 */
	private static final long serialVersionUID = 3L;
	private MainChoicePane mainchoicepane = new MainChoicePane();
	public ChoiceView(){
		this.setSize(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.CHOICEPANE_HEIGHT );
		//this.setPreferredSize(new Dimension(MyConstants.MAINCONTENT_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		this.setMinimumSize(new Dimension(ViewConstants.MAINCONTENT_WIDTH , ViewConstants.CHOICEPANE_HEIGHT));
		this.setLayout(new BorderLayout());
		this.setVisible(true);
	}
	
	public void init(){
		mainchoicepane.init(ViewConstants.DISPALY_CHOICE_PANE_WHOLE);
   		this.add(mainchoicepane, BorderLayout.CENTER);
	}

	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
		System.out.println("DEBUG INFO: ChoiceView requestUpdate " +questionIndex);
		mainchoicepane.requestUpdate(questionIndex, splitIndex, partIndex);
	}
	public void setReadingBrain(reading r){
		mainchoicepane.setReadingBrain(r);
	}
	public void setMathBrain(math m){
		mainchoicepane.setMathBrain(m);
	}
	
}