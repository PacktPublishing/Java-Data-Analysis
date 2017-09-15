/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 28, 2017
 */

package dawj.ch07;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Fruit {
    String name, size, color, surface;
    boolean sweet;

    public Fruit(String name, String size, String color, String surface, 
            boolean sweet) {
        this.name = name;
        this.size = size;
        this.color = color;
        this.surface = surface;
        this.sweet = sweet;
    }

    @Override
    public String toString() {
        return String.format("%-12s%-8s%-8s%-8s%s", 
                name, size, color, surface, (sweet? "T": "F") );
    }
    
    public static Set<Fruit> loadData(File file) {
        Set<Fruit> fruits = new HashSet();
        try {
            Scanner input = new Scanner(file);
            for (int i = 0; i < 7; i++) {  // read past metadata
                input.nextLine();
            }
            while (input.hasNextLine()) {
                String line = input.nextLine();
                Scanner lineScanner = new Scanner(line);
                String name = lineScanner.next();
                String size = lineScanner.next();
                String color = lineScanner.next();
                String surface = lineScanner.next();
                boolean sweet = (lineScanner.next().equals("T"));
                Fruit fruit = new Fruit(name, size, color, surface, sweet);
                fruits.add(fruit);
            }
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
        return fruits;
    }

    public static void print(Set<Fruit> fruits) {
        int k=1;
        for (Fruit fruit : fruits) {
            System.out.printf("%2d. %s%n", k++, fruit);
        }
    }
}

