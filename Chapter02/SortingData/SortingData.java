/*  Data Analysis with Java
 *  John R. Hubbard
 *  March 30, 2017
 */

package dawj.ch02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class SortingData {
    public static void main(String[] args) {
        File file = new File("data/Countries.dat");
        TreeMap<Integer,String> dataset = new TreeMap();
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String x = input.next();
                int y = input.nextInt();
                dataset.put(y, x);
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        print(dataset);
    }
    
    public static void print(TreeMap<Integer,String> map) {
        for (Integer key : map.keySet()) {
            System.out.printf("%,12d  %-16s%n", key, map.get(key));
        }
    }
}
