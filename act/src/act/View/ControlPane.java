package act.View;

import javax.swing.*;

import act.Controller.MainController;
import act.Model.ModelConstants;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;


public class ControlPane extends JPanel{
	/**
	 This is the controlPane, it's the navBar on top of mainView. 
	 */
	private static final long serialVersionUID = 4L;
	private JLabel title = new JLabel();
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
		title.setFont(ViewConstants.controlPane_labelFont);
//		title.setEditable(false);
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
				JFrame tmp = new JFrame();
				tmp.setAlwaysOnTop(true);
				int option = JOptionPane.showConfirmDialog(tmp,
					       "确定提交至成绩单界面？", "Submit", JOptionPane.YES_NO_OPTION);
					     switch (option) {
					     	case JOptionPane.YES_NO_OPTION: {
					     		MainController.handleScore();
					      	break;
					     }
					     case JOptionPane.NO_OPTION:
					    	 return;

					     }
				
				
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
		title.setForeground(new Color(192,192,192));
		this.removeAll();
		this.revalidate();
		JLabel jl1 = new JLabel();
		
		ImageIcon image = new ImageIcon("resources"+File.separator+"lib"+File.separator+"wlogo.png");
		image.setImage(image.getImage().getScaledInstance((int) (ViewConstants.NAV_WIDTH*0.2),(int)(ViewConstants.NAV_HEIGHT*1.2),Image.SCALE_DEFAULT)); 
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
//		this.add(Box.createHorizontalGlue());
		jl1.setSize((int) (ViewConstants.NAV_WIDTH*0.2),(int)(ViewConstants.NAV_HEIGHT*1.2));
		jl1.setIcon(image);
		this.add(jl1);
		this.add(Box.createHorizontalGlue());
		this.add(title);
		this.add(Box.createHorizontalGlue());
		this.add(timerView);
//		this.add(Box.createHorizontalGlue());
		this.add(pauseButton);
		this.add(befButton);
		this.add(nextButton);
//		if (MainController.getPartMode())
			this.add(submitButton);
		this.setPreferredSize(new Dimension(ViewConstants.NAV_WIDTH,ViewConstants.NAV_HEIGHT));
		this.setBackground(new Color(0,74,128));
		this.revalidate();
		
	}
	public void requestUpdate(int splitIndex,int partIndex){
		splitIndex += 1;
		if (partIndex != ModelConstants.MATH && partIndex != ModelConstants.WRITING)
		title.setText("Section:  "+ ModelConstants.PARTNAME[partIndex]+"  Passage: "+splitIndex);
		else if (partIndex == ModelConstants.MATH ){
			title.setText("Section:  "+ModelConstants.PARTNAME[partIndex]);
		}
		else if (partIndex == ModelConstants.WRITING){
			title.setText("Section:  "+ModelConstants.PARTNAME[partIndex]);
		}
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
//		this.add(befButton);
//		this.add(nextButton);
//		if (MainController.getPartMode())
		this.add(submitButton);
		this.revalidate();
		this.repaint();
	}
	public void normalMode(){
		this.remove(resumeButton);
		this.add(pauseButton);
		this.add(befButton);
		this.add(nextButton);
//		if (MainController.getPartMode())
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
		title.setText("");
	}
	public void pauseTimer(){
		timerView.suspend();
	}
	public void resumeTimer(){
		timerView.resume();
	}
	public Boolean isTimeAlive(){
		return timerView.isAlive();
	}
};