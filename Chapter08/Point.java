/*  Data Analysis with Java
 *  John R. Hubbard
 *  June 6, 2017
 */

package dawj.ch08;

public class Point {
    private final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public int hashCode() {
        int xhC = new Double(x).hashCode();
        int yhC = new Double(y).hashCode();
        return (int)(xhC + 79*yhC);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (object == this) {
            return true;
        } else if (!(object instanceof Point)) {
            return false;
        }
        Point that = (Point)object;
        return bits(that.x) == bits(this.x) && bits(that.y) == bits(this.y);
    }
    
    private long bits(double d) {
        return Double.doubleToLongBits(d);

    }

    @Override
    public String toString() {
        return String.format("(%.2f,%.2f)", x,y);
    }
}
