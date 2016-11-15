package act.View;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;

import act.Controller.MainController;
import act.Model.*;
import java.awt.*;

public class MainChoicePane extends JPanel{
	/**
	 This is the mainChoicePane which contains title and options.
	 */
	private static final long serialVersionUID = 6L;
	private ChoicePane choicepane = new ChoicePane();	//the choice panel
	private JTextPane titlePane = new JTextPane();
	private JScrollPane scrollPane = new JScrollPane();
	private reading readingBrain;
	private math mathBrain;
	public MainChoicePane()
	{
		titlePane.setContentType("text/html");
	}
	
	public void init(int type){
		choicepane.init(type);
		if(type == ViewConstants.DISPALY_CHOICE_PANE_PART){
			scrollPane.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_PART_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
			//this.setMaximumSize(new Dimension(MyConstants.CHOICEPANE_PART_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
		else if(type == ViewConstants.DISPALY_CHOICE_PANE_WHOLE){
			scrollPane.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_WHOLE_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
			//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
			//this.setMaximumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
		titlePane.setText("<html><body>afsdddd<img src=\"7.gif\">fuck you son of<><table style=\"border: 1px solid black\"><tr><td>1</td><td>2</td></tr></table> bitchasd asd asdsadhsa kjdhks ahdksahdjk sahd jksahdkj ahsdkjashdkl sjadla sdhk asd</body></html>");
//		titlePane.setOpaque(false);
		titlePane.setBackground(Color.WHITE);
		titlePane.setFont(ViewConstants.choiceTitleFont);
		String bodyRule = "body {padding:10; font-family: " +  ViewConstants.choiceTitleFont.getFamily() + "; " +
	            "font-size: " + ViewConstants.choiceTitleFont.getSize() + "pt; }";
		
		HTMLDocument document = (HTMLDocument)titlePane.getDocument();
		document.getStyleSheet().addRule(bodyRule);
		
		this.setLayout(new BorderLayout());
		this.add(titlePane,BorderLayout.PAGE_START);
		
		//scrollPane = new JScrollPane(choicepane,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollPane.add(choicepane);
		//this.add(scrollPane,BorderLayout.CENTER);
		this.add(choicepane, BorderLayout.CENTER);
        if(type == ViewConstants.DISPALY_CHOICE_PANE_PART){
			this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
        	//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_PART_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
		else if(type == ViewConstants.DISPALY_CHOICE_PANE_WHOLE){
			this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_WHOLE_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
		
	}
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
		if(partIndex == ModelConstants.MATH){
			titlePane.setText(mathBrain.getChoice(MainController.questionIndexinSplit(questionIndex)).getQuestion());
		}
		else{
			titlePane.setText(readingBrain.getChoice(MainController.questionIndexinSplit(questionIndex)).getQuestion());
			
		}
		choicepane.init(questionIndex, splitIndex, partIndex);
		choicepane.resetChecked();
	}
	public void setReadingBrain(reading r){
		readingBrain = r;
		choicepane.setReadingBrain(r);
	}
	
	public void setMathBrain(math m){
		mathBrain = m;
		choicepane.setMathBrain(m);
	}
	
};