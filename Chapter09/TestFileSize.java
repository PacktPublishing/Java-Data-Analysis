/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 23, 2017
 */

package dawj.ch09;

import java.io.IOException;
import java.io.RandomAccessFile;

public class TestFileSize {
    private static final int W = Double.BYTES;  // 8
    private static final int N = 1000;  //  1,000
    
    public static void main(String[] args) {
        String filespec = "data/TestRAF.dat";
        try {
            RandomAccessFile file = new RandomAccessFile(filespec, "rw");
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    file.writeDouble(Math.random());
                }
            }
            file.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
