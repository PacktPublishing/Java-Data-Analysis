/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 27, 2017
 */

package dawj.ch07;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class TestDataSource {
    public static void main(String[] args) throws Exception {
        DataSource source = new DataSource("data/fruit.arff");
        
        Instances instances = source.getDataSet();
        instances.setClassIndex(instances.numAttributes() - 1);
        System.out.println(instances.attribute(2));
        
        Instance instance = instances.get(3);
        System.out.println(instance);
        System.out.println(instance.stringValue(0));
        System.out.println(instance.stringValue(2));
    }
}

