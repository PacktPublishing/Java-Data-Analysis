/*  Data Analysis with Java
 *  John R. Hubbard
 *  June 7, 2017
 */

package dawj.ch08;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.ml.clustering.CentroidCluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;
import org.apache.commons.math3.ml.clustering.KMeansPlusPlusClusterer;
import org.apache.commons.math3.ml.distance.EuclideanDistance;

public class KMeansPlusPlus {
    private static final double[][] DATA = {{1,1}, {1,3}, {1,5}, {2,6}, {3,2}, 
        {3,4}, {4,3}, {5,6}, {6,3}, {6,4}, {7,1}, {7,5}, {7,6}};
    private static final int M = DATA.length;  // number of points
    private static final int K = 3;  // number of clusters
    private static final int MAX = 100;  // maximum number of iterations
    private static final EuclideanDistance ED = new EuclideanDistance();
    
    public static void main(String[] args) {
        List<DoublePoint> points = load(DATA);
        KMeansPlusPlusClusterer<DoublePoint> clusterer;
        clusterer = new KMeansPlusPlusClusterer(K, MAX, ED);
        List<CentroidCluster<DoublePoint>> clusters = clusterer.cluster(points);
        
        for (CentroidCluster<DoublePoint> cluster : clusters) {
            System.out.println(cluster.getPoints());
        }
    }
    
    private static List<DoublePoint> load(double[][] data) {
        List<DoublePoint> points = new ArrayList(M);
        for (double[] pair : data) {
            points.add(new DoublePoint(pair));            
        }
        return points;
    } 
}
/*
run:
[[1.0, 1.0], [1.0, 3.0], [1.0, 5.0], [2.0, 6.0], [3.0, 2.0], [3.0, 4.0], [4.0, 3.0]]
[[5.0, 6.0], [6.0, 3.0], [6.0, 4.0], [7.0, 5.0], [7.0, 6.0]]
[[7.0, 1.0]]
run:
[[5.0, 6.0], [6.0, 3.0], [6.0, 4.0], [7.0, 1.0], [7.0, 5.0], [7.0, 6.0]]
[[1.0, 1.0], [1.0, 3.0], [3.0, 2.0], [3.0, 4.0], [4.0, 3.0]]
[[1.0, 5.0], [2.0, 6.0]]
*/
