/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 28, 2017
 */

package dawj.ch07;

import java.io.File;
import java.util.Set;

public class BayesianTest {
    private static Set<Fruit> fruits;
    
    public static void main(String[] args) {
        fruits = Fruit.loadData(new File("data/Fruit.arff"));
        Fruit fruit = new Fruit("cola", "SMALL", "RED", "SMOOTH", false);
        double n = fruits.size();  // total number of fruits in training set
        double sum1 = 0;           // number of sweet fruits
        for (Fruit f : fruits) {
            sum1 += (f.sweet? 1: 0);
        }
        double sum2 = n - sum1;    // number of sour fruits
        double[][] p = new double[4][3];
        for (Fruit f : fruits) {
            if (f.sweet) {
                p[1][1] += (f.size.equals(fruit.size)? 1: 0)/sum1;
                p[2][1] += (f.color.equals(fruit.color)? 1: 0)/sum1;
                p[3][1] += (f.surface.equals(fruit.surface)? 1: 0)/sum1;
            } else {
                p[1][2] += (f.size.equals(fruit.size)? 1: 0)/sum2;
                p[2][2] += (f.color.equals(fruit.color)? 1: 0)/sum2;
                p[3][2] += (f.surface.equals(fruit.surface)? 1: 0)/sum2;
            }
        }
        double pc1 = p[1][1]*p[2][1]*p[3][1]*sum1/n;
        double pc2 = p[1][2]*p[2][2]*p[3][2]*sum2/n;
        System.out.printf("pc1 = %.4f, pc2 = %.4f%n", pc1, pc2);
        System.out.printf("Predict %s is %s.%n", 
                fruit.name, (pc1 > pc2? "sweet": "sour"));
    }
}

