package act.View;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.*;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.*;

import act.MainActivity;

public class MenuView extends JPanel{
	private JTable table = null;
	private MyTableModel model = null;
	private MainActivity mainActivity;
	
	
	public MenuView(){
		init();
	}
	
	private void init(){
		this.setSize(ViewConstants.MAINPANEL_WIDTH,ViewConstants.MAINPANEL_HEIGHT);
		this.setPreferredSize(new Dimension(ViewConstants.MAINPANEL_WIDTH,ViewConstants.MAINPANEL_HEIGHT));
		
		
		model = new MyTableModel();
		table = new JTable(model);
		
		ButtonColumn buttonColumn1 = new ButtonColumn(table,1);
		ButtonColumn buttonColumn2 = new ButtonColumn(table,2);
		
		buttonColumn1.setMenuView(this);
		buttonColumn2.setMenuView(this);
//		table.setEnabled(false);
		table.setShowVerticalLines(false);
		//table.setRowHeight(28);
		table.setFont(new Font("΢���ź�",Font.PLAIN,14));
		// �־���
		DefaultTableCellRenderer render = new DefaultTableCellRenderer();
	    render.setHorizontalAlignment(SwingConstants.CENTER);
	    table.setDefaultRenderer(table.getColumnClass(0), render);
	    // ��ͷ����
	    JTableHeader header = table.getTableHeader();
	    header.setFont(new Font("΢���ź�", Font.PLAIN, 16));
	    header.setPreferredSize(new Dimension(header.getWidth(), 32));
	    
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBorder(BorderFactory.createEtchedBorder());
		this.add(scroll);
		this.setVisible(true);
	}
	
	public void setMainActivity(MainActivity m){
		mainActivity = m;
	}
	public void enterTest(int i){
		mainActivity.initMainView(i);
	}
}

class MyTableModel extends AbstractTableModel{
	//��Ԫ��Ԫ������
	private Class[] cellType={String.class,JButton.class,JButton.class};
	//��ͷ
	private String[] tests = {"2005 April 60E","2006 May 70F"};
	private String[] colNames = {"Test","Complete","Report"};
	//ģ������
	private Object[][] obj = null;
	public MyTableModel(){
		obj = new Object[2][3];
		for (int i = 0; i < 2; i++){
			for (int j = 0; j < 3; j++){
				switch(j){
				case 0:
					obj[i][j] = tests[i];
					break;
				case 1:
					obj[i][j] = new JButton();
					break;
				case 2:
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
        return true;
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
    private ImageIcon icon = new ImageIcon("ACT resources\\lib\\button.gif");
    private MenuView menuView;
    
    public ButtonColumn(){}
    public ButtonColumn(JTable table, int column)
    {
        super();
        this.table = table;
        this.column = column;
        if (column == 1){
        	rb = new JButton(text1,icon);
        	eb = new JButton(text1,icon);
        }
        if (column == 2){
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
		if(column == 2)return;
		//System.out.println(row +" "+column);
		menuView.enterTest(row);
		
	}
	@Override
	public Component getTableCellRendererComponent(JTable arg0, Object value,
			boolean arg2, boolean arg3, int arg4, int arg5) {
		if (column == 1)rb.setText(text1);
		if (column == 2)rb.setText(text2);
        return rb;
	}
	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
			boolean isSelected, int row, int column) {
		if (column == 1)eb.setText(text1);
		if (column == 2)eb.setText(text2);
        this.row=row;
        return eb;
	}
	public void setMenuView(MenuView m){
		menuView = m;
	}
}