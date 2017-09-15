/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 30, 2017
 */

package dawj.ch10;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.bson.Document;

public class LoadBooks {
    private static final File DATA = new File("data/Books.dat");

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase library = client.getDatabase("library");
        MongoCollection books = library.getCollection("books");
        
        books.drop();
        library.createCollection("books");
        load(books);
    }
    
    public static void load(MongoCollection collection) {
        try {
            Scanner fileScanner = new Scanner(DATA);
            int n = 0;
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String title = lineScanner.next();
                int edition = lineScanner.nextInt();
                String cover = lineScanner.next();
                String publisher = lineScanner.next();
                int year = lineScanner.nextInt();
                String _id = lineScanner.next();
                int pages = lineScanner.nextInt();
                lineScanner.close();
                
                addDoc(_id, title, edition, publisher, year, cover, pages, collection);
                System.out.printf("%4d. %s, %s, %s, %d%n", 
                        ++n, _id, title, publisher, year);
            }
            System.out.printf("%d docs inserted in books collection.%n", n);
            fileScanner.close();
        } catch (IOException e) {
            System.err.println(e);
        }        
    }
    
    public static void addDoc(String _id, String title, int edition, 
            String publisher, int year, String cover, int pages, 
            MongoCollection collection) {
        Document doc = new Document();
        doc.put("_id", _id);
        doc.put("title", title);
        doc.put("edition", edition);
        doc.put("publisher", publisher);
        doc.put("year", year);
        doc.put("cover", cover);
        doc.put("pages", pages);
        collection.insertOne(doc);
    }
}
