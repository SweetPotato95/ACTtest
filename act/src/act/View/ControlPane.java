package act.View;

import javax.swing.*;

import act.Controller.MainController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ControlPane extends JPanel{
	/**
	 This is the controlPane, it's the navBar on top of mainView. 
	 */
	private static final long serialVersionUID = 4L;
	private JTextPane title = new JTextPane();
	private JPanel buttons = new JPanel();
	private JButton pauseButton = new JButton("Pause");
	private JButton resumeButton = new JButton("Resume");
	private JButton nextButton = new JButton("Next");	//test button
	private JButton befButton = new JButton("Back");
	private JButton submitButton = new JButton("Submit");
	private JButton finishButton = new JButton("Return");
	private JButton saveButton = new JButton("Save as PDF");
	private TimerView timerView = new TimerView();
	public ControlPane()
	{
		
	}
	
	public void init(){
		title.setOpaque(false);
		title.setEditable(false);
		nextButton.addActionListener(new ActionListener()
        {
        	@Override
        	  public void actionPerformed(ActionEvent e)
        	  {

        		MainController.handleNext();
        	  }
        	  });
		
		befButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handleBef();
			}
		});
		
		submitButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handleScore();
			}
		});
		
		finishButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handleReturn();
			}
		});
		
		pauseButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handlePause();
			}
		});
		resumeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handleResume();
			}
		});
		saveButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				MainController.handleSave();
			}
		});
		
		this.removeAll();
		this.revalidate();
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(Box.createHorizontalGlue());
		this.add(title);
		this.add(Box.createHorizontalGlue());
		this.add(timerView);
//		this.add(Box.createHorizontalGlue());
		this.add(pauseButton);
		this.add(befButton);
		this.add(nextButton);
		this.add(submitButton);
		this.setPreferredSize(new Dimension(ViewConstants.NAV_WIDTH,ViewConstants.NAV_HEIGHT));
		this.setBackground(Color.gray);
		this.revalidate();
		
	}
	public void requestUpdate(int questionIndex,int splitIndex,int partIndex){
		title.setText("Test1:PART "+partIndex+" SPLIT "+splitIndex+" QUESTION "+questionIndex);
	}
	
	public void scoreMode(){
		this.removeAll();
		this.add(Box.createHorizontalGlue());
		this.add(saveButton);
		this.add(finishButton);
		this.revalidate();
		this.repaint();
	}
	public void pauseMode(){
		this.remove(pauseButton);
		this.remove(nextButton);
		this.remove(befButton);
		this.remove(submitButton);
		this.add(resumeButton);
		this.add(befButton);
		this.add(nextButton);
		this.add(submitButton);
		this.revalidate();
		this.repaint();
	}
	public void normalMode(){
		this.remove(resumeButton);
		this.add(pauseButton);
		this.add(befButton);
		this.add(nextButton);
		this.add(submitButton);
		this.revalidate();
		this.repaint();
	}
	public void startTimer(int totalTime){
		timerView.startCount(totalTime);
	}
	public void startTimer(int hour,int minute,int seconds){
		int totalTime = hour*3600 + minute*60 + seconds;
		startTimer(totalTime);
	}
	public void setCountingStatus(boolean c){
		timerView.setCountingStatus(c);
	}
	public void initTimer(int totalTime){
		timerView.initTimer(totalTime);
	}
	public void pauseTimer(){
		timerView.suspend();
	}
	public void resumeTimer(){
		timerView.resume();
	}
};