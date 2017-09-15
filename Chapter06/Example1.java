/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 9, 2017
 */

package dawj.ch06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Example1 {
    public static void main(String[] args) {
        SimpleRegression sr = getData("data/Data1.dat");
        double m = sr.getSlope();
        double b = sr.getIntercept();
        double r = sr.getR();  // correlation coefficient
        double r2 = sr.getRSquare();
        double sse = sr.getSumSquaredErrors();
        double tss = sr.getTotalSumSquares();

        System.out.printf("y = %.6fx + %.4f%n", m, b);
        System.out.printf("r = %.6f%n", r);
        System.out.printf("r2 = %.6f%n", r2);
        System.out.printf("EV = %.5f%n", tss - sse);
        System.out.printf("UV = %.4f%n", sse);
        System.out.printf("TV = %.3f%n", tss);
    }
    
    public static SimpleRegression getData(String data) {
        SimpleRegression sr = new SimpleRegression();
        try {
            Scanner fileScanner = new Scanner(new File(data));
            fileScanner.nextLine();  // read past title line
            int n = fileScanner.nextInt();
            fileScanner.nextLine();  // read past line of labels
            fileScanner.nextLine();  // read past line of labels
            for (int i = 0; i < n; i++) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("\\t");
                double x = lineScanner.nextDouble();
                double y = lineScanner.nextDouble();
                sr.addData(x, y);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return sr;
    }
}

/*
run:
y = 0.882279x + 18.8739
r = 0.935222
r2 = 0.874641
sse = 204.004243
tss = 1627.361
*/