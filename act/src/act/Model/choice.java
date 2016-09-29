package act.Model;
import java.util.ArrayList;

public class choice {
	private int type;
	private int quizNum;
	private String question;
	private ArrayList<String> options;
	public choice(int t){
		this.type = t;
	}
	public int getType(){
		return type;
	}
	public void setQuestion(String q){
		this.question = q;
	}
	public String getQuestion(){
		return question;
	}
	public void setQuizNum(int quizNum){
		this.quizNum = quizNum;
	}
	public ArrayList<String> getOptions() {
		return options;
	}
	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}
}
