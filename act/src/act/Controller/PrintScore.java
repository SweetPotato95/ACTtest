package act.Controller;

import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileOutputStream; 
import java.io.IOException;
import java.util.Calendar;

import com.itextpdf.text.Document; 
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter; 

public class PrintScore {
	private Document doc;
//	private Object[][] values ={ 
//            {"1","A","A","1"}, 
//            {"2","B","B","1"}, 
//            {"3","C","C","1"}, 
//            {"4","B","A","1"}, 
//            {"5","A","A","0"} 
//	};
	public void writePDF(String testname, String name, Object[][][] values, Object[][] totalScore){
		doc = new Document(PageSize.A4);
		Calendar c = Calendar.getInstance();
		System.out.println(c.getTime());
		String path = new File(".").getAbsolutePath();
		path = path.substring(0,path.length()-1) + "reports\\";
		path += name + ".pdf";
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(path));
			doc.open();
			Paragraph  pr = new Paragraph("Test Name : " + testname + "   Time: " + c.getTime());
			pr.setSpacingAfter(5);
			doc.add(pr);
			pr = new Paragraph("English Score:");
			pr.setSpacingAfter(5);
			doc.add(pr);
			PdfPTable mainTable = getTable(values[0]);
			doc.add(mainTable);
			pr = new Paragraph("Math Score:");
			pr.setSpacingAfter(5);
			doc.add(pr);
			mainTable = getTable(values[1]);
			doc.add(mainTable);
			pr = new Paragraph("Reading Score:");
			pr.setSpacingAfter(5);
			doc.add(pr);
			mainTable = getTable(values[2]);
			doc.add(mainTable);
			pr = new Paragraph("Science Score:");
			pr.setSpacingAfter(5);
			doc.add(pr);
			mainTable = getTable(values[3]);
			doc.add(mainTable);
			pr = new Paragraph("Total score: ");
			pr.setSpacingAfter(5);
			doc.add(pr);
			for (int i = 0; i < 4; i++){
				pr = new Paragraph(totalScore[i][0].toString() + " : " + totalScore[i][1].toString());
				pr.setSpacingAfter(5);
				pr.setIndentationLeft(12); 
				doc.add(pr);
			}
			doc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private PdfPTable getTable(Object[][] values){
		PdfPTable mainTable = new PdfPTable(3);
		mainTable.setSplitLate(false);
		mainTable.setSplitRows(true);
		PdfPCell cell = new PdfPCell(new Paragraph("Num"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mainTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Your Answer"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mainTable.addCell(cell);
		cell = new PdfPCell(new Paragraph("Correct Answer"));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		mainTable.addCell(cell);
		for (int i = 0; i < values.length; i++){
			for (int j = 0; j < 3; j++){
				cell = new PdfPCell(new Paragraph(values[i][j].toString()));
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				mainTable.addCell(cell);
			}
		}
		return mainTable;
	}
}
