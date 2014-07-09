
import java.util.Arrays;

public class ShellSort
{
    // method invocation counters
    public int exchangecount = 0;
    public int comparecount = 0;

    // implementation
    private boolean lessthan(Comparable a, Comparable b) {
        comparecount++;
        return a.compareTo(b) < 0;
    }
    
    private void exchange(Comparable[] data, int i, int j) {
        exchangecount++;
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public boolean issorted(Comparable[] data) {
        for (int i = 1; i < data.length; i++) {
            if (lessthan(data[i], data[i - 1])) return false;
        }
        return true;
    }

    public boolean ishsorted(Comparable[] data, int h) {
        for (int start = 0; start < h; start++) {
            for (int j = start + h; j < data.length; j += h) {
                if (lessthan(data[j], data[j - h]))   return false;
            }
        }
        return true;
    }

    public void hsort(Comparable[] data, int h) {
        int N = data.length;
        // insertion sort
        for (int start = 0; start < h; start++) {
            for (int i = h + start; i < N; i += h) {
                for (int j = i; j >= h; j -= h) {
                    if (lessthan(data[j], data[j - h])) exchange(data, j, j - h);
                    else                                break;
                }
            }
        }
    }

    public void sort(Comparable[] data, int divisor) {
        for (int h = data.length / divisor; h > 1; h = h / divisor)
            { hsort(data, h); }
        hsort(data, 1);
    }

    public static void main(String[] args)
    {
        ShellSort testobj;
        // test with integers
        testobj = new ShellSort();
        Integer[] myintarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        System.out.println(Arrays.toString(myintarray));
        testobj.sort(myintarray, 3);
        System.out.println(Arrays.toString(myintarray));
        System.out.println(String.format("Compares: %d   Exchanges: %d",
            testobj.comparecount, testobj.exchangecount));
        // test with strings
        testobj = new ShellSort();
        String[] mystrarray = new String[] {"C", "B", "F", "A", "D", "E"};
        System.out.println(Arrays.toString(mystrarray));
        testobj.sort(mystrarray, 3);
        System.out.println(Arrays.toString(mystrarray));
        System.out.println(String.format("Compares: %d   Exchanges: %d",
            testobj.comparecount, testobj.exchangecount));
    }
}
