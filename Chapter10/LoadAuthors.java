/*  Data Analysis with Java
 *  John R. Hubbard
 *  July 28, 2017
 */

package dawj.ch10;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.bson.Document;

/*  Loads the data from the Authors.dat file into the Authors collection. 
 */
public class LoadAuthors {
    private static final File DATA = new File("data/Authors.dat");

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase library = client.getDatabase("library");
        MongoCollection authors = library.getCollection("authors");
        
        authors.drop();
        library.createCollection("authors");
        load(authors);
    }
    
    public static void load(MongoCollection collection) {
        try {
            Scanner fileScanner = new Scanner(DATA);
            int n = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String _id = lineScanner.next();
                String lname = lineScanner.next();
                String fname = lineScanner.next();
                int yob = lineScanner.nextInt();
                lineScanner.close();
                
                addDoc(_id, lname, fname, yob, collection);
                System.out.printf("%4d. %s, %s, %s, %d%n", 
                        ++n, _id, lname, fname, yob);
            }
            System.out.printf("%d docs inserted in authors collection.%n", n);
            fileScanner.close();
        } catch (IOException e) {
            System.err.println(e);
        }        
    }
    
    public static void addDoc(String _id, String lname, String fname, int yob, 
            MongoCollection collection) {
        Document doc = new Document();
        doc.put("_id", _id);
        doc.put("lname", lname);
        doc.put("fname", fname);
        doc.put("yob", yob);
        collection.insertOne(doc);
    }
}
