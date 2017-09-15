/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 22, 2017
 */

package dawj.ch09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Random;

public class DataGenerator3 {
    static final Random RANDOM = new Random();
    static final int NUM_USERS = 5;
    static final int NUM_ITEMS = 12;
    static final int MAX_RATING = 5;
    static final int NUM_PURCHASES = 36;
    static final double MU = 3.0;     //  average rating
    static final double SIGMA = 1.0;  //  standard deviation
    
    public static void main(String[] args) {
        HashSet<Purchase> purchases = new HashSet(NUM_PURCHASES);
        while (purchases.size() < NUM_PURCHASES) {
            purchases.add(new Purchase());
        }

        File outFile = new File("data/Purchases3.dat");
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
        double rating;

        public Purchase() {
            this.user = RANDOM.nextInt(NUM_USERS) + 1;
            this.item = RANDOM.nextInt(NUM_ITEMS) + 1;
            this.rating = randomRating();
        }

        public double randomRating() {
            double x =  MU + SIGMA*RANDOM.nextGaussian();
            x = Math.max(1, x);           //  x >= 1.0
            x = Math.min(MAX_RATING, x);  //  x <= 5.0
            return Math.floor(2*x)/2;     // 0.5, 1.0, 1.5, 2.0, . . .
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
            return String.format("%4d%4d%5.1f", user, item, rating);
        }
    }
}
