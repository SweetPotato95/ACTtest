package act.View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.table.*;

import act.MainActivity;
import act.Model.ModelConstants;

public class MenuView extends JPanel{
	private JTable table = null;
	private MyTableModel model = null;
	private MainActivity mainActivity ;
	private JPanel Title = null;
	private JLabel content = null;
	private JScrollPane scrollPane;
	public MenuView(){
		init();
	}
	
	private void init(){
		this.setSize(ViewConstants.MAINPANEL_WIDTH,ViewConstants.MAINPANEL_HEIGHT);
		this.setPreferredSize(new Dimension(ViewConstants.MAINPANEL_WIDTH,ViewConstants.MAINPANEL_HEIGHT));
		this.setLayout(new GridBagLayout());
		
		content = new JLabel("ACT TEST");
		content.setFont(new Font("΢���ź�",Font.PLAIN,25));
		
		content.setSize(100,100);
		Title = new JPanel();
		Title.add(content);
		
//		this.add(content);
		this.add(Title, new GBC(0,0,1,1).  
                setFill(GBC.BOTH).setIpad(ViewConstants.MAINPANEL_WIDTH, 10).setWeight(100, 0));
		
		model = new MyTableModel();
		table = new JTable(model);
		ButtonColumn buttonColumn1 = new ButtonColumn(table,1);
		ButtonColumn buttonColumn2 = new ButtonColumn(table,2);
		ButtonColumn buttonColumn3 = new ButtonColumn(table,3);
		ButtonColumn buttonColumn4 = new ButtonColumn(table,4);
		ButtonColumn buttonColumn5 = new ButtonColumn(table,5);
		ButtonColumn buttonColumn6 = new ButtonColumn(table,6);
		ButtonColumn buttonColumn7 = new ButtonColumn(table,7);
		
		buttonColumn1.setMenuView(this);
		buttonColumn2.setMenuView(this);
//		table.setEnabled(false);
		table.setShowVerticalLines(false);
		table.getColumn("Test").setPreferredWidth(300);
		table.getColumn("Complete").setPreferredWidth(180);
		table.getColumn("Report").setPreferredWidth(180);
		//table.setRowHeight(28);
		table.setFont(new Font("΢���ź�",Font.PLAIN,14));
		// �־���
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(table.getColumnClass(0), render);
	    table.setRowHeight(30);
	    // ��ͷ����
	    JTableHeader header = table.getTableHeader();
	    header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
	    header.setPreferredSize(new Dimension(header.getWidth(), 32));
	    
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(BorderFactory.createEtchedBorder());
//		this.add(scroll);
		this.add(scroll, new GBC(0,1,10,1).  
                setFill(GBC.BOTH).setIpad(ViewConstants.MAINPANEL_WIDTH, ViewConstants.MAINPANEL_HEIGHT-content.getHeight()));//.setWeight(100, 0));
		this.setVisible(true);
	}
	
	public void setMainActivity(MainActivity m){
		mainActivity = m;
	}
	public void enterTest(int i){
		System.out.println(i);
		mainActivity.initMainView(i);
	}
	public void enterSplit(int i, int j){
		System.out.println(i+ "," +j);
	}
}

class MyTableModel extends AbstractTableModel{
	//��Ԫ��Ԫ������
	private Class[] cellType={String.class,JButton.class,JButton.class,JButton.class,JButton.class,JButton.class,JButton.class,JButton.class};
	//��ͷ
	
	private String[] colNames = {"Test","Complete","English","Math","Reading","Science","Writing","Report"};
	//ģ������
	private Object[][] obj = null;
	public MyTableModel(){
		obj = new Object[9][8];
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 8; j++){
				switch(j){
				case 0:
					obj[i][j] = ModelConstants.TESTNAME[i];
					break;
				default:
					obj[i][j] = new JButton();
					break;
				}
			}
		}
	}
	@Override
	public Class<?> getColumnClass(int arg0) {
		// TODO Auto-generated method stub
		return cellType[arg0];
	}
	@Override
	public String getColumnName(int arg0) {
		// TODO Auto-generated method stub
		return colNames[arg0];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return colNames.length;
	}
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return obj.length;
	}
	@Override
	public Object getValueAt(int r, int c) {
		// TODO Auto-generated method stub
		return obj[r][c];
	}
	  //��дisCellEditable����
    public boolean isCellEditable(int r,int c)
    {
    	if (c != 0)
        return true;
    	return false;
    }
    //��дsetValueAt����
    public void setValueAt(Object value,int r,int c)
    {
        obj[r][c]=value;
        this.fireTableCellUpdated(r,c);
    }
}

