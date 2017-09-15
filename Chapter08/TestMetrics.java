/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jun 12, 2017
 */

package dawj.ch08;

import org.apache.commons.math3.ml.distance.*;

public class TestMetrics {
    public static void main(String[] args) {
        double[] x = {1, 3}, y = {5, 6};
        
        EuclideanDistance eD = new EuclideanDistance();
        System.out.printf("Euclidean distance = %.2f%n", eD.compute(x,y));
        
        ManhattanDistance mD = new ManhattanDistance();
        System.out.printf("Manhattan distance = %.2f%n", mD.compute(x,y));
        
        ChebyshevDistance cD = new ChebyshevDistance();
        System.out.printf("Chebyshev distance = %.2f%n", cD.compute(x,y));
        
        CanberraDistance caD = new CanberraDistance();
        System.out.printf("Canberra distance =  %.2f%n", caD.compute(x,y));
    }
}

/*
run:
Euclidean distance = 5.00
Manhattan distance = 7.00
Chebyshev distance = 4.00
Canberra distance =  1.00
*/
