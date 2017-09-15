/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 23, 2017
 */

package dawj.ch09;

import java.util.Map;
import java.util.TreeMap;

public class SparseMatrix {
    private final int m, n;  // dimensions of matrix
    private final Map<Key,Double> map;

    public SparseMatrix(int m, int n) {
        this.m = m;
        this.n = n;
        this.map = new TreeMap();
    }
    
    public void put(int i, int j, double x) {
        map.put(new Key(i,j), x);
    }
    
    public double get(int i, int j) {
        return map.get(new Key(i,j));
    }
    
    public class Key implements Comparable {
        int i, j;

        public Key(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return i*n + j;
        }

        @Override
        public boolean equals(Object object) {
            if (object == null) {
                return false;
            } else if (object == this) {
                return true;
            } else if (!(object instanceof Key)) {
                return false;
            }
            Key that = (Key)object;
            return that.i == this.i && that.j == this.j;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", i, j);
        }

        @Override
        public int compareTo(Object object) {
            if (object == null) {
                return -1;
            } else if (object == this) {
                return 0;
            } else if (!(object instanceof Key)) {
                return -1;
            }
            Key that = (Key)object;
            return this.hashCode() - that.hashCode();
        }
    }
}
