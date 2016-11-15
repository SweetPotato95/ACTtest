package act.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SingleChoicePane extends JPanel{
	/**
	 This is one option.
	 */
	private static final long serialVersionUID = 10L;
	private ChoicePane choicePane;
	private JRadioButton button; 
	private int index;

	public void singlechoice(){

	}
	
	public void init(String givenText,int i){
		button = new JRadioButton("<html><body>"+givenText+"</body></html>");
		button.setFont(ViewConstants.choiceFont);
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				choicePane.checkOption(index);
			}
		});
		
		//this.setMaximumSize(new Dimension(ViewConstants.CHOICEPANE_PART_WIDTH,1000));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.add(button);
		index = i;
		
		//this.setBorder(BorderFactory.createLineBorder(Color.black));
		//this.add(panel);
	}
	public void requestUpdate(String giveText){

		button.setText(giveText);
		//System.out.println(button.getText());
		button.repaint();
		this.repaint();
		super.repaint();
		
	}
	public void setChoicePane(ChoicePane c){
		choicePane = c;
	}
	public void setChecked(boolean v){
		button.setSelected(v);
	}
}