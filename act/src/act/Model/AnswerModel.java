package act.Model;

public class AnswerModel{
	private static int[] ans = new int[ModelConstants.QUESTIONNUM_TOTAL];
	private static int[] correct_ans = new int[ModelConstants.QUESTIONNUM_TOTAL];
	public AnswerModel(){
		for(int i=0;i<ModelConstants.QUESTIONNUM_TOTAL;i++){
			ans[i] = -1;
		}
	}
	
	public void setAns(int questionIndex,int answer){
		ans[questionIndex] = answer;
	}
	public int getAnswer(int questionIndex){
		return ans[questionIndex];
	}
}