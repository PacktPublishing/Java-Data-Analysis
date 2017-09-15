/*  Data Analysis with Java
 *  John R. Hubbard
 *  Apr 11, 2017
 */

package dawj.ch03;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

public class TimeSeries<T> implements Iterable<TimeSeries.Entry> {
    private final Map<Long,T> map = new TreeMap();
    
    public void add(long time, T event) {
        map.put(time, event);
        try {
            TimeUnit.MICROSECONDS.sleep(1);  // 0.000001 sec delay
        } catch(InterruptedException e) {
            System.err.println(e);
        }
    }
    
    public T get(long time) {
        return map.get(time);
    }

    ArrayList getList() {
        ArrayList<TimeSeries.Entry> list = new ArrayList();
        for (TimeSeries.Entry entry : this) {
            list.add(entry);
        }
        return list;
    }
    
    public int size() {
        return map.size();
    }
    
    @Override
    public Iterator iterator() {
        return new Iterator() { // anonymous inner class
            private final Iterator it = map.keySet().iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Entry<T> next() {
                long time = (Long)it.next();
                T event = map.get(time);
                return new Entry(time, event);
            }
        };
    }
    
    public static class Entry<T> {
        private final Long time;
        private final T event;

        public Entry(long time, T event) {
            this.time = time;
            this.event = event;
        }

        public long getTime() {
            return time;
        }

        public T getEvent() {
            return event;
        }

        @Override
        public String toString() {
            return String.format("(%d, %s)", time, event);
        }
    }
}

