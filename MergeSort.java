
import java.util.Arrays;

public class MergeSort
{
    private static boolean lessthan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] data, int i, int j) {
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    public static boolean issorted(Comparable[] data) {
        return issorted(data, 0, data.length - 1);
    }

    public static boolean issorted(Comparable[] data, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (lessthan(data[i], data[i - 1])) return false;
        }
        return true;
    }
    
    public static void insertionsort(Comparable[] data) {
        int N = data.length;
        // insertion sort
        for (int i = 1; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (lessthan(data[j], data[j - 1])) exchange(data, j, j - 1);
                else                                break;
            }
        }
    }

    public static void merge(Comparable[] data, int lo, int mid, int hi) {
        // debug checks
        assert issorted(data, lo, mid - 1);
        assert issorted(data, mid, hi);
        // temporary copy (should create this outside??)
        Comparable[] datacopy = new Comparable[data.length];
        for (int i = 0; i < data.length; i++)
            { datacopy[i] = data[i]; }
        int ptlo = lo;
        int pthi = mid;
        for (int i = lo; i <= hi; i++) {
            if ((pthi > hi) || (lessthan(datacopy[ptlo], datacopy[pthi]) && ptlo < mid)) {
                data[i] = datacopy[ptlo];
                ptlo++;
            }
            else {
                data[i] = datacopy[pthi];
                pthi++;
            }
        }
    }

    public static void bottomup(Comparable[] data, int insertionsize) {
        if data.length < insertionsize {
            data = insertionsort(data);
            return;
        // sort first group using insertion
        insertionsort(data, 0, insertionsize - 1);
        // loop through groups, sorting internally then combining
        for (int start = insertionsize; start < N; start += insertionsize) {
            // insertion sort for this group
            insertionsort(data, start, start + insertionsize - 1);
            // merge with previous values (all sorted)
            merge(data, 0, start, start + insertionsize - 1);
        }
        // final section of array
        insertionsort(data, start - insertionsize, data.length - 1);
        merge(data, 0, start - insertionsize, data.length - 1)
    }

    public static void main(String[] args)
    {
        // test merge with integers
        System.out.println("Last > mid");
        Integer[] myintarray1 = new Integer[] {1, 3, 4, 5, 7, 2, 6, 8, 9, 10};
        System.out.println(Arrays.toString(myintarray1));
        merge(myintarray1, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray1));
        System.out.println(issorted(myintarray1, 1, 8));
        // test merge with integers
        System.out.println("Last < mid");
        Integer[] myintarray2 = new Integer[] {2, 4, 6, 9, 10, 1, 3, 5, 8, 7};
        System.out.println(Arrays.toString(myintarray2));
        merge(myintarray2, 1, 5, 8);
        System.out.println(Arrays.toString(myintarray2));
        System.out.println(issorted(myintarray2, 1, 8));
    }
}
