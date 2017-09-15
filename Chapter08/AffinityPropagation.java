/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jun 23, 2017
 */

package dawj.ch08;

public class AffinityPropagation {
    private static double[][] x = {{1,2}, {2,3}, {4,1}, {4,4}, {5,3}};
    private static int n = x.length;                 // number of points
    private static double[][] s = new double[n][n];  // similarities
    private static double[][] r = new double[n][n];  // responsibilities
    private static double[][] a = new double[n][n];  // availabilities
    private static final int ITERATIONS = 10;
    private static final double DAMPER = 0.5;

    public static void main(String[] args) {
        initSimilarities();
        for (int i = 0; i < ITERATIONS; i++) {
            updateResponsibilities();
            updateAvailabilities();
        }
        printResults();
    }
    
    private static void initSimilarities() {
        double sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                sum += s[i][j] = s[j][i] = negSqEuclidDist(x[i], x[j]);
            }
        }
        double average = 2*sum/(n*n - n);  // average of s[i][j] for j < i
        for (int i = 0; i < n; i++) {
            s[i][i] = average;
        }
    }
    
    private static void updateResponsibilities() {
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                double oldValue = r[i][k];
                double max = Double.NEGATIVE_INFINITY;
                for (int j = 0; j < n; j++) {
                    if (j != k) {
                        max = Math.max(max, a[i][j] + s[i][j]);
                    }
                }
                double newValue = s[i][k] - max;
                r[i][k] = DAMPER*oldValue + (1 - DAMPER)*newValue;
            }
        }
    }
    
    private static void updateAvailabilities() {
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                double oldValue = a[i][k];
                double newValue = Math.min(0, r[k][k] + sumOfPos(i,k));
                if (k == i) {
                    newValue = sumOfPos(k,k);
                }
                a[i][k] = DAMPER*oldValue + (1 - DAMPER)*newValue;
            }
        }
    }
    
    /*  Returns the negative square of the Euclidean distance from x to y.
    */
    private static double negSqEuclidDist(double[] x, double[] y) {
        double d0 = x[0] - y[0];
        double d1 = x[1] - y[1];
        return -(d0*d0 + d1*d1);
    }
    
    /*  Returns the sum of the positive r[j][k] excluding r[i][k] and r[k][k].
    */
    private static double sumOfPos(int i, int k) {
        double sum = 0;
        for (int j = 0; j < n; j++) {
            if (j != i && j != k) {
                sum += Math.max(0, r[j][k]);
            }
        }
        return sum;
    }
    
    private static void printResults() {
        for (int i = 0; i < n; i++) {
            double max = a[i][0] + r[i][0];
            int k = 0;
            for (int j = 1; j < n; j++) {
                double arij = a[i][j] + r[i][j];
                if (arij > max) {
                    max = arij;
                    k = j;
                }
            }
            System.out.printf("point %d has exemplar point %d%n", i, k);
        }
    }
}
/*
run:
point 0 has exemplar point 1
point 1 has exemplar point 1
point 2 has exemplar point 4
point 3 has exemplar point 4
point 4 has exemplar point 4
*/
