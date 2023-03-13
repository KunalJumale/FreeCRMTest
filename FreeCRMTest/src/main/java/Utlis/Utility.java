package Utlis;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hpsf.Date;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utility {

		
		public static void CaptureScreenShot(WebDriver driver,int testID) throws IOException
		{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh-mm-ss");
		Date date = new Date();
		String result =formatter.format(date);
		System.out.println(result);

		TakesScreenshot ts = (TakesScreenshot)driver;
		File source  = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("C:\\Users\\jumal\\Downloads\\Automation\\ScreenShotFlipkartOriginal"
				+testID+" "+result+".jpeg");
		FileHandler.copy(source, dest);
		}



		public static String newfetchDataFromExcel(String exsheet,int rownum,int cellnum) throws EncryptedDocumentException, IOException
		{
		String path ="src\\main\\resources\\TestData1\\TestDataCRM.xlsx";
		FileInputStream file = new FileInputStream(path);
		String data= " ";

		Workbook book = WorkbookFactory.create(file);
		try {
		data = book.getSheet(exsheet).getRow(rownum).getCell(cellnum).getStringCellValue();
		}

		catch(Exception c) {
		double value = book.getSheet(exsheet).getRow(rownum).getCell(cellnum).getNumericCellValue();
		  //data = String.valueOf(value);

		String str = Long.toString((long)value);
		data = str;
		 
		}
		return data;
}
}