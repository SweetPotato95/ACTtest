package act.Controller;

import java.util.ArrayList;

import act.Model.ModelConstants;
import act.Model.choice;

public class reading {
	private int testIndex;
	private int partIndex;
	private int splitIndexinPart;
	private String path = null;
	private static String passage;
	private ArrayList<choice> quizs;
	private String[] paths = {"resources\\2005 April 60E\\",""};
	private String[] tests = {"English\\","Math\\","Reading\\","Science\\","Writing\\"};
	public reading(int t, int tn, int index){
		this.testIndex = t;
		this.partIndex = tn;
		this.splitIndexinPart = index;
		this.path = paths[t]+tests[tn]+(splitIndexinPart+1)+".txt";
		this.passage = readText.readPassage(path);
		this.quizs = readText.readChoice(t, path);
	}
	public static String getPassage(){
		return passage;
	}
	public int getQuizLength(){
		return quizs.size();
	}
	public choice getChoice(int index){
		return quizs.get(index);
	}
	public String readDirection(int partIndex){
		String res = readText.readDirection("resources\\2005 April 60E\\"+ModelConstants.PARTNAME[partIndex]+"\\Direction.txt");
		System.out.println(res);
		return res;
	}
	public void updateReading(int testIndex,int splitIndex,int partIndex){
		if(partIndex == ModelConstants.MATH){
			System.out.println("Miss request from Math to Reading");
			return;
		}
		if(testIndex == this.testIndex && splitIndex == this.splitIndexinPart && partIndex == this.partIndex){
			System.out.println("Already newest");
			return;
		}
		System.out.println("Update in");
		this.testIndex = testIndex;
		this.partIndex = partIndex;
		this.splitIndexinPart = splitIndex;
		this.path = paths[this.testIndex]+tests[partIndex]+(splitIndexinPart+1)+".txt";
		this.passage = readText.readPassage(path);
		this.quizs = readText.readChoice(this.testIndex, path);
	}
}
