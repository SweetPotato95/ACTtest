package act.Model;

import java.io.File;
import java.util.ArrayList;

public class math {
	private int testnum;
	private String path = null;
	private ArrayList<choice> quizs;
	public math(int tn){
		this.testnum = tn;
		this.path = ModelConstants.TESTPATH[tn]+File.separator + "Math" +File.separator + "math.txt";
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
	}
	public int getQuizLength(){
		return quizs.size();
	}
	public void updateMath(int testNum){
		System.out.println("Update test"+testnum);
		if(testNum == this.testnum){
			System.out.println("Already Newest");
			return;
		}
		this.testnum = testNum;
//		System.out.println("--------"+this.testnum);
		this.path = ModelConstants.TESTPATH[testNum] +File.separator + "Math" +File.separator + "math.txt";
		System.out.print(path);
		this.quizs = readText.readChoice(ModelConstants.MATH, path);
		
	}
	public choice getChoice(int index){
		return quizs.get(index);
	}
}
