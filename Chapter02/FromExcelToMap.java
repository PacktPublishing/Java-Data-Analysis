/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import static dawj.ch02.FromMapToExcel.print;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;

public class FromExcelToMap {
    public static void main(String[] args) {
        Map map = loadXL("data/Countries.xls", "Countries Worksheet");
        print(map);
    }
    
    /** Returns a Map object containing the data from the specified 
        worksheet in the specified Excel file.
    */
    public static Map loadXL(String fileSpec, String sheetName) {
        Map<String,Integer> map = new TreeMap();
        try {
            FileInputStream stream = new FileInputStream(fileSpec);
            HSSFWorkbook workbook = new HSSFWorkbook(stream);
            HSSFSheet worksheet = workbook.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
            for (Row row : worksheet) {
                HSSFRow hssfRow = (HSSFRow)row;
                HSSFCell cell = hssfRow.getCell(0);
                String country = cell.getStringCellValue();
                cell = hssfRow.getCell(1);
                String str = formatter.formatCellValue(cell);
                int population = (int)Integer.getInteger(str);
                map.put(country, population);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return map;
    }
}
