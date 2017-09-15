/*  Data Analysis with Java
 *  John R. Hubbard
 *  Jul 30, 2017
 */

package dawj.ch10;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Updates;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import org.bson.Document;

public class AddAuthorsToBooks {
    private static final File AUTHORS_BOOKS = new File("data/AuthorsBooks.dat");

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase library = client.getDatabase("library");
        MongoCollection books = library.getCollection("books");
        
        try {
            Scanner scanner = new Scanner(AUTHORS_BOOKS);
            int n = 0;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line).useDelimiter("/");
                String author_id = lineScanner.next();
                String book_id = lineScanner.next();
                lineScanner.close();
                
//                Document doc = new Document().append("author_id", author_id);
                Document doc = new Document("author_id", author_id);
                books.updateOne(
                        eq("_id", book_id), 
                        Updates.addToSet("author", doc));
//                System.out.printf("%4d. %s, %s%n", ++n, _id, author);
            }
            scanner.close();
        } catch (IOException e) {
            System.err.println(e);
        }        
    }
}
