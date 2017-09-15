/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 15, 2017
 */

package dawj.ch03;

import java.util.Random;

public class ArrivalTimesTester {
    static final Random random = new Random();
    static final double LAMBDA = 0.25;
    
    static double time() {
        double p = random.nextDouble();
        return -Math.log(1 - p)/LAMBDA;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            System.out.println(time());
        }
    }
}
