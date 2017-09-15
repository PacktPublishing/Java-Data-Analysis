/*  Data Analysis with Java
 *  John R. Hubbard
 *  June 6, 2017
 */

package dawj.ch08;

import java.util.HashSet;

public class Cluster {
    private final HashSet<Point> points;
    private Point centroid;

    public Cluster(HashSet points, Point centroid) {
        this.points = points;
        this.centroid = centroid;
    }
    
    public Cluster(Point point) {
        this.points = new HashSet();
        this.points.add(point);
        this.centroid = point;
    }

    public Cluster(double x, double y) {
        this(new Point(x,y));
    }

    public Point getCentroid() {
        return centroid;
    }

    public void add(Point point) {
        points.add(point);
        recomputeCentroid();
    }

    public void recomputeCentroid() {
        double xSum=0.0, ySum=0.0;
        for (Point point : points) {
            xSum += point.getX();
            ySum += point.getY();
        }
        centroid = new Point(xSum/points.size(), ySum/points.size());
    }
    
    public static double distance(Cluster c1, Cluster c2) {
        double dx = c1.centroid.getX() - c2.centroid.getX();
        double dy = c1.centroid.getY() - c2.centroid.getY();
        return Math.sqrt(dx*dx + dy*dy);
    }
    
    public static Cluster union(Cluster c1, Cluster c2) {
        Cluster cluster = new Cluster(c1.points, c1.centroid);
        cluster.points.addAll(c2.points);
        cluster.recomputeCentroid();
        return cluster;
    }

    @Override
    public int hashCode() {
        return points.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        } else if (object == this) {
            return true;
        } else if (!(object instanceof Cluster)) {
            return false;
        }
        final Cluster that = (Cluster)object;
        return that.points.equals(this.points);
    }

    @Override
    public String toString() {
        return String.format("%n{%s,%s}", centroid, points);
    }
}
