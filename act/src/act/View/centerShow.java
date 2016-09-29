package act.View;

import java.awt.*;
import javax.swing.JFrame;

public class centerShow {
	public centerShow(Dialog dialog){
		dialog.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = dialog.getSize();
		
		if (screenSize.height < dialogSize.height){
			dialogSize.height = screenSize.height;
		}
		if (screenSize.width < dialogSize.width){
			dialogSize.width = screenSize.width;
		}
		dialog.setLocation((screenSize.width-dialogSize.width)/2, (screenSize.height-dialogSize.height)/2);
	}
	public centerShow(JFrame dialog){
		dialog.setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension dialogSize = dialog.getSize();
		//System.out.println(dialogSize.width+" "+dialogSize.height);
		if (screenSize.height < dialogSize.height){
			dialogSize.height = screenSize.height;
		}
		if (screenSize.width < dialogSize.width){
			dialogSize.width = screenSize.width;
		}
		dialog.setLocation((screenSize.width-dialogSize.width)/2, (screenSize.height-dialogSize.height)/2);
	}
}