package act.View;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;

import java.awt.*;

public class ScoreListViewCellRenderer extends JPanel implements TableCellRenderer {
        JButton edit_btn;
        JTextField edit_txf;
        public ScoreListViewCellRenderer(){ 
              super();
             setLayout(new BorderLayout());
             edit_btn = new JButton("...");
             edit_txf = new JTextField();
             edit_txf.setOpaque(false);
             edit_txf.setBorder(null);
             add(edit_txf);
             add(edit_btn,BorderLayout.EAST);
             edit_btn.setBackground(Color.lightGray);
             edit_btn.setPreferredSize(new Dimension(30,getHeight()));
       } 
 
     public Component getTableCellRendererComponent(JTable table, Object value, 
          boolean isSelected, boolean hasFocus, int row, int column) { 
          if(isSelected){ 
              setForeground(table.getForeground()); 
               super.setBackground(table.getBackground()); 
          }else{ 
               setForeground(table.getForeground()); 
               setBackground(table.getBackground()); 
         } 
       if(value != null)
             edit_txf.setText(value.toString());
             return this; 
        } 
}