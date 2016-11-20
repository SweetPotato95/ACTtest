package act.Model;
public class ModelConstants{
	
	
	public static final int PARTNUM_TOTAL = 5;
	public static final int[] SPLITNUM_PER_PART = {5,1,4,7,1};
	public static final int SPLITNUM_TOTAL = 18;
	public static int[] QUESTIONNUM_PER_SPLIT={15,15,15,15,15,60,10,10,10,10,7,6,5,6,6,5,5,0};
	public static final int QUESTIONNUM_TOTAL = 215;
	public static final int[] QUESTIONNUM_PER_PART={75,60,40,40,0};
	public static int ENGLISH = 0;
	public static int MATH = 1;
	public static int READING = 2;
	public static int SCIENCE = 3;
	public static int WRITING = 4;
	
	public static String[] PARTNAME= {"English","Math","Reading","Science","Writing"};
	public static String[] TESTPATH = {"resources\\2005 April 60E\\","resources\\2005 Dec 63C\\","resources\\2005 June 61D\\"
			,"resources\\2006 April 63E\\","resources\\2006 June 63F\\","resources\\2007 Dec 65E\\",
			"resources\\2007 June 65C\\","resources\\2008 April 65D\\","resources\\2008 Dec 67A\\",
			"resources\\2009 April 66F\\","resources\\2009 Dec 68A\\","resources\\2010 April 68G\\",
			"resources\\2011 April 67F\\","resources\\2011 June 69F\\","resources\\2012 April 70G\\",
			"resources\\2012 June 70C\\"};
	public static String[] TESTNAME = {"2005 April 60E","2005 Dec 63C", "2005 June 61D","2006 April 63E","2006 June 63F",
			"2007 Dec 65E","2007 June 65C","2008 April 65D","2008 Dec 67A","2009 April 66F","2009 Dec 68A",
			"2010 April 68G","2011 April 67F","2011 June 69F","2012 April 70G","2012 June 70C"};
	public ModelConstants(){
		
	}
}