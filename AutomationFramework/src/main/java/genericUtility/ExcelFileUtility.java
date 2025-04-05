package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class consists of methods related to Excel File
 */
public class ExcelFileUtility {
	
	/**
	 * This method is used to read data from excel file provide sheetname,row and cell
	 * @param sheetname
	 * @param row
	 * @param cell
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public String toReadDataFromExcelFile(String sheetname,int row ,int cell) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream(".\\src\\test\\resources\\TestDataAd.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String value = wb.getSheet(sheetname).getRow(row).getCell(cell).toString();
		return value;
	}
	
	
	
	
	

}
