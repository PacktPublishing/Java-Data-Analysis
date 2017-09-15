/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 22, 2017
 */

package dawj.ch07;

/*  http://slidewiki.org/deck/1218_id3-example#tree-1218-slide-17752-5-view
*/
public class ComputeGain {
    public static void main(String[] args) {
        System.out.printf("h(11,16) = %.4f%n", h(11,16));
        System.out.println("Gain(Size):");
        System.out.printf("\th(3,5) = %.4f%n", h(3,5));
        System.out.printf("\th(6,7) = %.4f%n", h(6,7));
        System.out.printf("\th(2,4) = %.4f%n", h(2,4));
        System.out.printf("\tg({3,6,2},{5,7,4}) = %.4f%n", 
                    g(new int[]{3,6,2},new int[]{5,7,4}));
        System.out.println("Gain(Color):");
        System.out.printf("\th(3,4) = %.4f%n", h(3,4));
        System.out.printf("\th(3,5) = %.4f%n", h(3,5));
        System.out.printf("\th(2,3) = %.4f%n", h(2,3));
        System.out.printf("\th(2,4) = %.4f%n", h(2,4));
        System.out.printf("\tg({3,3,2,2},{4,5,3,4}) = %.4f%n", 
                    g(new int[]{3,3,2,2},new int[]{4,5,3,4}));
        System.out.println("Gain(Surface):");
        System.out.printf("\th(4,7) = %.4f%n", h(4,7));
        System.out.printf("\th(4,6) = %.4f%n", h(4,6));
        System.out.printf("\th(3,3) = %.4f%n", h(3,3));
        System.out.printf("\tg({4,4,3},{7,6,3}) = %.4f%n", 
                    g(new int[]{4,4,3},new int[]{7,6,3}));
        System.out.println("Gain(Size|SMOOTH):");
        System.out.printf("\th(1,3) = %.4f%n", h(1,3));
        System.out.printf("\th(3,3) = %.4f%n", h(3,3));
        System.out.printf("\tg({1,3,0},{3,3,1}) = %.4f%n", 
                    g(new int[]{1,3,0},new int[]{3,3,1}));
        System.out.println("Gain(Color|SMOOTH):");
        System.out.printf("\th(2,3) = %.4f%n", h(2,3));
        System.out.printf("\tg({2,2,0},{3,2,2}) = %.4f%n", 
                    g(new int[]{2,2,0},new int[]{3,2,2}));
        System.out.println("Gain(Size|ROUGH):");
        System.out.printf("\th(3,6) = %.4f%n", h(3,6));
        System.out.printf("\th(1,2) = %.4f%n", h(1,2));
        System.out.printf("\tg({2,1,1},{2,2,2}) = %.4f%n", 
                    g(new int[]{2,1,1},new int[]{2,2,2}));
        System.out.println("Gain(Color|ROUGH):");
        System.out.printf("\th(4,6) = %.4f%n", h(4,6));
        System.out.printf("\tg({1,1,1},{2,2,2}) = %.4f%n", 
                    g(new int[]{1,0,2,1},new int[]{1,2,2,1}));
    }
    
    /*  Gain for the splitting {A1, A2, ...}, where Ai 
        has n[i] points, m[i] of which are favorable.
    */
    public static double g(int[] m, int[] n) {
        int sm = 0, sn = 0;
        double nsh = 0.0;
        for (int i = 0; i < m.length; i++) {
            sm += m[i];
            sn += n[i];
            nsh += n[i]*h(m[i],n[i]);
        }
        return h(sm, sn) - nsh/sn;
    }
    
    /*  Entropy for m favorable items out of n.
    */
    public static double h(int m, int n) {
        if (m == 0 || m == n) {
            return 0;
        }
        double p = (double)m/n, q = 1 - p;
        return -p*lg(p) - q*lg(q);
    }
    
    /*  Returns the binary logarithm of x.
    */
    public static double lg(double x) {
        return Math.log(x)/Math.log(2);
    }
}

/*
h(11,16) = 0.8960
Gain(Size):
	h(3,5) = 0.9710
	h(6,7) = 0.5917
	h(2,4) = 1.0000
	g({3,6,2},{5,7,4}) = 0.0838
Gain(Color):
	h(3,4) = 0.8113
	h(3,5) = 0.9710
	h(2,3) = 0.9183
	h(2,4) = 1.0000
	g({3,3,2,2},{4,5,3,4}) = 0.0260
Gain(Surface):
	h(4,7) = 0.9852
	h(4,6) = 0.9183
	h(3,3) = 0.0000
	g({4,4,3},{7,6,3}) = 0.1206
Gain(Size|SMOOTH):
	h(1,3) = 0.9183
	h(3,3) = 0.0000
	g({1,3,0},{3,3,1}) = 0.5917
Gain(Color|SMOOTH):
	h(2,3) = 0.9183
	g({2,2,0},{3,2,2}) = 0.5917
Gain(Size|ROUGH):
	h(3,6) = 1.0000
	h(1,2) = 1.0000
	g({2,1,1},{2,2,2}) = 0.2516
Gain(Color|ROUGH):
	h(4,6) = 0.9183
	g({1,1,1},{2,2,2}) = 0.9183
*/
