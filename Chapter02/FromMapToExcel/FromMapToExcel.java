/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class FromMapToExcel {
    public static void main(String[] args) {
        Map<String,Integer> map = new TreeMap();
        load(map, "data/Countries.dat");
        print(map);
        storeXL(map, "data/Countries.xls", "Countries Worksheet");
    }
    
    /** Loads the data from the specified file into the specified map.
    */
    public static void load(Map map, String fileSpec) {
        File file = new File(fileSpec);
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String country = input.next();
                int population = input.nextInt();
                map.put(country, population);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
    
    public static void print(Map map) {
        Set countries = map.keySet();
        for (Object country : countries) {
            Object population = map.get(country);
            System.out.printf("%-10s%,12d%n", country, population);
        }
    }
    
    /** Stores the specified map in the specified worksheet of 
        the specified Excel workbook file.
     * @param map
     * @param fileSpec
     * @param sheet
    */
    public static void storeXL(Map map, String fileSpec, String sheet) {
        try {
            FileOutputStream out = new FileOutputStream(fileSpec);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet worksheet = workbook.createSheet(sheet);
            Set countries = map.keySet();
            short rowNum = 0;
            for (Object country : countries) {
                Object population = map.get(country);
                HSSFRow row = worksheet.createRow(rowNum);
                row.createCell(0).setCellValue((String)country);
                row.createCell(1).setCellValue((Integer)population);
                ++rowNum;
            }
            workbook.write(out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