class ButtonColumn extends AbstractCellEditor implements TableCellEditor, TableCellRenderer ,ActionListener{
	private JButton rb,eb;
    private int row, column; 
    private JTable table;
    private String text1="Start";
    private String text2="Report";
    private ImageIcon icon = new ImageIcon("resources\\lib\\button.gif");
    private MenuView menuView;
    
    public ButtonColumn(){}
    public ButtonColumn(JTable table, int column)
    {
        super();
        this.table = table;
        this.column = column;
        if (column != 7){
        	rb = new JButton(text1,icon);
        	eb = new JButton(text1,icon);
        }
        if (column == 7){
        	rb = new JButton(text2,icon);
        	eb = new JButton(text2,icon);
        }
        rb.setOpaque(false);
        eb.setOpaque(false);
        rb.setContentAreaFilled(false);
        eb.setContentAreaFilled(false);
        eb.setMargin(new Insets(0,0,0,0));
        eb.setBorder(null);
        eb.setHorizontalTextPosition(SwingConstants.CENTER);
        rb.setMargin(new Insets(0,0,0,0));
        rb.setBorder(null);
        rb.setHorizontalTextPosition(SwingConstants.CENTER);
        
        eb.setFocusPainted( false );
        eb.addActionListener( this );
        //���øõ�Ԫ����Ⱦ�ͱ༭��ʽ
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer( this );
        columnModel.getColumn(column).setCellEditor( this );
    }
	@Override
	public Object getCellEditorValue() {
		// TODO Auto-generated method stub
		return null;
	}
	//����������
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(column == 7){
			try {
//				System.out.println(new File(".").getAbsolutePath());
				String path = new File(".").getAbsolutePath();
//				path = path.substring(0,path.length()-1) + "reports";
				path = "reports";
				Runtime.getRuntime().exec("cmd /c start " + path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		//System.out.println(row +" "+column);
		if (column == 1)menuView.enterTest(row);
		else menuView.enterSplit(row, column);
		
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		if (column != 7)rb.setText(text1);
		if (column == 7)rb.setText(text2);
        return rb;
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (column != 7)eb.setText(text1);
		if (column == 7)eb.setText(text2);
        this.row=row;
        return eb;
	}
	public void setMenuView(MenuView m){
		menuView = m;
	}
}
class GBC extends GridBagConstraints
{
   //��ʼ�����Ͻ�λ��
   public GBC(int gridx, int gridy)
   {
      this.gridx = gridx;
      this.gridy = gridy;
   }

   //��ʼ�����Ͻ�λ�ú���ռ����������
   public GBC(int gridx, int gridy, int gridwidth, int gridheight)
   {
      this.gridx = gridx;
      this.gridy = gridy;
      this.gridwidth = gridwidth;
      this.gridheight = gridheight;
   }

   //���뷽ʽ
   public GBC setAnchor(int anchor)
   {
      this.anchor = anchor;
      return this;
   }

   //�Ƿ����켰���췽��
   public GBC setFill(int fill)
   {
      this.fill = fill;
      return this;
   }

   //x��y�����ϵ�����
   public GBC setWeight(double weightx, double weighty)
   {
      this.weightx = weightx;
      this.weighty = weighty;
      return this;
   }

   //�ⲿ���
   public GBC setInsets(int distance)
   {
      this.insets = new Insets(distance, distance, distance, distance);
      return this;
   }

   //�����
   public GBC setInsets(int top, int left, int bottom, int right)
   {
      this.insets = new Insets(top, left, bottom, right);
      return this;
   }

   //�����
   public GBC setIpad(int ipadx, int ipady)
   {
      this.ipadx = ipadx;
      this.ipady = ipady;
      return this;
   }
}
