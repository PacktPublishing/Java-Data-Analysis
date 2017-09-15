/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 27, 2017
 */

package dawj.ch04;

import org.apache.commons.math3.distribution.NormalDistribution;

public class NormalDistributionTester {
    static int n = 32;
    static double p = 0.5;
    static double mu = n*p;
    static double sigma = Math.sqrt(n*p*(1-p));

    public static void main(String[] args) {
        NormalDistribution nd = new NormalDistribution(mu, sigma);
        
        double a = 17.5, b = 21.5;
        double Fa = nd.cumulativeProbability(a);
        System.out.printf("F(a) = %6.4f%n", Fa);
        double Fb = nd.cumulativeProbability(b);
        System.out.printf("F(b) = %6.4f%n", Fb);
        System.out.printf("F(b) - F(a) = %6.4f%n", Fb - Fa);
    }
}
