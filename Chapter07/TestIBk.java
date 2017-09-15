/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jun 1, 2017
 */

package dawj.ch07;

import weka.classifiers.lazy.IBk;  // K-Nearest Neighbors
import weka.core.Instances;
import weka.core.Instance;
import weka.core.converters.ConverterUtils.DataSource;

public class TestIBk {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("data/AnonFruit.arff");
        Instances instances = source.getDataSet();
        instances.setClassIndex(3);  // target attribute: (Sweet)
        
        IBk ibk = new IBk();
        ibk.buildClassifier(instances);

        for (Instance instance : instances) {
            double prediction = ibk.classifyInstance(instance);
            System.out.printf("%4.0f%4.0f%n", 
                    instance.classValue(), prediction);
        }
    }
}

/*
run:
   1   1
   1   1
   1   1
   1   0
   1   1
   0   0
   1   1
   0   0
   0   0
   0   0
   1   1
   1   1
   1   1
   1   1
   0   0
   1   1
*/
