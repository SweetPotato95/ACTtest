package act.Controller;

import java.util.ArrayList;

import act.Model.ModelConstants;
import act.Model.choice;

public class math {
	private int testnum;
	private String path = null;
	private ArrayList<choice> quizs;
	public math(int tn){
		this.testnum = tn;
		this.path = ModelConstants.TESTPATH[tn]+"\\Math\\math.txt";
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
	}
	public int getQuizLength(){
		return quizs.size();
	}
	public void updateMath(int testNum){
		
		if(testNum == this.testnum){
			//System.out.println("Already Newest");
			return;
		}
		this.testnum = testNum;
		this.path = ModelConstants.TESTPATH[testNum] +"\\Math\\math.txt";
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
	}
	public choice getChoice(int index){
		return quizs.get(index);
	}
}
