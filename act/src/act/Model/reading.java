package act.Model;

import java.io.File;
import java.util.ArrayList;

public class reading {
	private int testIndex;
	private int partIndex;
	private int splitIndexinPart;
	private String path = null;
	private static String passage;
	private ArrayList<choice> quizs;
	private String[] tests = {"English\\","Math\\","Reading\\","Science\\","Writing\\"};
	public reading(int t, int tn, int index){
		this.testIndex = t;
		this.partIndex = tn;
		this.splitIndexinPart = index;
		this.path = ModelConstants.TESTPATH[t]+tests[tn]+(splitIndexinPart+1)+".txt";
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
		//System.out.println("-----------"+index);
		return quizs.get(index);
	}
	public String readDirection(int testIndex, int partIndex){
		if(partIndex >= ModelConstants.WRITING){
			return "";//This is temporary because there weren't any writing info now/
		}
		String res = readText.readDirection( ModelConstants.TESTPATH[testIndex]
				+ModelConstants.PARTNAME[partIndex]+"\\Direction.txt");
		return res;
	}
	public void updateReading(int testIndex,int splitIndex,int partIndex){
		if(partIndex == ModelConstants.MATH ){
			//System.out.println("Miss request from Math to Reading");
			return;
		}
		if(partIndex >= ModelConstants.WRITING){
			this.partIndex = partIndex;
			this.splitIndexinPart = 0;
			String pox = new File(".").getAbsolutePath();
			int num = (int)(1+Math.random()*(10-1+1));
			System.out.println(num);
			this.path = pox.substring(0,pox.length()-1)+"resources\\lib\\writing_test\\"+num+".txt";
			System.out.println(path);
			this.passage = readText.readPassage(path);
			this.quizs = null;
			return;
		}
		if(testIndex == this.testIndex && splitIndex == this.splitIndexinPart && partIndex == this.partIndex){
			//System.out.println("Already newest");
			return;
		}
		System.out.println("DEBUG INFO: reading -- " +testIndex+","+splitIndex);
		
		this.testIndex = testIndex;
		this.partIndex = partIndex;
		this.splitIndexinPart = splitIndex;
		this.path = ModelConstants.TESTPATH[this.testIndex]
				+tests[partIndex]+(splitIndexinPart+1)+".txt";
		this.passage = readText.readPassage(path);
		this.quizs = readText.readChoice(this.testIndex, path);
	}
}
