package act.View;
import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreListViewCellDetail extends JDialog{
 /**
	 * 
	 */
	private static final long serialVersionUID = -6275845889135880243L;
String[] colunmNames = {"QuestionId","CorrectAnswer","YourAnswer","Score"};
 public Object[][] values = { 
            {"1","A","A","1"}, 
            {"2","B","B","1"}, 
            {"3","C","C","1"}, 
            {"4","B","A","1"}, 
            {"5","A","A","0"} 
 };
 DefaultTableModel model = new DefaultTableModel(values,colunmNames);
 JTable detailScoreTable;
 JScrollPane scrollPane;
 JPanel centerPanel = new JPanel();
 public ScoreListViewCellDetail(int x,int y){
  setBounds(x,y,ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH,ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT);
  detailScoreTable = new JTable(model);
  scrollPane = new JScrollPane(detailScoreTable);
  scrollPane.setSize((int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_WIDTH),(int)Math.floor(0.9*ViewConstants.SCORE_LIST_VIEW_DETAIL_HEIGHT));
  add(scrollPane,BorderLayout.CENTER);
  
 }
}
