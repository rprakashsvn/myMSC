package Utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ExcelReader {

	public String filePath = "C:\\Program Files\\sw\\Automation\\myMSC New UI\\TestData\\eBookingTestData.xlsx";
	public String nameOfSheet = "Login";
	public Object[][] XLData;

	@SuppressWarnings({ "resource" })
	@DataProvider(name = "ReadExcel")
	public Object[][] readExcel() throws Exception {
		// Load the excel file
		File loadExcelFile = new File(filePath);

		// Access the excel file for read the data for using FileinputStream
		FileInputStream readExcelFile = new FileInputStream(loadExcelFile);

		// name of the file loaded for reading a data
		String excelFileLoc = loadExcelFile.getPath();
		String excelFileName = loadExcelFile.getName();
		System.out.println("Excel File Path Is : " + excelFileLoc);
		System.out.println("Excel File Name Is : " + excelFileName);

		String fileExtensionName = excelFileName.substring(excelFileName.indexOf("."));
		System.out.println("Excel File Extension Is : " + fileExtensionName);

		if (fileExtensionName.equals(".xlsx")) {

			// Create a object for XLXS files
			XSSFWorkbook XSSFWorkbooks = new XSSFWorkbook(readExcelFile);

			// Access Excel Sheets by using the object of excel and name of the sheet
			XSSFSheet sheets = XSSFWorkbooks.getSheet(nameOfSheet);

			// Get count of number of rows
			int rowCount = sheets.getLastRowNum() + 1;
			System.out.println("No of Rows in " + nameOfSheet + " is : " + rowCount);

			XSSFRow row = sheets.getRow(0);
			int colCount = row.getLastCellNum();
			XLData = new Object[rowCount][colCount];
			if (rowCount > 0) {
				int startRow = 1;
				int startCol = 0;

				for (int i = startRow; i < rowCount; i++) // loop through the rows
				{
					// Access the cell by using the from rows
					XSSFRow rowCounts = sheets.getRow(i);

					for (int j = startCol; j < row.getLastCellNum(); j++) // loop through the columns
					{
						Cell userDetails = rowCounts.getCell(j);
						Cell loginDetails = userDetails;
						System.out.println(loginDetails);
					}
					System.out.println();
				}
			}
			readExcelFile.close();
		}
		return XLData;
	}
}
