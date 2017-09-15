/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 23, 2017
 */

package dawj.ch09;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTester {
    private static final int W = Double.BYTES;  // 8
    
    public static void main(String[] args) {
        String filespec = "data/Similarity4.dat";
        try {
            RandomAccessFile inout = new RandomAccessFile(filespec, "rw");
            for (int i = 0; i < 100; i++) {
                inout.writeDouble(Math.sqrt(i));
            }
            System.out.printf("Current file length is %d.%n", inout.length());
            
            for (int i = 4; i < 10; i++) {
                inout.seek(i*W);
                double x = inout.readDouble();
                System.out.printf("The square root of %1d is %.8f.%n", i, x);
            }
            System.out.println();
            
            inout.seek(7*W);
            inout.writeDouble(9.99999);
            
            for (int i = 4; i < 10; i++) {
                inout.seek(i*W);
                double x = inout.readDouble();
                System.out.printf("The square root of %1d is %.8f.%n", i, x);
            }
            inout.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}

/*
run:
Current file length is 800.
The square root of 4 is 2.00000000.
The square root of 5 is 2.23606798.
The square root of 6 is 2.44948974.
The square root of 7 is 2.64575131.
The square root of 8 is 2.82842712.
The square root of 9 is 3.00000000.

The square root of 4 is 2.00000000.
The square root of 5 is 2.23606798.
The square root of 6 is 2.44948974.
The square root of 7 is 9.99999000.
The square root of 8 is 2.82842712.
The square root of 9 is 3.00000000.
*/
