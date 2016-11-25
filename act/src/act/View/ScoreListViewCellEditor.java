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
 //EventListenerList:����EventListener �б���ࡣ 
 private EventListenerList listenerList = new EventListenerList(); 
 //ChangeEvent����֪ͨ����Ȥ�Ĳ������¼�Դ�е�״̬�ѷ������ġ� 
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
	  edit_btn.addActionListener(this);    //����Ԫ���JButton���ActionListener���Ա��ڵ���JDialog
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
		    //֮������i+1������Ϊһ��ΪCellEditorListener.class��Class���󣩣� 
		    //���ŵ���һ��CellEditorListener��ʵ�� 
		    listener= (CellEditorListener)listeners[i+1]; 
		    //��changeEventȥ֪ͨ�༭���Ѿ������༭ 
		    //          //��editingStopped�����У�JTable����getCellEditorValue()ȡ�ص�Ԫ���ֵ�� 
		    //���Ұ����ֵ���ݸ�TableValues(TableModel)��setValueAt() 
		    listener.editingStopped(changeEvent); 
		   } 
	  } 
 } 
 public void cancelCellEditing() {          
 } 
  
 public boolean stopCellEditing() { 
  //����ע�͵������fireEditingStopped();��Ȼ����GenderEditor�Ĺ��캯���а� 
  //addActionListener()��ע��ȥ������ʱ������ֹ�༭������JComboBox��ã��� 
  fireEditingStopped();//������ֹ�༭������JTable��� 
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