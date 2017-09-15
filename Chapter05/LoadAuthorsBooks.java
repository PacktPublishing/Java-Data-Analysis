/*  Data Analysis with Java
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

/*  Loads the data from the AuthorsBooks.dat file into the AuthorsBooks table. 
 */
public class LoadAuthorsBooks {
    private static final String URL = "jdbc:derby://localhost:1527/Library";
    private static final String USR = "jhubbard";  // USE YOUR USERNAME HERE
    private static final String PWD = "dawj";      // USE YOUR PASSWORD HERE
    private static final File DATA = new File("data/AuthorsBooks.dat");
    private static final String SQL = "insert into AuthorsBooks values(?, ?)";

    public static void main(String[] args) {
        try(Connection conn = DriverManager.getConnection(URL, USR, PWD);
            PreparedStatement ps = conn.prepareStatement(SQL);
            Scanner fileScanner = new Scanner(DATA)){
            conn.createStatement().execute("delete from AuthorsBooks");
            int rows = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String author = lineScanner.next();
                String book = lineScanner.next();
                ps.setString(1, author);
                ps.setString(2, book);
                rows += ps.executeUpdate();
                lineScanner.close();
            }
            System.out.printf("%d rows inserted in AuthorsBooks table.%n", rows);
            conn.close();
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }        
    }
}
