package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility {
    public FileInputStream fi;
    public FileOutputStream fo;
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    public XSSFRow row;
    public XSSFCell cell;
    public CellStyle style;
    String path;
    public  XLUtility(String path){
        this.path=path;
    }

    public int getrow() throws IOException {
        File f= new File("D:\\Ddrive25072022\\automationFramework\\testData\\user.xlsx");
        FileInputStream fis = new FileInputStream(f);
        Workbook wb=WorkbookFactory.create(fis);
        wb.getSheet("Sheet");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Sheet");
        XSSFRow row = sheet.getRow(0);
        int colNum = row.getLastCellNum();
        System.out.println("Total Number of Columns in the excel is : "+colNum);
        int rowNum = sheet.getLastRowNum()+1;
        System.out.println("Total Number of Rows in the excel is : "+rowNum);


        return colNum;
    }





    public int getRowCount(String sheetName) throws IOException {
     fi= new FileInputStream(path);
     workbook=new XSSFWorkbook(fi);
     sheet= workbook.getSheet(sheetName);
     int rowcount=sheet.getLastRowNum();
     workbook.close();
     fi.close();
     return rowcount;
     }

    public int getCellCount(String sheetName,int rownum) throws IOException {
        fi= new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row= sheet.getRow(rownum);
        int cellCount=row.getLastCellNum();
        workbook.close();
        fi.close();
        return cellCount;
    }

    public String getCellData(String sheetName, int rownum, int column) throws IOException {
        fi= new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        sheet= workbook.getSheet(sheetName);
        row= sheet.getRow(rownum);
        cell=row.getCell(column);
        DataFormatter formatter= new DataFormatter();
        String data;
        try{
            data= formatter.formatCellValue(cell);
        }
        catch (Exception e)
        {
            data="";
        }
        workbook.close();
        fi.close();
        return data;
    }

    public void setCellData(String sheetName, int rownum, int column,String data) throws IOException {
        File xlfile= new File(path);
        if(!xlfile.exists())
        {
            workbook=new XSSFWorkbook();
            fo=new FileOutputStream(path);
            workbook.write(fo);
        }

        fi=new FileInputStream(path);
        workbook=new XSSFWorkbook(fi);
        if(workbook.getSheetIndex(sheetName)==-1)
            workbook.createSheet(sheetName);
        sheet=workbook.getSheet(sheetName);

        if(sheet.getRow(rownum)==null)
            sheet.createRow(rownum);
        row=sheet.getRow(rownum);
        cell=row.createCell(column);
        cell.setCellValue(data);
        fo=new FileOutputStream(path);
        workbook.write(fo);
        workbook.close();
        fi.close();
        fo.close();

    }



}
