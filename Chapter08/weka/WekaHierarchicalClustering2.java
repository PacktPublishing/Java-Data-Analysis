/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jun 18, 2017
 */

package dawj.ch08;

import java.awt.BorderLayout;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JFrame;
import weka.clusterers.HierarchicalClusterer;
import static weka.clusterers.HierarchicalClusterer.TAGS_LINK_TYPE;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.SparseInstance;
import weka.gui.hierarchyvisualizer.HierarchyVisualizer;

public class WekaHierarchicalClustering2 {
    private static final double[][] DATA = {{1,1}, {1,3}, {1,5}, {2,6}, {3,2}, 
        {3,4}, {4,3}, {5,6}, {6,3}, {6,4}, {7,1}, {7,5}, {7,6}};
    private static final int M = DATA.length;  // number of points
    private static final int K = 3;            // number of clusters

    public static void main(String[] args) {
        Instances dataset = load(DATA);
        HierarchicalClusterer hc = new HierarchicalClusterer();
        hc.setLinkType(new SelectedTag(4, TAGS_LINK_TYPE));  // CENTROID
        hc.setNumClusters(1);
        try {
            hc.buildClusterer(dataset);
            for (Instance instance : dataset) {
                System.out.printf("(%.0f,%.0f): %s%n", 
                        instance.value(0), instance.value(1), 
                        hc.clusterInstance(instance));
            }
            displayDendrogram(hc.graph());
        } catch (Exception e) {
            System.err.println(e);
        }
    }
    
    private static Instances load(double[][] data) {
        ArrayList<Attribute> attributes = new ArrayList<Attribute>();
        attributes.add(new Attribute("X"));
        attributes.add(new Attribute("Y"));
        Instances dataset = new Instances("Dataset", attributes, M);
        for (double[] datum : data) {
            Instance instance = new SparseInstance(2);
            instance.setValue(0, datum[0]);
            instance.setValue(1, datum[1]);
            dataset.add(instance);
        }
        return dataset;
    }
    
    public static void displayDendrogram(String graph) {
        JFrame frame = new JFrame("Dendrogram");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());
        pane.add(new HierarchyVisualizer(graph));
        frame.setVisible(true);
    }
}
