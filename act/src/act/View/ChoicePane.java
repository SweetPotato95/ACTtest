package act.View;

import javax.swing.*;

import act.Controller.MainController;
import act.Model.*;
import java.awt.*;




public class ChoicePane extends JPanel{
	/**
	 This is the choice pane, only the options are in this pane.
	 Not include titles.
	 */
	private static final long serialVersionUID = 2L;
	private reading readingBrain;
	private math mathBrain;
	private int choiceSize = 5;
	private SingleChoicePane[] choicelist = new SingleChoicePane[choiceSize];
	private int curIndex = 0;
	private JLabel tmp1 = new JLabel();
	public void choicePane(){
				
	}
	
	public void init(int type){
		for(int i=0;i<choiceSize;i++){
			//System.out.println(i);
			choicelist[i] = new SingleChoicePane();
			//choicelist.add(new SingleChoicePane());
		}
		this.setBackground(Color.WHITE);
		
//		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		int height = this.getHeight();
		if(type == ViewConstants.DISPALY_CHOICE_PANE_PART){
			//this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,height));
			for(int i=0;i<choiceSize;i++){
				this.add(choicelist[i]);
			}
			this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
			this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
			//this.setMaximumSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,10000));
			//this.setMinimumSize(new Dimension(0, ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMaximumSize(new Dimension(MyConstants.CHOICEPANE_PART_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
		else if(type == ViewConstants.DISPALY_CHOICE_PANE_WHOLE){
			
//			tmp1.setLayout(new GridLayout(0,2));
			for(int i=0;i<choiceSize;i++){
				this.add(choicelist[i]);
//				this.add(new Label(""));
			}
			this.setLayout(new GridLayout(4,3));
			//this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_WHOLE_WIDTH,height));
			this.setPreferredSize(new Dimension(ViewConstants.CHOICEPANE_WHOLE_WIDTH,ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMaximumSize(new Dimension(ViewConstants.CHOICEPANE_WHOLE_WIDTH,10000));
			//this.setMinimumSize(new Dimension(0, ViewConstants.CHOICEPANE_HEIGHT));
			//this.setMinimumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
			//this.setMaximumSize(new Dimension(MyConstants.CHOICEPANE_WHOLE_WIDTH , MyConstants.CHOICEPANE_HEIGHT));
		}
	}
	
	public void init(int questionIndex,int splitIndex,int partIndex){
		System.out.println("DEBUG INFO: choicepane --" + questionIndex);
		this.removeAll();
		this.setBackground(Color.WHITE);
		curIndex = questionIndex;
		for(int i=0;i<choiceSize;i++){
			choicelist[i] = new SingleChoicePane();
		}
		if(partIndex == ModelConstants.MATH){
			for(int i=0;i<choiceSize;i++){
				choicelist[i].init(mathBrain.getChoice(MainController.questionIndexinSplit(questionIndex)).getOptions().get(i),i);
			}
		}
		else{
			for(int i=0;i<choiceSize-1;i++){
				choicelist[i].init(readingBrain.getChoice(MainController.questionIndexinSplit(questionIndex)).getOptions().get(i),i);
			}
			choicelist[choiceSize-1].init("", choiceSize-1);;
			choicelist[choiceSize-1].setVisible(false);
		}
		if (partIndex != ModelConstants.MATH){
			for(int i=0;i<choiceSize;i++){
				choicelist[i].setChoicePane(this);
				this.add(choicelist[i]);
			}
		}
		if (partIndex == ModelConstants.MATH){
			for(int i=0;i<3;i++){
				choicelist[i].setChoicePane(this);
				this.add(choicelist[i]);
			}
//			this.add(new JLabel(""));
			for(int i=3;i<5;i++){
				choicelist[i].setChoicePane(this);
				this.add(choicelist[i]);
			}
//			this.add(new JLabel(""));
//			for(int i=4;i<5;i++){
//				choicelist[i].setChoicePane(this);
//				this.add(choicelist[i]);
//			}
			for(int i=0;i<7;i++){
				this.add(new JLabel(""));
			}
		}
	}
	public int count(){
		return choiceSize;
	}
	public void setReadingBrain(reading r){
		readingBrain = r;
	}
	public void setMathBrain(math m){
		mathBrain = m;
	}
	public void checkOption(int index){
		MainController.setAnswer(curIndex,index);
		for(int i=0;i<choiceSize;i++){
			if(i==index){
				choicelist[i].setChecked(true);
			}
			else
			{
				choicelist[i].setChecked(false);
			}
		}
	}
	public void resetChecked(){
		checkOption(MainController.getAnswer(curIndex));
	}
	
}