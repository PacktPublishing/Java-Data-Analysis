/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 24, 2017
 */

package dawj.ch10;

import java.util.HashMap;
import java.util.Map;

public class Example1 {
    public static void main(String[] args) {
        Map<Integer,Employee> map = new HashMap();
        
        Employee rose = new Employee("Davis", "Rose", "1983-05-12", 
                "IT Manager", "rdavis@xyz.com");
        map.put(23098, rose);
    }
    
    static class Employee {
        String lastName, firstName, dob, title, email;

        public Employee(String lastName, String firstName, String dob, 
                String title, String email) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.dob = dob;
            this.title = title;
            this.email = email;
        }
    }
}
