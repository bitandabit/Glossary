import java.util.Comparator;

/**
 * Sorts given Queue into alphabetical order.
 */
public class SortComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareTo(o2);
    }
}
