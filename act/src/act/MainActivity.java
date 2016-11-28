package act;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
import act.Model.*;
import act.Controller.*;
import act.View.*;



public class MainActivity extends JFrame{
	/**
	 This is the main activity. where main method in. 
	 */
	

	private static final long serialVersionUID = 1L;
	//private MainController mainController = new MainController();
	private MainView mainView = new MainView();
	private TestBasicInfo basicInfo = new TestBasicInfo();
	private reading readingBrain = new reading(0,0,0);
	private math mathBrain = new math(0);
	private AnswerModel ans;
	private MenuView menuView = new MenuView();
	public MainActivity(){
		this.setTitle("ACT Practice");
		this.setSize(ViewConstants.MAINPANEL_WIDTH, ViewConstants.MAINPANEL_HEIGHT);
		menuView.setMainActivity(this);
		this.add(menuView);
        this.setUndecorated(false);  
        this.setResizable(false);
        this.getGraphicsConfiguration().getDevice()  
                .setFullScreenWindow(this); 
        this.setVisible(true);
        }

	public void initMainController(int testIndex){
		MainController.setMainActivity(this);
		MainController.setTestIndex(testIndex);
		ans = new AnswerModel(testIndex);
		MainController.setAnswerModel(ans);
		ModelConstants.QUESTIONNUM_PER_SPLIT = readText.readLens(ModelConstants.TESTPATH[testIndex]);
		basicInfo = new TestBasicInfo();
		MainController.setBasicInfo(basicInfo);
		
		MainController.setReading(readingBrain);
		MainController.setMath(mathBrain);
		MainController.init();
		}

	public void initMainView(int testIndex){
		System.out.println("DEBUG INFO MainActivity initMainView: "+testIndex);
		initMainController(testIndex);
		mainView = new MainView();
		
		mainView.setReadingBrain(readingBrain);
		readingBrain.updateReading(testIndex, 0, 0);
		
		mainView.setMathBrain(mathBrain);
		mathBrain.updateMath(testIndex);
		mainView.init();
		
		this.remove(menuView);
		this.revalidate();
		this.add(mainView);
		this.revalidate();
	}
	// 直接进入part
	public void initMainView(int testIndex, int partIndex){
		initMainController(testIndex);
		mainView = new MainView();
		
		mainView.setReadingBrain(readingBrain);
		readingBrain.updateReading(testIndex, 0, partIndex);
		
		mainView.setMathBrain(mathBrain);
		mathBrain.updateMath(testIndex);
		mainView.init(partIndex);
	
		this.remove(menuView);
		this.revalidate();
		this.add(mainView);
		this.revalidate();
	}
	
	public void showMenuView(){
		this.remove(mainView);
		this.revalidate();
		this.repaint();
		this.add(menuView);
		this.revalidate();
	}
//	public static void main(String[] args){
//	MainActivity mainActivity = new MainActivity();
//	mainActivity.setSize(Toolkit.getDefaultToolkit().getScreenSize());
//	mainActivity.addWindowListener(new WindowAdapter(){
//			@Override
//			public void windowClosing(WindowEvent we){
//				System.exit(0);
//			}
//		});
//	}

	
}
