/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 14, 2017
 */

package dawj.ch06;

import org.apache.commons.math3.linear.*;

public class Example4 {
    static double[] x = {20, 30, 40, 50, 60, 70};
    static double[] y = {52, 87, 136, 203, 290, 394};
    static int n = y.length;  // 6

    public static void main(String[] args) {
        double[][] a = new double[3][3];
        double[] w = new double[3];
        deriveNormalEquations(a, w);
        printNormalEquations(a, w);
        double[] b = solveNormalEquations(a, w);
        printResults(b);
    }

    public static void deriveNormalEquations(double[][] a, double[] w) {
        for (int i = 0; i < n; i++) {
            double xi = x[i];
            double yi = y[i];
            a[0][0] = n;
            a[0][1] = a[1][0] += xi;
            a[0][2] = a[1][1] = a[2][0] += xi*xi;
            a[1][2] = a[2][1] += xi*xi*xi;
            a[2][2] += xi*xi*xi*xi;
            w[0] += yi;
            w[1] += xi*yi;
            w[2] += xi*xi*yi;
        }
    }

    public static void printNormalEquations(double[][] a, double[] w) {
        for (int i = 0; i < 3; i++) {
            System.out.printf("%8.0fb0 + %6.0fb1 + %8.0fb2 = %7.0f%n",
                    a[i][0], a[i][1], a[i][2], w[i]);
        }
    }

    /*  Solves the matrix equation a*b = w for b[], representing a[] 
        as RealMatrix m and b[] as RealVector v: 
     */
    private static double[] solveNormalEquations(double[][] a, double[] w) {
            RealMatrix m = new Array2DRowRealMatrix(a, false);
            LUDecomposition lud = new LUDecomposition(m);
            DecompositionSolver solver = lud.getSolver();
            RealVector v = new ArrayRealVector(w, false);
            return solver.solve(v).toArray();
    }
    
    private static void printResults(double[] b) {
        System.out.printf("f(t) = %.2f + %.3ft + %.5ft^2%n", b[0], b[1], b[2]);
        System.out.printf("f(55) = %.1f%n", f(55, b));
    }
    
    private static double f(double t, double[] b) {
        return b[0] + b[1]*t + b[2]*t*t;
    }
}

/*
run:
       6b0 +    270b1 +    13900b2 =    1162
     270b0 +  13900b1 +   783000b2 =   64220
   13900b0 + 783000b1 + 46750000b2 = 3798800
f(t) = 40.73 + -1.170t + 0.08875t^2
f(55) = 244.8
*/
