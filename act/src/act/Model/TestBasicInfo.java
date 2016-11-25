package act.Model;

class Part{
	public int partIndex;
	public int splitNum;
	Part(int partindex,int splitnum){
		partIndex = partindex;
		splitNum = splitnum;		
	}
}
class Split{
	public int splitIndex;
	public int partIndex;
	public int questionNum;
	Split(int splitindex, int partindex, int questionnum){
		splitIndex = splitindex;
		partIndex = partindex;
		questionNum = questionnum;
	}
}
class Question{
	public int partIndex;
	public int splitIndex;
	public int questionIndex;
	Question(int partindex, int splitindex, int questionindex){
		partIndex = partindex;
		splitIndex = splitindex;
		questionIndex = questionindex;
	}
}
public class TestBasicInfo{
	Part[] splitPerPart = new Part[ModelConstants.PARTNUM_TOTAL];
	Split[] questionPerSplit = new Split[ModelConstants.SPLITNUM_TOTAL];
	Question[] questions = new Question[ModelConstants.QUESTIONNUM_TOTAL];
	int[] fakequestionnum= {14,60,2,2,2};	
	public TestBasicInfo(){
		
		int splitIndex = 0;
		int questionIndex = 0;
		for(int i = 0 ; i < ModelConstants.PARTNUM_TOTAL ; ++i){
			splitPerPart[i] = new Part(i,ModelConstants.SPLITNUM_PER_PART[i]);
			for(int j = 0 ; j < splitPerPart[i].splitNum ; j++){
				questionPerSplit[splitIndex] = 
						new Split(i,splitIndex,ModelConstants.QUESTIONNUM_PER_SPLIT[splitIndex]);
				for(int k = 0 ; k < questionPerSplit[splitIndex].questionNum ; k++){
					questions[questionIndex] = new Question(i,splitIndex,questionIndex);
					questionIndex++;
				}
				splitIndex++;
			}
		}
		
		/*for(int i=0; i<ModelConstants.QUESTIONNUM_TOTAL;i++){
			System.out.println(questions[i].partIndex+","+questions[i].splitIndex+","+questions[i].questionIndex);
		}*/
	}
	
	public boolean isLastInPart(int i){
		if(i >= questions.length-1) return true;
		return questions[i].partIndex != questions[i+1].partIndex ;
	}
	public boolean isLastInSplit(int i){
		if(i >= questions.length-1) return true;
		return questions[i].splitIndex != questions[i+1].splitIndex ;
	}
	public boolean isFirstInPart(int i){
		if(i == 0) return true;
		return questions[i].partIndex != questions[i-1].partIndex;
	}
	public boolean isFirstInSplit(int i){
		if(i == 0) return true;
		return questions[i].splitIndex != questions[i-1].splitIndex;
	}
	public int getTotalPartNum(){
		return ModelConstants.PARTNUM_TOTAL;
	}
	public int questionIndexinSplit(int questionIndexinTotal){
		int t = questionIndexinTotal;
		if(t==ModelConstants.QUESTIONNUM_TOTAL)return ModelConstants.QUESTIONNUM_PER_SPLIT[ModelConstants.SPLITNUM_PER_PART[ModelConstants.PARTNUM_TOTAL-1]-1]-1;
		int res = t;
		int i = 0;
		while(t>=0){
			res = t;
			t-=ModelConstants.QUESTIONNUM_PER_SPLIT[i++];
		}
		return res;
	}
	public int questionIndexinPart(int questionIndexinTotal){
		int t = questionIndexinTotal;
		if(t==ModelConstants.QUESTIONNUM_TOTAL)return ModelConstants.QUESTIONNUM_PER_PART[ModelConstants.PARTNUM_TOTAL-1]-1;
		int res = t;
		int i = 0;
		while(t>=0){
			res = t;
			t-=ModelConstants.QUESTIONNUM_PER_PART[i++];
		}
		return res;
	}
	public int splitIndexinPart(int splitIndexinTotal){
		int s = splitIndexinTotal;
		if(s==ModelConstants.SPLITNUM_TOTAL)return ModelConstants.SPLITNUM_PER_PART[ModelConstants.PARTNUM_TOTAL-1];
		int res = s;
		int i = 0;
		while(s>=0){
			res = s;
			s-=ModelConstants.SPLITNUM_PER_PART[i++];
		}
		return res;
	}
	public int firstQuestionIndexInSplit(int splitIndex){
		int res = 0;
		for(int i=0;i<splitIndex;i++){
			res+=ModelConstants.QUESTIONNUM_PER_SPLIT[i];
		}
		return res;
	}
	public int firstSplitInPart(int partIndex){
		int res = 0;
		for(int i=0;i<partIndex;i++){
			res+=ModelConstants.SPLITNUM_PER_PART[i];
		}
		return res;
		
	}
	
}