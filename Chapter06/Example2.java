/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 9, 2017
 */

package dawj.ch06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Example2 {
    private static double sX=0, sXX=0, sY=0, sYY=0, sXY=0;
    private static int n=0;

    public static void main(String[] args) {
        getData("data/Data1.dat");
        double m = (n*sXY - sX*sY)/(n*sXX - sX*sX);
        double b = sY/n - m*sX/n;
        double r2 = m*m*(n*sXX - sX*sX)/(n*sYY - sY*sY);
        double r = Math.sqrt(r2);
        double tv = sYY - sY*sY/n;
        double mX = sX/n;  // mean value of x
        double ev = (sXX - 2*mX*sX + n*mX*mX)*m*m;
        double uv = tv - ev;
        
        System.out.printf("y = %.6fx + %.4f%n", m, b);
        System.out.printf("r = %.6f%n", r);
        System.out.printf("r2 = %.6f%n", r2);
        System.out.printf("EV = %.5f%n", ev);
        System.out.printf("UV = %.4f%n", uv);
        System.out.printf("TV = %.3f%n", tv);
    }
    
    public static void getData(String data) {
        try {
            Scanner fileScanner = new Scanner(new File(data));
            fileScanner.nextLine();  // read past title line
            n = fileScanner.nextInt();
            fileScanner.nextLine();  // read past line of labels
            fileScanner.nextLine();  // read past line of labels
            for (int i = 0; i < n; i++) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("\\t");
                double x = lineScanner.nextDouble();
                double y = lineScanner.nextDouble();
                sX += x;
                sXX += x*x;
                sY += y;
                sYY += y*y;
                sXY += x*y;
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
}
