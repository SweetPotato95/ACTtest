package act.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import act.Model.choice;

public class readText {
	public static int[] readLens(String testname){
		int[] lens = new int[5+1+4+7];
		String name = "lens.txt";
		File file = new File(testname+name);
		BufferedReader reader = null;
		try{
			System.out.println("reading..."+testname+name);
			reader = new BufferedReader(new FileReader(file));
			String tmpstring = null;
			tmpstring = reader.readLine();
			String[] strs = tmpstring.split(" ");
			for (int i = 0; i < lens.length; i++){
				lens[i] = Integer.valueOf(strs[i]);
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
		
		return lens;
	}
	public static int[] readAnswer(String testname){
		int[]ans = new int[75+60+40+40];
		int[] lens = {0,75,60,40,40};
		int[] adds = {0,75,135,175,215};
		String[] names = {"","english.txt","math.txt","reading.txt","science.txt"};
		for(int i = 1; i <= 4; i++){
			File file = new File(testname+names[i]);
			BufferedReader reader = null;
			try{
				System.out.println("reading..."+testname+names[i]);
				reader = new BufferedReader(new FileReader(file));
				String tmpstring = null;
//				int line = 1;
				for(int j = 0; j < lens[i]; j++)
				{
					tmpstring = reader.readLine();
					tmpstring = tmpstring.split(" ")[1];
//					System.out.println( tmpstring.substring(0,tmpstring.length()-1));
					if(tmpstring.equals("A") || tmpstring.equals("F"))
						ans[adds[i-1] + j] = 0;
					if(tmpstring.equals("B") || tmpstring.equals("G"))
						ans[adds[i-1] + j] = 1;
					if(tmpstring.equals("C") || tmpstring.equals("H"))
						ans[adds[i-1] + j] = 2;
					if(tmpstring.equals("D") || tmpstring.equals("J"))
						ans[adds[i-1] + j] = 3;
					if(tmpstring.equals("E") || tmpstring.equals("K"))
						ans[adds[i-1] + j] = 4;
				}
				reader.close();
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if (reader != null){
					try{
						reader.close();
					}catch(IOException e1){
						e1.printStackTrace();
					}
				}
			}
		}
		return ans;
	}
	public static String readPassage(String filename){
		String passage = "";
		File file = new File(filename);
		BufferedReader reader = null;
		try{
			System.out.println("reading..."+filename);
			reader = new BufferedReader(new FileReader(file));
			String tmpstring = null;
			int line = 1;
			while((tmpstring = reader.readLine()) != null){
				System.out.println("reading...line "+line);
				line ++;
				if (tmpstring.startsWith("<h1>")){
					passage += tmpstring;
				}
				if (tmpstring.startsWith("<div class = \"para\">")){
					passage += tmpstring;
				}
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
		return passage;
	}
	public static String readDirection(String filename){
		String passage = "";
		File file = new File(filename);
		BufferedReader reader = null;
		try{
			System.out.println("reading..."+filename);
			reader = new BufferedReader(new FileReader(file));
			String tmpstring = null;
			int line = 1;
			while((tmpstring = reader.readLine()) != null){
				System.out.println("reading...line "+line);
				line ++;
				passage += tmpstring;
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
		return passage;
	}
	public static ArrayList<choice> readChoice(int type, String filename){
		ArrayList<choice> result = new ArrayList<choice>();
		choice tmpresult = new choice(type);
		File file = new File(filename);
		BufferedReader reader = null;
		int sum = 0;
		ArrayList<String> tmpoptions;
		try{
			System.out.println("reading..."+filename);
			reader = new BufferedReader(new FileReader(file));
			String tmpstring = null;
			int line = 1;
			int quizNum;
			int mark = 1;
			String ques = "";
			tmpoptions = new ArrayList<String>();
			while((tmpstring = reader.readLine()) != null){
				System.out.println("reading...line "+line);
				line ++;
				if (tmpstring.startsWith("####")){
					mark = 1;
					ques = "";
					sum++;
					if (sum > 1){
						tmpresult.setOptions(tmpoptions);
						result.add(tmpresult);
						tmpresult = new choice(type);
						tmpoptions = new ArrayList<String>();
					}
				}
				
				if((mark == 2&&!tmpstring.startsWith("<div class = \"choice\">"))||tmpstring.startsWith("<div class = \"quiz\">")){
					mark = 2;
					ques += tmpstring.replace("<div class = \"quiz\">", "").replace("</div>", "");
					ques += "\n";
				}
				if(tmpstring.startsWith("<div class = \"choice\">")){					
					if (mark == 2){
//						System.out.println(ques);
						int tmpl = ques.length();
						tmpresult.setQuestion(ques.substring(0, tmpl-1));
						int pos = ques.indexOf(".");
						quizNum = Integer.parseInt(ques.substring(0, pos));
//						System.out.println(quizNum);
						tmpresult.setQuizNum(quizNum);
					}
					mark = 3;
					tmpstring = tmpstring.replace("<div class = \"choice\">", "");
					tmpstring = tmpstring.replace("</div>", "");
					tmpoptions.add(tmpstring);
				}
			}
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if (reader != null){
				try{
					reader.close();
				}catch(IOException e1){
					e1.printStackTrace();
				}
			}
		}
		return result;
	}
}