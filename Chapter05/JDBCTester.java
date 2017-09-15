/*  Data Analysis with Java  (Lis. 5-12)
 *  John R. Hubbard
 *  Apr 30, 2017
 */

package jdawj.ch05;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTester {
    private static final String URL = "jdbc:derby://localhost:1527/Library";
    private static final String USR = "jhubbard";  // USE YOUR USERNAME HERE
    private static final String PWD = "dawj";      // USE YOUR PASSWORD HERE

    public static void main(String[] args) {
        String sql = String.format("select name, city from Publishers");
        try(Connection conn = DriverManager.getConnection(URL, USR, PWD);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String pubName = rs.getString("name");
                String pubCity = rs.getString("city");
                System.out.printf("%s, %s%n", pubName, pubCity);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
