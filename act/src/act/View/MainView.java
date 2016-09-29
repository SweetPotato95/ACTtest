package act.View;

import javax.swing.*;
import act.Controller.MainController;
import act.Controller.math;
import act.Controller.reading;
import act.Model.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class MainView extends JPanel{
	/**
	 This is the main view.
	 */
	private static final long serialVersionUID = 7L;
	private ControlPane navBar = new ControlPane();	//navigation bar on top
	private JLayeredPane mainContent = new JLayeredPane();
	private PassageView passageView = new PassageView();	//content panel,insist of passage and choice
	private ChoiceView choiceView = new ChoiceView();
	private WritingView writingView = new WritingView();
	private InstructionView instructionView = new InstructionView();
	private ScoreListView scoreListView = new ScoreListView();
	private reading readingBrain ;
	private math mathBrain ;
	public MainView()
	{
		
	}
	
	public void init(){
		this.removeAll();
		this.revalidate();
		passageView.init();
	    choiceView.init();
	    writingView.init();
	    instructionView.init();
	    navBar.init();
		mainContentInit();
		MainController.setMainContent(this);
		this.setSize(ViewConstants.MAINPANEL_WIDTH, ViewConstants.MAINPANEL_HEIGHT);
		this.setPreferredSize(new Dimension(ViewConstants.MAINPANEL_WIDTH, ViewConstants.MAINPANEL_HEIGHT));
		this.setVisible(true);
		this.setLayout(new BorderLayout());
		this.add(navBar,BorderLayout.PAGE_START);
        this.add(mainContent,BorderLayout.CENTER);
        this.setVisible(true);
        this.revalidate();
        Toolkit.getDefaultToolkit().addAWTEventListener(new AWTEventListener() {  
            
          @Override  
          public void eventDispatched(AWTEvent event) {  
              // TODO Auto-generated method stub  
              if(((KeyEvent)event).getID()==KeyEvent.KEY_PRESSED){  
                  switch (((KeyEvent)event).getKeyCode()) {  
                  case KeyEvent.VK_ENTER:  
                       System.out.println("fuckyou");
                      break;  
                  case KeyEvent.VK_RIGHT:
                	  MainController.handleNext();
                	  break;
                  case KeyEvent.VK_LEFT:
                	  MainController.handleBef();
                	  break;
                  }  
              }  
          }  
      }, AWTEvent.KEY_EVENT_MASK);
        showInstructionView(ModelConstants.ENGLISH);
        //showPassageView();
        
	}
	
	public void mainContentInit(){
		mainContent.removeAll();
		mainContent.setSize(ViewConstants.MAINCONTENT_WIDTH, ViewConstants.MAINCONTENT_HEIGHT);
		mainContent.setPreferredSize(new Dimension(ViewConstants.MAINCONTENT_WIDTH, ViewConstants.MAINCONTENT_HEIGHT));
		mainContent.add(passageView, ViewConstants.PASSAGEVIEW_LAYER);
		mainContent.add(choiceView, ViewConstants.CHOICEVIEW_LAYER);
		mainContent.add(writingView, ViewConstants.WRITINGVIEW_LAYER);
		mainContent.add(instructionView, ViewConstants.INSTRUCTIONVIEW_LAYER);
		
	}
	
	public void showChoiceView(){
		choiceView.setVisible(true);
		passageView.setVisible(false);
		writingView.setVisible(false);
		instructionView.setVisible(false);
		mainContent.moveToFront(choiceView);
		
	}
	public void showPassageView(){
		choiceView.setVisible(false);
		passageView.setVisible(true);
		writingView.setVisible(false);
		instructionView.setVisible(false);
		mainContent.moveToFront(passageView);
	}
	public void showWritingView(){
		choiceView.setVisible(false);
		passageView.setVisible(false);
		writingView.setVisible(true);
		instructionView.setVisible(false);
		mainContent.moveToFront(writingView);
	}
	public void showInstructionView(int partIndexToShow){
		System.out.println("IN");
		choiceView.setVisible(false);
		passageView.setVisible(false);
		writingView.setVisible(false);
		instructionView.requestUpdate(partIndexToShow);
		mainContent.remove(instructionView);
		mainContent.add(instructionView, ViewConstants.INSTRUCTIONVIEW_LAYER);
		instructionView.setVisible(true);
		
		mainContent.moveToFront(instructionView);
	}
	public void showScoreView(){
		mainContent.removeAll();
		mainContent.add(scoreListView,0);
		navBar.scoreMode();
		scoreListView.setVisible(true);
	}
	public void requestUpdate(){
		requestUpdate(MainController.getQuestionIndex(),MainController.getSplitIndex(),MainController.getPartIndex());
	}
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
		navBar.requestUpdate(questionIndex, splitIndex, partIndex);
		if(partIndex == ModelConstants.MATH){
			choiceView.requestUpdate(questionIndex, splitIndex, partIndex);
			mainContent.remove(choiceView);
			mainContent.add(choiceView, ViewConstants.CHOICEVIEW_LAYER);
			showChoiceView();
		}
		else if (partIndex != ModelConstants.WRITING){
			passageView.requestUpdate(questionIndex, splitIndex, partIndex);
			mainContent.remove(passageView);
			mainContent.add(passageView, ViewConstants.PASSAGEVIEW_LAYER);
			showPassageView();
		}
		else{
			showWritingView();
		}
	}
	public void setReadingBrain(reading r){
		readingBrain = r;
		choiceView.setReadingBrain(r);
		passageView.setReadingBrain(r);
		instructionView.setReadingBrain(r);
	}
	public void setMathBrain(math m){
		mathBrain = m;
		choiceView.setMathBrain(m);
		instructionView.setMathBrain(m);
	}
	public void startTimer(int partIndex){
		navBar.startTimer(ViewConstants.TIME_LIMIT_PER_PART[partIndex]);
	}
};