package act.View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

public class ViewConstants{
	public static int MAINPANEL_WIDTH = 1024;
	public static int MAINPANEL_HEIGHT = 800;
	public static int NAV_WIDTH;
	public static int NAV_HEIGHT = 50;
	public static int CHOICEPANE_PART_WIDTH;
	public static int CHOICEPANE_WHOLE_WIDTH;
	public static int CHOICEPANE_HEIGHT;
	public static int MAINCONTENT_WIDTH;
	public static int MAINCONTENT_HEIGHT;
	public static int PASSAGEPANE_WIDTH;
	public static int PASSAGEVIEW_LAYER= 0;
	public static int CHOICEVIEW_LAYER = 1;
	public static int WRITINGVIEW_LAYER = 2;
	public static int INSTRUCTIONVIEW_LAYER = 3;
	public static int PASSAGEPANE_HEIGHT;
	public static int DISPALY_CHOICE_PANE_PART = 1;
	public static int DISPALY_CHOICE_PANE_WHOLE = 2;

	public static int WRITING_INPUTPANE_WIDTH;
	public static int WRITING_INPUTPANE_HEIGHT;
	public static int WRITING_PASSAGEPANE_WIDTH;
	public static int WRITING_PASSAGEPANE_HEIGHT;
	
	public static int SCORE_LIST_VIEW_DETAIL_WIDTH = 500;
	public static int SCORE_LIST_VIEW_DETAIL_HEIGHT = 400;
	public static int SCORE_LIST_VIEW_DETAIL_PADDING_UP ;
	public static int SCORE_LIST_VIEW_DETAIL_PADDING_DOWN ;
	public static int SCORE_LIST_VIEW_DETAIL_PADDING_LEFT ;
	public static int SCORE_LIST_VIEW_DETAIL_PADDING_RIGHT ;
	
	public static Font choiceFont = new Font("Microsoft Yahei",Font.PLAIN,15);
	public static Font choiceTitleFont = new Font("Microsoft Yahei",Font.PLAIN,18);
	public static Font instructionTitleFont = new Font("Microsoft Yahei",Font.BOLD,30);
	public static Font instructionBodyFont = new Font("Microsoft Yahei",Font.PLAIN,20);
	public static int[] TIME_LIMIT_PER_PART = {5,500,1000,10,10};
	
	static{
		Dimension sc = Toolkit.getDefaultToolkit().getScreenSize();
		MAINPANEL_WIDTH = (int) Math.ceil(sc.getWidth());
		MAINPANEL_HEIGHT = (int)Math.ceil(sc.getHeight());
		NAV_WIDTH = MAINPANEL_WIDTH;
		MAINCONTENT_WIDTH = (int) Math.floor(0.98*MAINPANEL_WIDTH);
		MAINCONTENT_HEIGHT = MAINPANEL_HEIGHT - NAV_HEIGHT-50;
		CHOICEPANE_HEIGHT = (int) Math.floor(0.9*MAINCONTENT_HEIGHT);
		CHOICEPANE_PART_WIDTH = (int) Math.floor(0.3*MAINCONTENT_WIDTH);
		CHOICEPANE_WHOLE_WIDTH = MAINCONTENT_WIDTH;
		PASSAGEPANE_WIDTH = (int) Math.floor(0.65*MAINCONTENT_WIDTH);
		PASSAGEPANE_HEIGHT = (int) Math.floor(0.9*MAINCONTENT_HEIGHT);
		
		WRITING_INPUTPANE_WIDTH = (int)Math.floor(0.55*MAINCONTENT_WIDTH);
		WRITING_INPUTPANE_HEIGHT = (int) Math.floor(0.9*MAINCONTENT_HEIGHT);
		WRITING_PASSAGEPANE_WIDTH = (int)Math.floor(0.4*MAINCONTENT_WIDTH);
		WRITING_PASSAGEPANE_HEIGHT = (int) Math.floor(0.9*MAINCONTENT_HEIGHT);
		SCORE_LIST_VIEW_DETAIL_PADDING_LEFT = (MAINCONTENT_WIDTH-SCORE_LIST_VIEW_DETAIL_WIDTH)/2;
		SCORE_LIST_VIEW_DETAIL_PADDING_UP = NAV_HEIGHT + (MAINCONTENT_HEIGHT - SCORE_LIST_VIEW_DETAIL_HEIGHT)/2;
	}
	public ViewConstants()
	{
		
	}
};
