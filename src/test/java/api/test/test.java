package api.test;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {

    public static void main(String args[]) throws IOException {


        int a=tt();
        System.out.println(a);

    }

    public static int tt() throws IOException {
        FileInputStream file = new FileInputStream("D:\\Ddrive25072022\\automationFramework\\testData\\user.xlsx");
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.getSheetAt(0);

        // Get the total number of rows, including empty ones
        int rowCount = sheet.getPhysicalNumberOfRows();

        System.out.println("Total Number of Rows: " + rowCount);


        workbook.close();
        return rowCount;
    }



}

