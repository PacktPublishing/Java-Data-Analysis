/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class ReadingCSVFiles {
    public static void main(String[] args) {
        File dataFile = new File("data/Countries.csv");
        try {
            Scanner input = new Scanner(dataFile);
            input.useDelimiter(",|\\s");
            String column1 = input.next();
            String column2 = input.next();
            System.out.printf("%-10s%12s%n", column1, column2);
            while (input.hasNext()) {
                String country = input.next();
                int population = input.nextInt();
                System.out.printf("%-10s%,12d%n", country, population);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
