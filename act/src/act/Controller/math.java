package act.Controller;

import java.util.ArrayList;

import act.Model.ModelConstants;
import act.Model.choice;

public class math {
	private int testnum;
	private String path = null;
	private ArrayList<choice> quizs;
	private String[] tests = {"resources\\2005 April 60E\\Math\\",""};
	public math(int tn){
		this.testnum = tn;
		this.path = tests[tn]+"math.txt";
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
	}
	public int getQuizLength(){
		return quizs.size();
	}
	public void updateMath(int testNum){
		
		if(testNum == this.testnum){
			System.out.println("Already Newest");
			return;
		}
		this.testnum = testNum;
		this.path = tests[testNum] +"math.txt";
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
	}
	public choice getChoice(int index){
		return quizs.get(index);
	}
}
