package tn.dalhia.shared.tools;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import tn.dalhia.entities.User;

//localhost

public class UserPDFExporter {
	private List<User> listUsers;

	public UserPDFExporter(List<User> listUsers) {
		this.listUsers = listUsers;
	}
	
	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell(); 
		cell.setBackgroundColor(Color.DARK_GRAY);
		cell.setPadding(5);
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);
		
		cell.setPhrase(new Phrase("ID" , font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Role" , font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("First Name" , font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Last Name" , font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email" , font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Phone", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Date Birth", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);
		cell.setPhrase(new Phrase("Email Verif", font));
		table.addCell(cell);

		
	}
	
	private void writeTableData(PdfPTable table) {
		for (User user : listUsers ) {
			table.addCell(String.valueOf(user.getId()));
			table.addCell(String.valueOf(user.getRole()));
			table.addCell(user.getFirst_name());
			table.addCell(user.getLast_name());
			table.addCell(user.getEmail());
			table.addCell(user.getPhone());
			table.addCell(String.valueOf(user.getDate_birth()));
			table.addCell(user.getAddress());
			table.addCell(String.valueOf(user.getEmailVerificationStatus()));
		}
	}
	
	
	
	public void export(HttpServletResponse response) throws DocumentException, IOException { //caught by the controller
		Document document = new Document(PageSize.A4);
		
		String imageSrc = "ImagePdf/Logo_ESPRIT_Ariana.jpg";
		String imageSrc2 = "ImagePdf/dalhia4.png";

		
		
		
		
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.DARK_GRAY);
		font.setSize(18);	
		
		Image esprit = Image.getInstance(imageSrc);
		Image dalhia = Image.getInstance(imageSrc2);
		
	
		dalhia.scalePercent(50);
		dalhia.setAbsolutePosition(120, 300);
       
        document.add(dalhia);
		
		
		Paragraph title = new Paragraph("List of all our users" , font);
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);
		
		
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] {1.0f,2f,2f,3f,3.5f,2.5f,3.5f,2.5f,2.0f});
		
		
		
		writeTableHeader(table);
		writeTableData(table);
		
		document.add(dalhia);
		document.add(table);
		
		
		esprit.scalePercent(20);
		esprit.setAbsolutePosition(400, 5);
		document.add(esprit);
		
		
		
		document.close();
	}
}
