/*  Data Analysis with Java  (Lis. 5-14)
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
import java.sql.Types;
import java.util.Scanner;

/*  Loads the data from the Publishers.dat file into the Publishers table. 
 */
public class LoadPublishers {
    private static final String URL = "jdbc:derby://localhost:1527/Library";
    private static final String USR = "jhubbard";  // USE YOUR USERNAME HERE
    private static final String PWD = "dawj";      // USE YOUR PASSWORD HERE
    private static final File DATA = new File("data/Publishers.dat");
    private static final String SQL = 
            "insert into Publishers values(?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA)){
            conn.createStatement().execute("delete from AuthorsBooks");
            conn.createStatement().execute("delete from Books");
            conn.createStatement().execute("delete from Publishers");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String id = lineScanner.next();
                String name = lineScanner.next();
                String city = lineScanner.next();
                String country = lineScanner.next();
                String url = (lineScanner.hasNext() ? lineScanner.next() : "");
                ps.setString(1, id);
                ps.setString(2, name);
                ps.setString(3, city);
                ps.setString(4, country);
                if (url.length() > 0) {
                    ps.setString(5, url);
                } else {
                    ps.setNull(5, Types.VARCHAR);
                }
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d rows inserted in Publishers table.%n", rows);
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
