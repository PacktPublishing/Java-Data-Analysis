/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 12, 2017
 */

package dawj.ch09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;

public class DataGenerator1 {
    static final Random RANDOM = new Random();
    static final int NUM_USERS = 5;
    static final int NUM_ITEMS = 12;
    static final int NUM_PURCHASES = 36;
    
    public static void main(String[] args) {
        HashSet<Purchase> purchases = new HashSet(NUM_PURCHASES);
        while (purchases.size() < NUM_PURCHASES) {
            purchases.add(new Purchase());
        }

        File outFile = new File("data/Purchases1.dat");
        try {
            PrintWriter out = new PrintWriter(outFile);
            out.printf("%d users%n", NUM_USERS);
            out.printf("%d items%n", NUM_ITEMS);
            out.printf("%d purchases%n", NUM_PURCHASES);
            for (Purchase purchase : purchases) {
                out.println(purchase);
                System.out.println(purchase);
            }
            out.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    static class Purchase {
        int user;
        int item;

        public Purchase() {
            this.user = RANDOM.nextInt(NUM_USERS) + 1;
            this.item = RANDOM.nextInt(NUM_ITEMS) + 1;
        }

        @Override
        public int hashCode() {
            return NUM_ITEMS*this.user + NUM_USERS*this.item;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            } else if (object == this) {
                return true;
            } else if (!(object instanceof Purchase)) {
                return false;
            }
            Purchase that = (Purchase)object;
            return that.user == this.user && that.item == this.item;
        }

        @Override
        public String toString() {
            return String.format("%4d%4d", user, item);
        }
    }
}
