/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MergingFiles {
    public static void main(String[] args) {
        File inFile1 = new File("data/Countries1.dat");
        File inFile2 = new File("data/Countries2.dat");
        File outFile = new File("data/Countries.dat");
        try {
            Scanner in1 = new Scanner(inFile1);
            Scanner in2 = new Scanner(inFile2);
            PrintWriter out = new PrintWriter(outFile);
            Country country1 = new Country(in1);
            Country country2 = new Country(in2);  
            System.out.println(country1.hashCode());
            System.out.println(country2.hashCode());
            while (!country1.isNull() && !country2.isNull()) {
                if (country1.compareTo(country2) < 0) {
                    out.println(country1);
                    country1 = new Country(in1);
                } else {
                    out.println(country2);
                    country2 = new Country(in2);
                }
            }
            while (!country1.isNull()) {
                out.println(country1);
                country1 = new Country(in1);
            }
            while (!country2.isNull()) {
                out.println(country2);
                country2 = new Country(in2);
            }
            in1.close();
            in2.close();
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }
}
