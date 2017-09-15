/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 25, 2017
 */

package dawj.cho4;

import java.util.Random;

public class Simulation {
    static final Random RANDOM = new Random();
    static final int n = 5;  // number of dice used
    static final int N = 1000000;  // 1,000,000 simulations
    
    public static void main(String[] args) {
        double[] dist = new double[n+1];
        for (int i = 0; i < N; i++) {
            int x = numRedDown(n);
            ++dist[x];
        }
        for (int i = 0; i <= n; i++) {
            System.out.printf("%4d%8.4f%n", i, dist[i]/N);
        }
    }
    
    /*  Simulates the toss of one tetrahedral die that has one red face and
        three green faces. Returns false unless the face down is red.
    */
    static boolean redDown() {
        int m = RANDOM.nextInt(4);  // 0 <= m < 4
        return (m == 0);            // P(m = 0) = 1/4
    } 
    
    /*  Simulates the toss of n tetrahedral dice that have one red face and
        three green faces. Returns the number that lands with red face down.
    */
    static int numRedDown(int n) {
        int numRed = 0;
        for (int i = 0; i < n; i++) {
            if (redDown()) {
                ++numRed;
            }
        }
        return numRed;
    }
}


