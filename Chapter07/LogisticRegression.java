/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 31, 2017
 */

package dawj.ch07;

import org.apache.commons.math3.analysis.function.*;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class LogisticRegression {
    static int n = 6;
    static double[] x = {5, 15, 25, 35, 45, 55};
    static double[] p = {2./6,2./5, 4./8, 5./9, 3./5, 4./5};
    static double[] y = new double[n];    // y = logit(p)

    public static void main(String[] args) {
        
        //  Transform p-values into y-values:
        Logit logit = new Logit();
        for (int i = 0; i < n; i++) {
            y[i] = logit.value(p[i]);
        }
        
        //  Set up input array for linear regression:
        double[][] data = new double[n][n];
        for (int i = 0; i < n; i++) {
            data[i][0] = x[i];
            data[i][1] = y[i];
        }
        
        //  Run linear regression of y on x:
        SimpleRegression sr = new SimpleRegression();
        sr.addData(data);
        
        //  Print results:
        for (int i = 0; i < n; i++) {
            System.out.printf("x = %2.0f, y = %7.4f%n", x[i], sr.predict(x[i]));
        }
        System.out.println();
        
        //  Convert y-values back to p-values:
        Sigmoid sigmoid = new Sigmoid();
        for (int i = 0; i < n; i++) {
            double p = sr.predict(x[i]);
            System.out.printf("x = %2.0f, p = %6.4f%n", x[i], sigmoid.value(p));
        }
    }
}

/*
run:
x =  5, y = -0.7797
x = 15, y = -0.4067
x = 25, y = -0.0338
x = 35, y =  0.3392
x = 45, y =  0.7121
x = 55, y =  1.0851

x =  5, p = 0.3144
x = 15, p = 0.3997
x = 25, p = 0.4916
x = 35, p = 0.5840
x = 45, p = 0.6709
x = 55, p = 0.7475
*/
