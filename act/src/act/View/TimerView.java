package act.View;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.FileInputStream;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import act.Controller.MainController;

/**
 * 倒计时
 * 
 */
public class TimerView extends JPanel{

	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	private JTextPane jtp;
	private boolean inCountingMode = true;

	/* 倒计时的主要代码块 */
	public void startCount(int hour,int minute,int seconds) {
		int totalTime = hour*3600 + minute*60 + seconds;
		startCount(totalTime);
	}
	public void setCountingStatus(boolean c){
		inCountingMode = c;
	}
	public void startCount(int totalTime) {

		new Thread(){
			public void run(){
				long time = totalTime; // 自定义倒计时时间
				long hour = 0;
				long minute = 0;
				long seconds = 0;

				while (time >= 0 && inCountingMode) {
					hour = time / 3600;
					minute = (time - hour * 3600) / 60;
					seconds = time - hour * 3600 - minute * 60;
					
					//System.out.println(hour+","+minute+","+seconds);
					jl1.setText(hour + "时");
					jl2.setText(minute + "分");
					jl3.setText(seconds + "秒");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					time--;
				}
				if(inCountingMode){
					//System.out.println("In");
					MainController.timeIsUp();
				}
				else{
					//System.out.println("Exit");
				}
				
				
			}
		}.start();
		

	}

	/* 构造 实现界面的开发 GUI */
	public TimerView() {
		jl1 = new JLabel();
		jl2 = new JLabel();
		jl3 = new JLabel();
		jtp = new JTextPane();
		init();

	}

	/* 组件的装配 */
	private void init() {
		JPanel jp = new JPanel();
		jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
		this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		jp.add(jl1);
		jp.add(jl2);
		jp.add(jl3);

		jp.setVisible(true);
		//this.setLayout(new GridLayout(3,1));
		this.add(Box.createVerticalGlue());
		this.add(jp);
		this.add(Box.createVerticalGlue());
		this.setVisible(true);
		this.setSize(300, 100);
		this.setPreferredSize(new Dimension(300,ViewConstants.NAV_HEIGHT));
		
	}
	

}
