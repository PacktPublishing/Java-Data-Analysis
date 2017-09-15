/*  Data Analysis with Java  (Lis. 5-13)
 *  John R. Hubbard
 *  May 4, 2017
 */

package dawj.ch05;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

/*  Loads the data from the Authors.dat file into the Authors table. 
 */
public class AddAuthors {
    private static final String URL = "jdbc:derby://localhost:1527/Library";
    private static final String USR = "jhubbard";  // USE YOUR USERNAME HERE
    private static final String PWD = "dawj";      // USE YOUR PASSWORD HERE

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(URL, USR, PWD);
            File file = new File("data/Authors.dat");
            String sql = "insert into Authors values(?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            Scanner fileScanner = new Scanner(file);
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter(",");
                String id = lineScanner.next();
                String lastName = lineScanner.next();
                String firstName = lineScanner.next();
                int yob = lineScanner.nextInt();
                ps.setString(1, id);
                ps.setString(2, lastName);
                ps.setString(3, firstName);
                ps.setInt(4, yob);
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d rows inserted in Authors table.%n", rows);
            fileScanner.close();
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}

