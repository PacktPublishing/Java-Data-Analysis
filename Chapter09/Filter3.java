/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 22, 2017
 */

package dawj.ch09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Filter3 {
    private static int m;  //  number of users
    private static int n;  //  number of items

    public static void main(String[] args) {
        File purchasesFile = new File("data/Purchases3.dat");
        File utilityFile = new File("data/Utility3.dat");
        File similarityFile = new File("data/Similarity3.dat");
        try {
            double[][] u = computeUtilityMatrix(purchasesFile);
            storeUtilityMatrix(u, utilityFile);
            double[][] s = computeSimilarityMatrix(u);
            storeSimilarityMatrix(s, similarityFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }
    
    public static double[][] computeUtilityMatrix(File file) 
            throws FileNotFoundException {
        Scanner in = new Scanner(file);
        //  Read the five header lines:
        m = in.nextInt();  in.nextLine();
        n = in.nextInt();  in.nextLine();
        in.nextLine();  in.nextLine();  in.nextLine();

        //  Read in the utility matrix:
        double[][] u = new double[m+1][n+1];
        while (in.hasNext()) {
            int i = in.nextInt();       // user
            int j = in.nextInt();       // item
            u[i][j] = in.nextDouble();  // rating
        }
        in.close();
        return u;
    }

    public static void storeUtilityMatrix(double[][] u, File file)
            throws FileNotFoundException {
        PrintWriter out = new PrintWriter(file);
        out.printf("%d users%n", m);
        out.printf("%d items%n", n);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                out.printf("%5.1f", u[i][j]);
            }
            out.println();
        }
        out.close();
    }
    
    public static double[][] computeSimilarityMatrix(double[][] u) {
        double[][] s = new double[n+1][n+1];
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                s[j][k] = cosine(u, j, k);
            }
        }
        return s;
    }

    public static void storeSimilarityMatrix(double[][] s, File file)
            throws FileNotFoundException {
        PrintWriter out = new PrintWriter(file);
        out.printf("%d items%n", n);
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                out.printf("%6.2f", s[i][j]);
            }
            out.println();
        }
        out.close();
    }

    /*  Returns the cosine similarity of the jth and kth columns of u[][].
     */
    public static double cosine(double[][] u, int j, int k) {
        double denominator = norm(u,j)*norm(u,k);
        return (denominator == 0 ? 0 : dot(u,j,k)/denominator);
    }
    
    /*  Returns the dot product of the jth and kth columns of u[][].
     */
    public static double dot(double[][] u, int j, int k) {
        double sum = 0.0;
        for (int i = 0; i <= m; i++) {
            sum += u[i][j]*u[i][k];
        }
        return sum;
    }
    
    /*  Returns the norm of the jth column of u[][].
     */
    public static double norm(double[][] u, int j) {
        return Math.sqrt(dot(u,j,j));
    }
}
