/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 18, 2017
 */

package dawj.ch09;

public class HammingTest {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 1000000000; i++) {
            long a = Double.doubleToLongBits(Math.random());
            long b = Double.doubleToLongBits(Math.random());
            if (a == b) {
                ++count;
            }
        }
        System.out.println(count);
    }
}

/*
run:
0
BUILD SUCCESSFUL (total time: 49 seconds)
*/
