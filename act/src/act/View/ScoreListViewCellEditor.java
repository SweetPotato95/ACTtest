package act.View;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
public class ScoreListViewCellEditor extends JPanel implements TableCellEditor,ActionListener{
 private static final long serialVersionUID = 5860619160549087886L; 
 //EventListenerList:保存EventListener 列表的类。 
 private EventListenerList listenerList = new EventListenerList(); 
 //ChangeEvent用于通知感兴趣的参与者事件源中的状态已发生更改。 
 private ChangeEvent changeEvent = new ChangeEvent(this); 
 JButton edit_btn;
 JTextField edit_txf;
 
 private int row;
 
 
 public ScoreListViewCellEditor(){ 
	  super();
	  setLayout(new BorderLayout());
	
	  edit_btn = new JButton("More details");
	  edit_txf = new JTextField();
	  edit_txf.setOpaque(false);
	  edit_txf.setBorder(null);
	  edit_txf.setEditable(false);
	  add(edit_txf);
	  add(edit_btn,BorderLayout.EAST);
	  edit_btn.setBackground(Color.lightGray);
	  edit_btn.setPreferredSize(new Dimension(130,getHeight()));
	  edit_btn.addActionListener(this);    //给单元格的JButton添加ActionListener，以便于弹出JDialog
 }
 public void addCellEditorListener(CellEditorListener l) { 
	 listenerList.add(CellEditorListener.class,l); 
 } 
 public void removeCellEditorListener(CellEditorListener l) { 
	 listenerList.remove(CellEditorListener.class,l); 
 } 
 private void fireEditingStopped(){ 
	  CellEditorListener listener; 
	  Object[]listeners = listenerList.getListenerList(); 
	  for(int i = 0; i < listeners.length; i++){ 
		   if(listeners[i]== CellEditorListener.class){ 
		    //之所以是i+1，是因为一个为CellEditorListener.class（Class对象）， 
		    //接着的是一个CellEditorListener的实例 
		    listener= (CellEditorListener)listeners[i+1]; 
		    //让changeEvent去通知编辑器已经结束编辑 
		    //          //在editingStopped方法中，JTable调用getCellEditorValue()取回单元格的值， 
		    //并且把这个值传递给TableValues(TableModel)的setValueAt() 
		    listener.editingStopped(changeEvent); 
		   } 
	  } 
 } 
 public void cancelCellEditing() {          
 } 
  
 public boolean stopCellEditing() { 
  //可以注释掉下面的fireEditingStopped();，然后在GenderEditor的构造函数中把 
  //addActionListener()的注释去掉（这时请求终止编辑操作从JComboBox获得）， 
  fireEditingStopped();//请求终止编辑操作从JTable获得 
  return true; 
 } 
  
 public Component getTableCellEditorComponent(JTable table, Object value, 
   boolean isSelected, int row, int column) { 
  if(value != null)
   edit_txf.setText(value.toString());
  this.row = row;
  return this; 
 } 
  
 public boolean isCellEditable(EventObject anEvent) { 
  return true; 
 } 
  
 public boolean shouldSelectCell(EventObject anEvent) { 
  return true; 
 } 
  
 public Object getCellEditorValue() { 
  return edit_txf.getText(); 
 } 
 public void actionPerformed(ActionEvent e){
	 if(row == 4){
		 System.out.print("haha");
		 ScoreListViewCellDetailWriting detailDialog = new ScoreListViewCellDetailWriting(ViewConstants.SCORE_LIST_VIEW_DETAIL_PADDING_LEFT,
				  ViewConstants.SCORE_LIST_VIEW_DETAIL_PADDING_UP);
		 detailDialog.setVisible(true);
		  detailDialog.init();
		 return;
	 }
  ScoreListViewCellDetail detailDialog = new ScoreListViewCellDetail(ViewConstants.SCORE_LIST_VIEW_DETAIL_PADDING_LEFT,
		  ViewConstants.SCORE_LIST_VIEW_DETAIL_PADDING_UP);
  detailDialog.setVisible(true);
  detailDialog.setModel(row);
  detailDialog.setScore(row);
  detailDialog.init();
 }
 
}