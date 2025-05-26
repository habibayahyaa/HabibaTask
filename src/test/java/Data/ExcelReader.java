package Data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static Object[][] getExcelData(String sheetName) {
        String filePath = "src/test/java/data/Book1.xlsx";
        Object[][] data = null;

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            int numRows = sheet.getPhysicalNumberOfRows();

            if (numRows == 0) {
                return new Object[0][0];
            }

            Row row = sheet.getRow(0); // احنا قارئين بس أول صف
            int numCols = row.getPhysicalNumberOfCells();
            data = new Object[1][numCols];

            for (int j = 0; j < numCols; j++) {
                Cell cell = row.getCell(j);
                data[0][j] = (cell != null) ? cell.toString() : "";
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
