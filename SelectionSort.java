
import java.util.Arrays;

public class SelectionSort
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
    
    public void sort(Comparable[] data)
    {
        int N = data.length;
        int minval;
        // selection loop
        for (int i = 0; i < N - 1; i++)
        {
            minval = i;
            for (int j = i + 1; j < N; j++)
                { if (lessthan(data[j], data[minval])) minval = j; }
            exchange(data, i, minval);
        }
    }

    public static void main(String[] args)
    {
        SelectionSort testobj;
        // test with integers
        testobj = new SelectionSort();
        Integer[] myintarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        System.out.println(Arrays.toString(myintarray));
        testobj.sort(myintarray);
        System.out.println(Arrays.toString(myintarray));
        System.out.println(String.format("Compares: %d   Exchanges: %d",
            testobj.comparecount, testobj.exchangecount));
        // test with strings
        testobj = new SelectionSort();
        String[] mystrarray = new String[] {"C", "B", "F", "A", "D", "E"};
        System.out.println(Arrays.toString(mystrarray));
        testobj.sort(mystrarray);
        System.out.println(Arrays.toString(mystrarray));
        System.out.println(String.format("Compares: %d   Exchanges: %d",
            testobj.comparecount, testobj.exchangecount));
    }
}
