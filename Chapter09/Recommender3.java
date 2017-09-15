/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 22, 2017
 */

package dawj.ch09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Recommender3 {
    private static int m;            //  number of users
    private static int n;            //  number of items
    private static double[][] u;     //  utility matrix
    private static double[][] s;     //  similarity matrix
    private static int user;         //  the current user
    private static Item itemBought;  //  the current item bought by user

    public static void main(String[] args) {
        readFiles();
        getInput();
        Set<Item> set1 = itemsNotYetBought();
        Set<Item> set2 = firstPartOf(set1, n/3);
        makeRecommendations(set2, n/4);
    }

    public static void readFiles() {
        File utilityFile = new File("data/Utility3.dat");
        File similarityFile = new File("data/Similarity3.dat");
        try {
            readUtilMatrix(utilityFile);
            readSimilMatrix(similarityFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    public static void readUtilMatrix(File f) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        m = in.nextInt();  in.nextLine();
        n = in.nextInt();  in.nextLine();
        u = new double[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                u[i][j] = in.nextDouble();
            }
            in.nextLine();
        }
        in.close();
    }
    
    public static void readSimilMatrix(File f) throws FileNotFoundException {
        Scanner in = new Scanner(f);
        n = in.nextInt();    
        in.nextLine();
        s = new double[n+1][n+1];
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                s[j][k] = in.nextDouble();
            }
            in.nextLine();
        }
        in.close();
    }

    public static void getInput() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter user number: ");
        user = input.nextInt();
        System.out.print("Enter item number: ");
        int item = input.nextInt();
        if (u[user][item] > 0) {
            System.out.printf("User %d already has item %d.%n", user, item);
            System.exit(0);
        }
        System.out.print("Enter rating (1-5): ");
        double rating = input.nextDouble();
        System.out.printf("User %d rated item %d at %4.2f.%n", user, item, rating);
        u[user][item] = rating;
        itemBought = new Item(item);
    }
        
    private static Set<Item> itemsNotYetBought() {
        Set<Item> set = new TreeSet(itemBought.new SimilarityComparator());
        for (int j = 1; j <= n; j++) {
            if (u[user][j] == 0) {  // user has not yet bought item j
                set.add(new Item(j));
            }
        }
        return set;
    }
        
    private static Set<Item> firstPartOf(Set<Item> set1, int n1) {
        Set<Item> set2 = new TreeSet(itemBought.new PopularityComparator());
        int count = 0;
        for (Item item : set1) {
            set2.add(item);
            if (++count == n1) {
                break;
            }
        }
        return set2;
    }

    private static void makeRecommendations(Set<Item> set, int n2) {
        System.out.printf("We also recommend these %d items:", n2);
        int count = 0;
        for (Item item : set) {
            System.out.printf("  %d", item.index);
            if (++count == n2) {
                break;
            }
        }
        System.out.println();
    }

    static class Item {
        int index;
        
        public Item(int index) {
            this.index = index;
        }
        
        public double popularity() {
            double sum = 0.0;
            int count = 0;
            for (int i = 1; i <= m; i++) {
                double value = u[i][this.index];
                if (value > 0) {
                    sum += value;
                    ++count;
                }
            }
            return (count > 0 ? sum/count : 0.0);
        }
        
        public double similarity(Item item) {
            return s[this.index][item.index];
        }

        public class PopularityComparator implements Comparator<Item> {
            @Override
            public int compare(Item item1, Item item2) {
                double p1 = item1.popularity();
                double p2 = item2.popularity();
                return (p1 > p2 ? -1 : 1);
            }
        }
        
        public class SimilarityComparator implements Comparator<Item> {
            @Override
            public int compare(Item item1, Item item2) {
                double s1 = Item.this.similarity(item1);
                double s2 = Item.this.similarity(item2);
                return (s1 > s2 ? -1 : 1);
            }
        }
        
        @Override
        public String toString() {
            return String.format("%d", index);
        }
    }
}

/*
run:
Enter user number: 1
Enter item number: 1
Enter rating (1-5): 2.5
User 1 rated item 1 at 2.50.
We also recommend these 3 items:  5  8  3
*/
