
import java.util.Arrays;

public class SelectionSort
{
    private static boolean lessthan(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    
    private static void exchange(Comparable[] data, int i, int j) {
        Comparable temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }
    
    public static void sort(Comparable[] data)
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
        // test with integers
        Integer[] myintarray = new Integer[] {3, 4, 5, 1, 2, 7, 8, 6, 10, 9, 11};
        System.out.println(Arrays.toString(myintarray));
        SelectionSort.sort(myintarray);
        System.out.println(Arrays.toString(myintarray));
        // test with strings
        String[] mystrarray = new String[] {"C", "B", "F", "A", "D", "E"};
        System.out.println(Arrays.toString(mystrarray));
        SelectionSort.sort(mystrarray);
        System.out.println(Arrays.toString(mystrarray));
    }
}
