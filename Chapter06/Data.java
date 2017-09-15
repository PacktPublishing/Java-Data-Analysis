/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 11, 2017
 */

package dawj.ch06;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Data {
    private String title,xName, yName;
    private int n;
    private double[] x, y;
    private double sX, sXX, sY, sYY, sXY, minX, minY, maxX, maxY;
    private double meanX, meanY, slope, intercept, corrCoef;

    public Data(File inputFile) {
        try {
            Scanner input = new Scanner(inputFile);
            title = input.nextLine();
            n = input.nextInt();
            xName = input.next();
            yName = input.next();
            input.nextLine();
            x = new double[n];
            y = new double[n];
            minX = minY = Double.POSITIVE_INFINITY;
            maxX = maxY = Double.NEGATIVE_INFINITY;
            for (int i = 0; i < n; i++) {
                double xi = x[i] = input.nextDouble();
                double yi = y[i] = input.nextDouble();
                sX += xi;
                sXX += xi*xi;
                sY += yi;
                sYY += yi*yi;
                sXY += xi*yi;
                minX = (xi < minX? xi: minX);
                minY = (yi < minY? yi: minY);
                maxX = (xi > maxX? xi: maxX);
                maxY = (yi > maxY? yi: maxY);
            }
            meanX = sX/n;
            meanY = sY/n;
            slope = (n*sXY - sX*sY)/(n*sXX - sX*sX);
            intercept = meanY - slope*meanX;
            corrCoef = slope*Math.sqrt((n*sXX - sX*sX)/(n*sYY - sY*sY));
        } catch (FileNotFoundException e) {
            System.err.println(e);
        }
    }

    public String getTitle() {
        return title;
    }

    public String getXName() {
        return xName;
    }

    public String getYName() {
        return yName;
    }

    public int getN() {
        return n;
    }

    public double[] getX() {
        return x;
    }

    public double[] getY() {
        return y;
    }

    public double getMeanX() {
        return meanX;
    }

    public double getMeanY() {
        return meanY;
    }

    public double getSlope() {
        return slope;
    }

    public double getIntercept() {
        return intercept;
    }

    public double getCorrCoef() {
        return corrCoef;
    }
    
    public double[][] getTable() {
        double[][] table = new double[n][2];
        for (int i = 0; i < n; i++) {
            table[i][0] = x[i];
            table[i][1] = y[i];
        }
        return table;
    }

    public double getMinX() {
        return minX;
    }

    public double getMinY() {
        return minY;
    }

    public double getMaxX() {
        return maxX;
    }

    public double getMaxY() {
        return maxY;
    }
}
