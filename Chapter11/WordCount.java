/*  Data Analysis with Java
 *  John R. Hubbard
 *  Aug 8, 2017
 */

package ch11;

public class WordCount {
    
    public static class WordCountMapper extends Mapper {
        public void map(Object key, Text value, Context context) {
            
        }
    }
    
    public static class WordCountReducer extends Reducer {
        public void reduce(Text key, Iterable values, Context context) {
            
        }
    }
    
    public static void main(String[] args) {
        
    }
}



class Mapper {}
class Text {}
class Context {}
class Reducer {}

