package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadDataFromExcelFiIe {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataAd.xlsx");

Workbook wb=WorkbookFactory.create(fis);
String lastname=wb.getSheet("Contacts").getRow(1).getCell(2).toString();

System.out.println(lastname);


	}

}
