/*  Data Analysis with Java
 *  John R. Hubbard
 *  May 13, 2017
 */

package dawj.ch06;

import java.io.File;
import javax.swing.JFrame;

public class Example3 {
    public static void main(String[] args) {
        Data data = new Data(new File("data/Data1.dat"));
        JFrame frame = new JFrame(data.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RegressionPanel panel = new RegressionPanel(data);
        frame.add(panel);
        frame.pack();
        frame.setSize(500, 422);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);  // center frame on screen
        frame.setVisible(true);
    }
}
