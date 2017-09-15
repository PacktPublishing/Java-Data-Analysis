/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 16, 2017
 */

package dawj.ch06;

import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class Example6 {
    static double[][] x = { {10, 59}, {9, 57}, {12, 61}, {10, 52}, {9, 48}, 
            {10, 55}, {8, 51}, {11, 62} };
    static double[] y = {71, 68, 76, 56, 57, 77, 55, 67};

    public static void main(String[] args) {
        OLSMultipleLinearRegression mlr = new OLSMultipleLinearRegression();
        mlr.newSampleData(y, x);
        double[] b = mlr.estimateRegressionParameters();
        printResults(b);
    }
    
    private static void printResults(double[] b) {
        System.out.printf("f(s, t) = %.2f + %.2fs + %.2ft%n", b[0], b[1], b[2]);
        System.out.printf("f(10, 59) = %.1f%n", f(10, 59, b));
        System.out.printf("f(9, 57) = %.1f%n", f(9, 57, b));
        System.out.printf("f(11, 64) = %.1f%n", f(11, 64, b));
    }
    
    private static double f(double s, double t, double[] b) {
        return b[0] + b[1]*s + b[2]*t;
    }
}

/*
run:
f(s, t) = -5.75 + 1.55s + 1.01t
f(10, 59) = 69.5
f(9, 57) = 65.9
f(11, 64) = 76.1
*/
