import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by mark on 3/22/15.
 */
public class Counter {
  private String name;
  private int count = 0;
  private Map<String, Integer> domain = new TreeMap<String, Integer>();

  public Counter(String name) {
    this.name = name;
  }

  public void countItem(String item) {
    Integer itemCount = domain.get(item);
    if (itemCount == null) {
      domain.put(item, 1);
    }
    else {
      domain.put(item, itemCount+1);
    }
    count++;
  }

  public void dump() {
    System.out.printf("Counter: [%s]\n", name);

    Iterator it = domain.entrySet().iterator();
    while (it.hasNext()) {
      Map.Entry pair = (Map.Entry)it.next();
      System.out.printf("  %s:  %d\n", pair.getKey(), pair.getValue());
//    it.remove(); // avoids a ConcurrentModificationException
    }
    System.out.printf("  // %d\n", count);
  }

  public int getCount() {
    return count;
  }
}
