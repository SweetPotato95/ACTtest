package act.View;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import act.Model.AnswerModel;
import act.Model.ModelConstants;

public class ScoreListViewCellDetail extends JDialog{
 /**
	 * 
	 */
	private static final long serialVersionUID = -6275845889135880243L;
String[] colunmNames = {"Question","Your Answer","Correct Answer"};
 public Object[][] values = { 
            {"1","A","A"}, 
            {"2","B","B"}, 
            {"3","C","C"}, 
            {"4","B","A"}, 
            {"5","A","A"} 
 };
 DefaultTableModel model = new DefaultTableModel(values,colunmNames);
 JTable detailScoreTable;
 JScrollPane scrollPane;
 JPanel centerPanel = new JPanel();
 JLabel jl = new JLabel();
 public ScoreListViewCellDetail(int x,int y){
  setBounds(x,y,ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH,ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT);
  this.setVisible(true);
 }
 
 public void init(){
	 model = new DefaultTableModel(values,colunmNames);
	 detailScoreTable = new JTable(model);
//	 detailScoreTable.setRowHeight(30);
	 scrollPane = new JScrollPane(detailScoreTable);
	 scrollPane.setSize((int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH),(int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT));
	 //this.remove(scrollPane);
	 scrollPane.setVisible(true);
	 jl.setFont(new java.awt.Font("Microsoft Yahei",0,16));
	 add(jl,BorderLayout.BEFORE_FIRST_LINE);
	 add(scrollPane,BorderLayout.CENTER); 
 }
 
 public void setModel(int i){
	 values = AnswerModel.getAnsModel(i);
 }
 public void setScore(int i){
	 jl.setText("   Your Score: "+ AnswerModel.getScore(i)[1].toString());
 }
 
}


