/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class GeneratingTestData {
    private static final int ROWS = 8, COLS = 5;
    private static final Random RANDOM = new Random();
    
    public static void main(String[] args) {
        File outputFile = new File("data/Output.csv");
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            for (int i = 0; i < ROWS; i++) {
                for (int j = 0; j < COLS-1; j++) {
                    writer.printf("%.6f,", RANDOM.nextDouble());
                }
                writer.printf("%.6f%n", RANDOM.nextDouble());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
