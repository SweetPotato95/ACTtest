package act.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import act.Model.AnswerModel;

import javax.swing.table.*;

public class ScoreListView extends JPanel{
	private boolean DEBUG = true;
	private JTable table;
	private String[] columnNames = {"Passage(Part)Name","Score(Details Touch the ... Button)"};
	private Object[][] values;
	DefaultTableModel model;
	public ScoreListView(){
	 super(new BorderLayout());
	          //创建表头
	 	 }

	
	public void init(){
		values = AnswerModel.getTotalScore();
		 model = new DefaultTableModel(values,columnNames);
		 
		 table = new JTable(model);
		 table.setBackground(Color.YELLOW);
		 //table.setPreferredScrollableViewportSize(new Dimension(500, 0));
		 JScrollPane scrollPane = new JScrollPane(table);
		 add(scrollPane);
		 TableColumnModel tcm= table.getColumnModel();  
		 TableColumn tc = tcm.getColumn(1); 
		  tc.setCellRenderer(new ScoreListViewCellRenderer());
		  tc.setCellEditor(new ScoreListViewCellEditor());
		 this.setSize(new Dimension(ViewConstants.MAINCONTENT_WIDTH,ViewConstants.MAINCONTENT_HEIGHT));
		 //this.setPreferredSize(preferredSize);
		
	}
}