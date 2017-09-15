/*  Data Analysis with Java
 *  John R. Hubbard
 *  Aug 4, 2017
 */

package dawj.ch11;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Example1 {
    public static void main(String[] args) {
        try {
            File tempFile = new File("/data/Temp.dat");
            map("data/sonnets/", 80, tempFile);

            Map<String,StringBuilder> hashTable = new HashMap(2500);
            combine(tempFile, hashTable);

            File outFile = new File("/data/Output.dat");
            reduce(hashTable, outFile);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void map(String src, int n, File temp) throws IOException {
        PrintWriter writer = new PrintWriter(temp);
        for (int i = 0; i < n; i++) {
            String filename = String.format("%sSonnet%03d.txt", src, i+1);
            map(filename, writer);
        }
        writer.close();
    }

    public static void combine(File temp, Map<String,StringBuilder> table) 
            throws IOException {
        Scanner scanner = new Scanner(temp);
        while (scanner.hasNext()) {
            String word = scanner.next();
            StringBuilder value = table.get(word);
            if (value == null) {
                value = new StringBuilder("");
            }
            table.put(word, value.append(" 1"));
            scanner.nextLine();  // scan past the rest of the line (a "1")
        }
        scanner.close();
    }
        
    public static void reduce(Map<String,StringBuilder> table, File out) 
            throws IOException {
        PrintWriter writer = new PrintWriter(out);
        for (Map.Entry<String, StringBuilder> entry : table.entrySet()) {
            String key = entry.getKey();  // e.g., "speak"
            String value = entry.getValue().toString();  // e.g., "1 1 1 1 1"
            reduce(key, value, writer);
        }
        writer.close();
    }

    
    /*  Writes the pair (word, 1) for each word in the specified file.
    */
    public static void map(String filename, PrintWriter writer) 
            throws IOException {
        Scanner input = new Scanner(new File(filename));
        input.useDelimiter("[.,:;()?!\"\\s]+");
        while (input.hasNext()) {
            String word = input.next();
            writer.printf("%s 1%n", word.toLowerCase());
        }
        input.close();
    }

    /*  Counts the 1s in the value argument and writes (key, count) to file.
    */
    public static void reduce(String key, String value, PrintWriter writer)
            throws IOException {
        int count = (value.length() + 1)/2;  // e.g. "1 1 1 1 1" => 5
        writer.printf("%s %d%n", key, count);
    }
    
    private static void sort(File file) throws IOException {
        Scanner input = new Scanner(file);
        List<String> list = new ArrayList();
        while (input.hasNext()) {
            list.add(input.nextLine());
        }
        input.close();
        Collections.sort(list);
        PrintWriter output = new PrintWriter(file);
        for (String string : list) {
            output.println(string);
        }
        output.close();
    }
}
