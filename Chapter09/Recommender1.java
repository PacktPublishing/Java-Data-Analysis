/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 12, 2017
 */

package dawj.ch09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Recommender1 {
    private static int m;         //  number of users
    private static int n;         //  number of items
    private static int[][] u;     // utility matrix
    private static double[][] s;  // similarity matrix
    private static int user;      // the current user
    private static int bought;    // the current item bought by user

    public static void main(String[] args) {
        readFiles();
        getInput();
        Set<Item> set = itemsNotYetBought();
        makeRecommendations(set, n/4);
    }

    public static void readFiles() {
        File utilityFile = new File("data/Utility1.dat");
        File similarityFile = new File("data/Similarity1.dat");
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
        u = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                u[i][j] = in.nextInt();
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
        bought = input.nextInt();
        System.out.printf("User %d bought item %d.%n", user, bought);
        u[user][bought] = 1;
    }
        
    private static Set<Item> itemsNotYetBought() {
        Set<Item> set = new TreeSet();
        for (int j = 1; j <= n; j++) {
            if (u[user][j] == 0) {  // user has not yet bought item j
                set.add(new Item(j));
            }
        }
        return set;
    }

    private static void makeRecommendations(Set<Item> set, int numRecs) {
        System.out.printf("We also recommend these %d items:", numRecs);
        int count = 0;
        for (Item item : set) {
            System.out.printf("  %d", item.index);
            if (++count == numRecs) {
                break;
            }
        }
        System.out.println();
    }

    static class Item implements Comparable<Item> {
        int index;
        
        public Item(int index) {
            this.index = index;
        }
        
        @Override
        public int compareTo(Item item) {
            double s1 = s[bought][this.index];
            double s2 = s[bought][item.index];
            return (s1 > s2 ? -1 : 1);
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
User 1 bought item 1.
We also recommend these 3 items:  2  12  6
run:
Enter user number: 2
Enter item number: 3
User 2 bought item 3.
We also recommend these 3 items:  11  9  8
*/
