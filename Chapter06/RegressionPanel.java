/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 11, 2017
 */

package dawj.ch06;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class RegressionPanel extends JPanel {
    private static final int WIDTH=500, HEIGHT=400, BUFFER=28, MARGIN=40;
    private final Data data;
    private double xMin, xMax, yMin, yMax, xRange, yRange, gWidth, gHeight;
    private double slope, intercept;

    public RegressionPanel(Data data) {
        this.data = data;
        this.setSize(WIDTH, HEIGHT);
        this.xMin = data.getMinX();
        this.xMax = data.getMaxX();
        this.yMin = data.getMinY();
        this.yMax = data.getMaxY();
        this.slope = data.getSlope();
        this.intercept = data.getIntercept();
        this.xRange = xMax - xMin;
        this.yRange = yMax - yMin;
        this.gWidth = WIDTH - 2*MARGIN - BUFFER;
        this.gHeight = HEIGHT - 2*MARGIN - BUFFER;
        setBackground(Color.WHITE);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(1));
        drawGrid(g2);
        drawPoints(g2, data.getX(), data.getY());
        drawLine(g2);
    }

    private void drawGrid(Graphics2D g2) {
        g2.setStroke(new BasicStroke(1));
        double xGd = Math.pow(10, Math.floor(Math.log10(xRange)));
        int xd = dToI(xGd);
        int x0 = dToI(xGd*Math.floor(xMin/xGd));
        int xn = dToI(xGd*Math.ceil(xMax/xGd));
        for (int xi = x0; xi <= xn; xi += xd) {
            g2.setColor(Color.LIGHT_GRAY);
            int p = f(xi);
            g2.drawLine(p, 0, p, HEIGHT-18);  // vertical lines
            g2.setColor(Color.BLACK);
            g2.drawString(""+xi, p-8, HEIGHT-4);
        }
        double yGd = Math.pow(10, Math.floor(Math.log10(yRange)));
        int yd = dToI(yGd);
        int y0 = dToI(xGd*Math.floor(xMin/yGd));
        int yn = dToI(xGd*Math.ceil(yMax/yGd));
        for (int yi = y0; yi <= yn; yi += yd) {
            g2.setColor(Color.LIGHT_GRAY);
            int q = g(yi);
            g2.drawLine(BUFFER, q, WIDTH, q);  // horizontal lines
            g2.setColor(Color.LIGHT_GRAY);
            g2.setColor(Color.BLACK);
            g2.drawString((yi<100?"  ":"")+yi, 2, q+5);
        }
    }
    
    private void drawPoints(Graphics2D g2, double[] x, double[] y) {
        g2.setColor(Color.BLACK);
        for (int i = 0; i < x.length; i++) {
            int u = f(x[i]);
            int v = g(y[i]);
            g2.fillOval(u-3, v-3, 6, 6);  // coordinates are at NW corners
        }
    }
    
    private void drawLine(Graphics2D g2) {
        g2.setColor(Color.BLUE);
        g2.setStroke(new BasicStroke(2));
        int p0 = BUFFER;
        int q0 = g(yLine(fInv(p0)));
        int p1 = WIDTH;
        int q1 = g(yLine(fInv(p1)));
        g2.drawLine(p0, q0, p1, q1);
    }
    
    private double yLine(double x) {
        return slope*x + intercept;
    }
    
    private int dToI(double x) {
        return (int)Math.round(x);
    }
    
    private int f(double x) {
        return dToI((x - xMin)*gWidth/xRange) + BUFFER + MARGIN;
    }
    
    private int g(double y) {
        return dToI(gHeight - (y - yMin)*gHeight/yRange) + MARGIN;
    }
    
    private double fInv(int p) {
        return (p - BUFFER - MARGIN)*xRange/gWidth + xMin;
    }
    
    private double gInv(int q) {
        return yMin + (gHeight + MARGIN - q)*yRange/gHeight;
    }
}
