/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 11, 2017
 */

package dawj.ch03;

public class MovingAverageTester {
    static final double[] DATA = {20, 25, 21, 26, 28, 27, 29, 31};
    
    public static void main(String[] args) {
        TimeSeries<Double> series = new TimeSeries();
        for (double x : DATA) {
            series.add(System.currentTimeMillis(), x);
        }
        System.out.println(series.getList());

        TimeSeries<Double> ma3 = new MovingAverage(series, 3);
        System.out.println(ma3.getList());

        TimeSeries<Double> ma5 = new MovingAverage(series, 5);
        System.out.println(ma5.getList());
    }
}
