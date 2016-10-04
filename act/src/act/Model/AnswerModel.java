package act.Model;

import act.Controller.readText;

public class AnswerModel{
	private static int[] ans = new int[ModelConstants.QUESTIONNUM_TOTAL+1];
	private static int[] correct_ans = new int[ModelConstants.QUESTIONNUM_TOTAL+1];
	private static TestBasicInfo testBasicInfo;
	private static Object[][][] ansForPart = new Object[5][][];
	private static Object[][] totalScore = new Object[5][2];
	public AnswerModel(){
		for(int i=0;i<ModelConstants.QUESTIONNUM_TOTAL;i++){
			ans[i] = -1;
		}
	}
	public void setBasicInfo(TestBasicInfo t){
		testBasicInfo = t;
	}
	public void setAns(int questionIndex,int answer){
		ans[questionIndex] = answer;
	}
	public int getAnswer(int questionIndex){
		return ans[questionIndex];
	}
	public void judgeScore(){
		correct_ans = readText.readAnswer(0);
		boolean start = true;
		
		int questionIndex = 0;
		for(int partIndex = 0;partIndex<ModelConstants.PARTNUM_TOTAL;partIndex++){
			start = true;
			ansForPart[partIndex] = new Object[ModelConstants.QUESTIONNUM_PER_PART[partIndex]][4];
			int scoreThisPart = 0;
			for(int questionIndexinPart = 0 ; questionIndexinPart<ModelConstants.QUESTIONNUM_PER_PART[partIndex];questionIndexinPart++){
				
				String ch_answer = "null";
				String c_answer = "null";
				if(start){
					switch(ans[questionIndex]){
					case 0:
						ch_answer = "A";
						break;
					case 1:
						ch_answer = "B";
						break;
					case 2:
						ch_answer = "C";
						break;
					case 3:
						ch_answer = "D";
						break;
					case 4:
						ch_answer = "E";
						break;
						
					}
					
					switch(correct_ans[questionIndex]){
					case 0:
						c_answer = "A";
						break;
					case 1:
						c_answer = "B";
						break;
					case 2:
						c_answer = "C";
						break;
					case 3:
						c_answer = "D";
						break;
					case 4:
						c_answer = "E";
						break;
					}
				}
				else{
					switch(ans[questionIndex]){
					case 0:
						ch_answer = "F";
						break;
					case 1:
						ch_answer = "G";
						break;
					case 2:
						ch_answer = "H";
						break;
					case 3:
						ch_answer = "J";
						break;
					case 4:
						ch_answer = "K";
						break;
					}
					switch(correct_ans[questionIndex]){
					case 0:
						c_answer = "F";
						break;
					case 1:
						c_answer = "G";
						break;
					case 2:
						c_answer = "H";
						break;
					case 3:
						c_answer = "J";
						break;
					case 4:
						c_answer = "K";
						break;
					}
				}
				start = !start;
				//System.out.println("NO."+i+" My answer is "+ch_answer+", and correct answer is "+c_answer);
				
				ansForPart[partIndex][questionIndexinPart][0] = questionIndex;
				ansForPart[partIndex][questionIndexinPart][1] = ch_answer;
				ansForPart[partIndex][questionIndexinPart][2] = c_answer;
				ansForPart[partIndex][questionIndexinPart][3] = ans[questionIndex] == correct_ans[questionIndex]?1:0;
				
				scoreThisPart+=(int)ansForPart[partIndex][questionIndexinPart][3];
				questionIndex++;
			}
			totalScore[partIndex][0] = ModelConstants.PARTNAME[partIndex];
			totalScore[partIndex][1] = scoreThisPart;
			
			
		}

	}
	public static Object[][] getAnsModel(int partIndex){
		return ansForPart[partIndex];
	}
	public static Object[][] getTotalScore(){
		return totalScore;
	}
	
	public void resetAll(){
		totalScore = new Object[5][2];
		ansForPart = new Object[5][][];
		testBasicInfo = null;
		ans = new int[ModelConstants.QUESTIONNUM_TOTAL+1];
		for(int i=0;i<ModelConstants.QUESTIONNUM_TOTAL;i++){
			ans[i] = -1;
		}
		correct_ans = new int[ModelConstants.QUESTIONNUM_TOTAL+1];
		for(int i=0;i<ModelConstants.QUESTIONNUM_TOTAL;i++){
			correct_ans[i] = -1;
		}
	}
}